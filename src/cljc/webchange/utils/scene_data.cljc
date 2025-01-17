(ns webchange.utils.scene-data
  (:require
    [webchange.utils.scene-action-data :as action-data]))

(defn- gen-uuid []
  #?(:clj  (java.util.UUID/randomUUID)
     :cljs (random-uuid)))

(defn generate-name
  ([]
   (generate-name nil))
  ([parent-name]
   (let [uid (-> (gen-uuid)
                 (.toString)
                 (subs 0 8))]
     (if (some? parent-name)
       (str parent-name "-" uid)
       uid))))

(defn- process-object-data
  [object-data target-name]
  (if (= (:type object-data) "group")
    (let [children (:children object-data)
          updated-children (map (fn [_] (generate-name target-name)) children)]
      {:data      (cond-> (assoc object-data :children updated-children)
                          (contains? object-data :transition) (assoc :transition target-name))
       :to-rename (map vector children updated-children)})
    {:data      object-data
     :to-rename []}))

(defn rename-object
  ([template object-name]
   (rename-object template object-name (generate-name object-name)))
  ([template object-name new-object-name]
   (loop [result-template template
          [current-rename & rest-que] [[object-name new-object-name]]]
     (if (some? current-rename)
       (let [[source-name target-name] current-rename
             {:keys [data to-rename]} (process-object-data
                                        (get result-template (keyword source-name))
                                        target-name)]
         (recur
           (-> result-template
               (dissoc (keyword source-name))
               (assoc (keyword target-name) data))
           (concat rest-que to-rename)))
       result-template))))

;; Scene Data

(def empty-data {:assets        []
                 :objects       {}
                 :scene-objects []
                 :actions       {}
                 :triggers      {}
                 :metadata      {}})

; Assets

(defn- add-asset
  [scene-data asset-data]
  (->> (fn [assets]
         (->> assets
              (concat [asset-data])
              (vec)))
       (update scene-data :assets)))

(defn- update-asset
  [scene-data predicate asset-data-patch]
  (->> (fn [assets]
         (->> assets
              (map (fn [asset-data]
                     (if (predicate {:data asset-data})
                       (merge asset-data asset-data-patch)
                       asset-data)))
              (vec)))
       (update scene-data :assets)))

(defn- remove-asset
  [scene-data predicate]
  (->> (fn [assets]
         (->> assets
              (filter (fn [asset-data]
                        (-> {:data asset-data} (predicate) (not))))
              (vec)))
       (update scene-data :assets)))

; Objects

(defn- get-scene-objects
  [scene-data]
  (get scene-data :objects {}))

(defn get-scene-object
  [scene-data object-name]
  (-> (get-scene-objects scene-data)
      (get object-name)))

(defn get-scene-object-by-path
  [scene-data object-path]
  (-> (get-scene-objects scene-data)
      (get-in object-path)))

(defn get-scene-background
  [scene-data]
  (->> (get-scene-objects scene-data)
       (some (fn [[object-name {:keys [type] :as object-data}]]
               (and (some #{type} ["background" "layered-background"])
                    [object-name object-data])))))

; Actions

(defn- get-scene-actions
  [scene-data]
  (get scene-data :actions {}))

(defn get-action
  [scene-data action-name]
  (->> (get-scene-actions scene-data)
       (some (fn [[name data]]
               (and (= name action-name)
                    data)))))

(defn get-dialog-actions
  [scene-data]
  (->> (get-scene-actions scene-data)
       (filter (fn [[_ action-data]] (action-data/dialog-action? action-data)))
       (map first)))

(defn- add-action
  [scene-data action-name action-data]
  (assoc-in scene-data [:actions action-name] action-data))

(defn- update-action
  [scene-data predicate action-data-patch]
  (->> (fn [actions]
         (->> actions
              (map (fn [[action-name actions-data]]
                     (if (predicate {:name action-name
                                     :data actions-data})
                       [action-name (merge actions-data action-data-patch)]
                       [action-name actions-data])))
              (into {})))
       (update scene-data :actions)))

(defn find-actions
  [scene-data predicate]
  (->> (get-scene-actions scene-data)
       (filter (fn [[action-name action-data]]
                 (predicate {:name action-name
                             :data action-data})))))

(defn find-actions-by-tag
  [scene-data tag]
  (->> (fn [{:keys [data]}]
         (->> (get data :tags [])
              (some #{tag})))
       (find-actions scene-data)))

(defn- find-action-recursively-core
  [action-data action-path predicate]
  (cond
    (predicate action-data) {:path action-path
                             :data action-data}
    (map? action-data) (some (fn [[field-name field-value]]
                               (find-action-recursively-core field-value (conj action-path field-name) predicate))
                             action-data)
    (sequential? action-data) (some (fn [[idx data]]
                                      (find-action-recursively-core data (conj action-path idx) predicate))
                                    (map-indexed vector action-data))))

(defn find-action-recursively
  [scene-data predicate]
  (find-action-recursively-core (get-scene-actions scene-data)
                                []
                                predicate))

(defn- find-and-change-action-recursively-core
  [action-data predicate modifier]
  (cond
    (predicate action-data) (modifier action-data)
    (map? action-data) (->> action-data
                            (map (fn [[field-name field-value]]
                                   [field-name (find-and-change-action-recursively-core field-value predicate modifier)]))
                            (into {}))
    (sequential? action-data) (map (fn [data]
                                     (find-and-change-action-recursively-core data predicate modifier))
                                   action-data)
    :else action-data))

(defn find-and-change-action-recursively
  [scene-data predicate modifier]
  (find-and-change-action-recursively-core scene-data
                                           predicate
                                           modifier))

; Triggers

(def background-music-trigger-name :music)

(defn- get-scene-triggers
  [scene-data]
  (get scene-data :triggers {}))

(defn- get-trigger
  [scene-data trigger-name]
  (->> (get-scene-triggers scene-data)
       (some (fn [[name data]]
               (and (= name trigger-name)
                    data)))))

(defn- get-background-music-trigger
  [scene-data]
  (get-trigger scene-data :music))

(defn- add-trigger
  [scene-data trigger-name trigger-data]
  (assoc-in scene-data [:triggers trigger-name] trigger-data))

;; Metadata

(defn get-metadata
  [scene-data]
  (get scene-data :metadata {}))

(defn get-template-name
  [scene-data]
  (-> (get-metadata scene-data)
      (get :template-name)))

(defn- get-metadata-actions
  [scene-data]
  (-> (get-metadata scene-data)
      (get :actions)))

(defn get-tracks
  [scene-data]
  (-> (get-metadata scene-data)
      (get :tracks)))

(defn get-track-by-id
  [scene-data track-id]
  (when (some? track-id)
    (->> (get-tracks scene-data)
         (some (fn [{:keys [id] :as track}]
                 (and (= id track-id) track))))))

(defn get-track-by-index
  [scene-data track-index]
  (when (some? track-index)
    (-> (get-tracks scene-data)
        (nth track-index nil))))

(defn get-main-track
  [scene-data]
  (get-track-by-id scene-data "main"))

(defn get-track-actions
  [scene-data track-id]
  (->> (get-metadata-actions scene-data)
       (filter (fn [[_ data]]
                 (= (:track-id data) track-id)))
       (into {})))

(defn get-metadata-untracked-actions
  [scene-data]
  (get-track-actions scene-data nil))

(defn get-available-actions
  [scene-data]
  (-> (get-metadata scene-data)
      (get :available-actions [])))

(defn set-available-actions
  [scene-data available-actions]
  (assoc-in scene-data [:metadata :available-actions] available-actions))

(defn get-available-effects
  [scene-data]
  (->> (get-available-actions scene-data)
       (map action-data/fix-available-effect)))

(defn get-history
  [scene-data]
  (-> (get-metadata scene-data)
      (get :history {})))

(defn get-updates-history
  [scene-data]
  (-> (get-history scene-data)
      (get :updated [])))

(defn set-updates-history
  [scene-data updates]
  (assoc-in scene-data [:metadata :history :updated] updates))

; General

(defn- get-background-music-action
  [scene-data]
  (let [action-name (->> (get-background-music-trigger scene-data)
                         (:action)
                         (keyword))]
    [action-name (get-action scene-data action-name)]))

(defn get-background-music-src
  [scene-data]
  (->> (get-background-music-action scene-data)
       (second)
       (:id)))

(defn- add-background-music
  [scene-data music-src]
  (let [action-name :start-background-music-action
        trigger-name :music]
    (-> scene-data
        (add-asset {:url music-src :size 10 :type "audio"})
        (add-action action-name {:type "audio" :id music-src :loop true})
        (add-trigger trigger-name {:on "start" :action (clojure.core/name action-name)}))))

(defn- change-background-music
  [scene-data old-music-src new-music-src]
  (let [action-name (-> (get-background-music-action scene-data) (first))]
    (-> scene-data
        (remove-asset (fn [{:keys [data]}] (= (:url data) old-music-src)))
        (add-asset {:url new-music-src :size 10 :type "audio"})
        (update-action (fn [{:keys [name]}] (= name action-name)) {:id new-music-src}))))

(defn set-background-music
  [scene-data music-src]
  (let [current-music-src (get-background-music-src scene-data)]
    (if (some? current-music-src)
      (change-background-music scene-data current-music-src music-src)
      (add-background-music scene-data music-src))))
