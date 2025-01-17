(ns webchange.templates.common-actions
  (:require
    [clojure.data.json :as json]
    [clojure.string :as string]
    [clojure.tools.logging :as log]
    [webchange.templates.utils.characters :refer [animations character-positions]]
    [webchange.utils.scene-common-actions :as common-actions-utils]))

(defn- file-used?
  [scene-data file]
  (clojure.string/includes? (json/write-str (select-keys scene-data [:objects :actions])) (json/write-str file)))

(defn- remove-asset
  [scene-data file]
  (assoc scene-data :assets
                    (vec (remove (fn [asset] (and (= (:type asset) "audio") (= (:url asset) file)))
                                 (:assets scene-data)))))

(defn- update-background-music
  [scene-data {:keys [background-music]}]
  (let [background-music-name "start-background-music"
        file-name (get-in background-music [:src]),
        volume (get-in background-music [:volume]),
        start-background-music {:type "audio", :id file-name :volume volume :loop true}
        music {:on "start", :action background-music-name}
        asset {:url file-name, :size 10, :type "audio"}
        triggers (:triggers scene-data)]
    (cond-> scene-data
            (contains? triggers :music)
            ((fn [data]
               (let [action-key (keyword (get-in triggers [:music :action]))
                     file (get-in data [:actions action-key :id])
                     start-background-music (if file-name
                                              start-background-music
                                              (assoc start-background-music :id file))
                     data (assoc-in data [:actions action-key] start-background-music)]
                 (if (file-used? data file) data (remove-asset data file))
                 )))
            (not (contains? triggers :music)) ((fn [data]
                                                 (-> data
                                                     (assoc-in [:actions (keyword background-music-name)] start-background-music)
                                                     (assoc-in [:triggers :music] music))))
            true (update :assets conj asset)
            true (update :assets vec))))

(defn- remove-background-music
  [scene-data]
  (let [triggers (:triggers scene-data)]
    (cond-> scene-data
            (contains? triggers :music)
            ((fn [data]
               (let [action-key (keyword (get-in triggers [:music :action]))
                     file (get-in data [:actions action-key :id])
                     data (update-in data [:actions] dissoc action-key)]
                 (if (file-used? data file) data (remove-asset data file)))))
            true (update-in [:triggers] dissoc :music))))

(defn- add-object-to-last-layer
  [scene-data object-name]
  (let [last-layer (-> scene-data
                       :scene-objects
                       last
                       (concat [object-name]))
        layers (-> scene-data
                   :scene-objects
                   drop-last
                   (concat [last-layer]))]
    (assoc-in scene-data [:scene-objects] layers)))

(defn- add-image
  [scene-data {:keys [name image]}]
  (let [image-idx (-> scene-data
                      (get-in [:metadata :uploaded-image-idx])
                      (or 0)
                      inc)
        object-name (str "uploaded-image-" image-idx)
        show-action-name (str "show-uploaded-image-" image-idx)
        hide-action-name (str "hide-uploaded-image-" image-idx)
        image-object {:type      "image"
                      :alias     name
                      :links     [{:type "action" :id show-action-name}
                                  {:type "action" :id hide-action-name}]
                      :src       (:src image)
                      :origin    {:type "center-center"}
                      :x         960
                      :y         540
                      :visible   false
                      :metadata  {:uploaded-image? true}
                      :editable? {:select        true
                                  :drag          true
                                  :show-in-tree? true}}
        show-action {:type "set-attribute" :attr-name "visible", :attr-value true :target object-name}
        hide-action {:type "set-attribute" :attr-name "visible", :attr-value false :target object-name}
        available-actions [{:action show-action-name
                            :type   "image"
                            :links  [{:type "object" :id object-name}]
                            :name   (str "Show " name)}
                           {:action hide-action-name
                            :type   "image"
                            :links  [{:type "object" :id object-name}]
                            :name   (str "Hide " name)}]]
    (-> scene-data
        (update-in [:assets] concat [{:url (:src image) :type "image" :size 1}])
        (assoc-in [:objects (keyword object-name)] image-object)
        (add-object-to-last-layer object-name)
        (assoc-in [:actions (keyword show-action-name)] show-action)
        (assoc-in [:actions (keyword hide-action-name)] hide-action)
        (assoc-in [:metadata :uploaded-image-idx] image-idx)
        (update-in [:metadata :available-actions] concat available-actions))))

(defn- add-character
  [scene-data {:keys [name skin] :as data}]
  (let [character-idx (-> scene-data
                          (get-in [:metadata :added-character-idx])
                          (or 0)
                          inc)
        character-name (cond-> name
                               (some? skin) (str "-" skin)
                               :always (str "-" character-idx)
                               :always (-> (string/replace " " "-")
                                           (string/replace "_" "-")
                                           (string/lower-case)))

        show-action-name (str "show-character-" character-name)
        hide-action-name (str "hide-character-" character-name)
        show-action {:type "set-attribute" :attr-name "visible" :attr-value true :target character-name}
        hide-action {:type "set-attribute" :attr-name "visible" :attr-value false :target character-name}
        available-actions [{:action show-action-name
                            :type   "image"
                            :links  [{:type "object" :id character-name}]
                            :name   (str "Show " character-name)}
                           {:action hide-action-name
                            :type   "image"
                            :links  [{:type "object" :id character-name}]
                            :name   (str "Hide " character-name)}]

        character-data (merge {:type      "animation"
                               :anim      "idle"
                               :start     true
                               :editable? {:select        true
                                           :drag          true
                                           :show-in-tree? true}
                               :metadata  {:added-character? true}
                               :links     [{:type "action" :id show-action-name}
                                           {:type "action" :id hide-action-name}]}
                              (get animations name {:scale {:x 1 :y 1}
                                                    :speed 1})
                              (nth character-positions character-idx {:x 500 :y 500})
                              data)]
    (-> scene-data
        (assoc-in [:objects (keyword character-name)] character-data)
        (add-object-to-last-layer character-name)
        (assoc-in [:metadata :added-character-idx] character-idx)
        (assoc-in [:actions (keyword show-action-name)] show-action)
        (assoc-in [:actions (keyword hide-action-name)] hide-action)
        (update-in [:metadata :available-actions] concat available-actions))))

(defn update-activity
  [scene-data action data]
  (case (keyword action)
    :background-music (update-background-music scene-data data)
    :background-music-remove (remove-background-music scene-data)
    :add-image (add-image scene-data data)
    :remove-image (common-actions-utils/remove-image scene-data data)
    :add-character (add-character scene-data data)
    :remove-character (common-actions-utils/remove-character scene-data data)
    :remove-question (common-actions-utils/remove-question scene-data data)))
