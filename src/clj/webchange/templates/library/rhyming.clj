(ns webchange.templates.library.rhyming
  (:require
    [webchange.templates.utils.common :as common]
    [webchange.templates.core :as core]
    [clojure.string :as str]
    [webchange.templates.utils.dialog :as dialog]))

(def concept-image-options {:max-width  100
                            :max-height 100
                            :min-height 20
                            :min-width  20})

(def m {:id          27
        :name        "Rhyming"
        :tags        ["Independent Practice"]
        :description "Users are presented with two category labels and a group of words or images to sort. Users drag the words or images to sort them into their correct categories."
        :options     {:left  {:label       "Left Goal word"
                              :placeholder "Type left goal word here.."
                              :type        "string"
                              :description "Left Goal"}
                      :right {:label       "Right Goal word"
                              :placeholder "Type right goal word here.."
                              :type        "string"
                              :description "Right Goal"}}
        :props       {:game-changer?    true
                      :preview          "/images/templates/previews/rhyming.png"
                      :preview-activity {:course-slug   "q-q-nzvzixrf"
                                         :activity-slug "q"}}
        :actions     {:add-ball    {:title    "Add ball"
                                    :track-id "main"
                                    :options  {:side {:label   "Correct ball side"
                                                      :type    "lookup"
                                                      :options [{:name "Left" :value "left"}
                                                                {:name "Right" :value "right"}]}
                                               :text {:label       "Name"
                                                      :placeholder "Place your word here"
                                                      :type        "string"}
                                               :img  {:label   "Dialog"
                                                      :type    "image"
                                                      :options concept-image-options}}}
                      :remove-ball {:title   "Remove ball"
                                    :options {:remove-ball {:label "Remove ball"
                                                            :type  "remove-editable-object"}}}}})

(def t {:assets        [{:url "/raw/img/rhyming/background.png", :size 10 :type "image"}
                        {:url "/raw/img/rhyming/surface.png", :size 10 :type "image"}
                        {:url "/raw/img/rhyming/left-gate.png", :size 10 :type "image"}
                        {:url "/raw/img/rhyming/right-gate.png", :size 10 :type "image"}
                        {:url "/raw/img/rhyming/ball.png", :size 10 :type "image"}

                        {:url "/raw/clipart/rhyming/rhyming_background.png", :size 10 :type "image"}
                        {:url "/raw/clipart/rhyming/rhyming_background.png", :size 10 :type "image"}
                        {:url "/raw/clipart/rhyming/etc--rhyming-content_wrapper--ball.png", :size 1 :type "image"}
                        {:url "/raw/clipart/rhyming/etc--rhyming-gate--gate_left.png", :size 1 :type "image"}
                        {:url "/raw/clipart/rhyming/etc--rhyming-gate--gate_right.png", :size 1 :type "image"}
                        {:url "/raw/clipart/rhyming/etc--rhyming-plate--board.png", :size 1 :type "image"}
                        {:url "/raw/clipart/rhyming/etc--rhyming-plate--board1.png", :size 1 :type "image"}
                        ],
        :objects
        {:layered-background {:type       "layered-background"
                              :background {:src "/raw/clipart/rhyming/rhyming_background.png"}
                              :surface    {:src "/raw/clipart/rhyming/rhyming_surface.png"}}
         :left-gate          {:type       "image"
                              :src        "/raw/clipart/rhyming/etc--rhyming-gate--gate_left.png"
                              :x          303
                              :y          700
                              :max-width  700
                              :max-height 850
                              :origin     {:type "center-center"}
                              :transition "left-gate"
                              :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}}
                              :editable?  {:select     true
                                           :drag       true
                                           :image-tags ["rhyming" "gate"]
                                           :edit-form  {:select-image true
                                                        :upload-image true
                                                        :flip         true}}}
         :left-gate-text-group {:type "group"
                                :x 355
                                :y 330
                                :editable?      {:select true
                                                 :drag   true}
                                :children ["left-gate-plate" "left-gate-text"]}
         :left-gate-plate {:type       "image"
                           :src        "/raw/clipart/rhyming/etc--rhyming-plate--board1.png"
                           :max-width  700
                           :max-height 850
                           :origin     {:type "center-center"}
                           
                           :editable? {:image-tags ["rhyming" "plate"]
                                       :edit-form {:select-image true
                                                   :upload-image true
                                                   :flip true}}}
         :left-gate-text     {:type           "text"
                              :text           "rhyme"
                              :skew-x         0
                              :skew-y         0.19
                              :align          "center"
                              :vertical-align "middle"
                              :font-family    "Lexend Deca"
                              :font-size      80
                              :fill           "#000000"
                              :editable? {}}

         :right-gate         {:type       "image"
                              :src        "/raw/clipart/rhyming/etc--rhyming-gate--gate_right.png"
                              :x          1633
                              :y          700
                              :max-width  700
                              :max-height 850
                              :origin     {:type "center-center"}
                              :transition "right-gate"
                              :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}}
                              :editable?  {:select     true
                                           :drag       true
                                           :image-tags ["rhyming" "gate"]
                                           :edit-form  {:select-image true
                                                        :upload-image true
                                                        :flip         true}}}
         :right-gate-text-group {:type "group"
                                 :x              1590
                                 :y              330
                                 :editable?      {:select true
                                                  :drag   true}
                                 :children ["right-gate-plate" "right-gate-text"]}
         :right-gate-plate {:type       "image"
                            :src        "/raw/clipart/rhyming/etc--rhyming-plate--board.png"
                            :max-width  700
                            :max-height 850
                            :origin     {:type "center-center"}
                            :editable? {:image-tags ["rhyming" "plate"]
                                        :edit-form {:select-image true
                                                    :upload-image true
                                                    :flip true}}}
         :right-gate-text    {:type           "text"
                              :text           "rhyme"
                              :skew-x         -0.05
                              :skew-y         -0.19
                              :align          "center"
                              :vertical-align "middle"
                              :font-family    "Lexend Deca"
                              :font-size      80
                              :fill           "#000000"
                              :editable? {}
                              }
         },
        :scene-objects [["layered-background"] ["left-gate" "right-gate" "left-gate-text-group" "right-gate-text-group"]],
        :actions       {:start-drag           {:type "sequence-data"
                                               :data [{:type "set-variable", :var-name "left-selected", :var-value false}
                                                      {:type "set-variable", :var-name "right-selected", :var-value false}
                                                      {:type "set-variable", :var-name "next-check-collide", :var-value true}
                                                      {:id "next-check-collide" :type "action"}
                                                      {:type "action", :from-params [{:action-property "id" :param-property "tap-dialog"}]}]}
                        :next-check-collide   {:type "sequence-data"
                                               :data [{:type     "set-timeout"
                                                       :action   "check-collide"
                                                       :interval 10}]}
                        :check-collide        {:type "sequence-data"
                                               :data [{:type          "test-transitions-and-pointer-collide",
                                                       :success       "highlight",
                                                       :fail          "unhighlight",
                                                       :transitions   ["right-gate" "left-gate"]
                                                       :action-params [{:target   "right-gate"
                                                                        :variable "right-selected"}
                                                                       {:target   "left-gate"
                                                                        :variable "left-selected"}]}
                                                      {:type     "test-var-scalar",
                                                       :success  "next-check-collide",
                                                       :value    true,
                                                       :var-name "next-check-collide"}]}
                        :highlight            {:type "sequence-data"
                                               :data [{:type        "set-variable",
                                                       :var-value   true
                                                       :from-params [{:action-property "var-name" :param-property "variable"}]}
                                                      {:type        "state"
                                                       :id          "highlighted"
                                                       :from-params [{:action-property "target" :param-property "target"}]}]}
                        :unhighlight          {:type "sequence-data"
                                               :data [{:type        "set-variable",
                                                       :var-value   false
                                                       :from-params [{:action-property "var-name" :param-property "variable"}]}
                                                      {:type        "state"
                                                       :id          "not-highlighted"
                                                       :from-params [{:action-property "target" :param-property "target"}]}]}
                        :wrong-option         {:type "parallel",
                                               :data [{:id "wrong-answer-dialog", :type "action"}
                                                      {:to          {:init-position true :duration 0.5},
                                                       :from-params [{:action-property "transition-id" :param-property "target"}]
                                                       :type        "transition"}]}
                        :wrong-answer-dialog  {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "wrong-answer",
                                               :phrase-description "wrong answer"}
                        :check-wrong-option   {:type      "test-var-list-at-least-one-true"
                                               :var-names ["saved-left-selected" "saved-right-selected"]
                                               :success   "wrong-option"}
                        :end-dragging         {:type "sequence-data"
                                               :data [{:type      "copy-variables",
                                                       :var-names ["saved-left-selected" "saved-right-selected"]
                                                       :from-list ["left-selected" "right-selected"]}
                                                      {:type        "test-var-scalar",
                                                       :success     "correct-option",
                                                       :fail        "check-wrong-option",
                                                       :value       true,
                                                       :from-params [{:param-property  "side",
                                                                      :template        "saved-%-selected",
                                                                      :action-property "var-name"}]}
                                                      {:type "set-variable", :var-name "next-check-collide", :var-value false}
                                                      {:type        "state"
                                                       :id          "not-highlighted"
                                                       :from-params [{:action-property "target" :param-property "gate"}]}]}
                        :correct-option       {:type "sequence-data",
                                               :data [{:type "set-variable", :var-name "next-check-collide", :var-value false}
                                                      {:type "counter" :counter-action "increase" :counter-id "sorted-counter"}
                                                      {:id "object-in-right-gate", :type "action"}
                                                      {:id "correct-answer", :type "action"}
                                                      {:type       "test-var-inequality"
                                                       :var-name   "sorted-counter",
                                                       :value      0,
                                                       :inequality ">=",
                                                       :success    "finish-scene",}]}
                        :init-activity        {:type "sequence-data"
                                               :data [{:type "start-activity"}
                                                      {:type "counter" :counter-action "reset" :counter-value 0 :counter-id "sorted-counter"}
                                                      {:type "action" :id "intro"}]}
                        :intro                {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :tags               ["intro"],
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "intro",
                                               :phrase-description "introduction"}
                        :object-in-right-gate {:id          "park-position",
                                               :type        "state",
                                               :from-params [{:action-property "target" :param-property "target"}]}
                        :correct-answer       {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "correct-answer",
                                               :phrase-description "correct answer"}
                        :stop-activity        {:type "sequence-data",
                                               :data [{:type "set-variable", :var-name "next-check-collide", :var-value false}
                                                      {:type "stop-activity"},]}
                        :read-all-word-left   {:type "sequence-data",
                                               :data [],}
                        :read-all-word-right  {:type "sequence-data",
                                               :data [],}
                        :finish-scene         {:type       "sequence-data",
                                               :data       [{:type "action" :id "read-all-word-left"}
                                                            {:type "action" :id "read-all-word-right"}
                                                            {:type "action" :id "finish-dialog"}
                                                            {:type "finish-activity"}],
                                               :unique-tag "click"}
                        :finish-dialog        {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "finish-dialog",
                                               :phrase-description "finish dialog"}}
        :triggers      {:back  {:on "back", :action "stop-activity"},
                        :start {:on "start", :action "init-activity"}},
        :metadata      {:tracks [{:id    "main"
                                  :title "Main Track"
                                  :nodes [{:type      "dialog"
                                           :action-id "intro"}
                                          {:type     "track"
                                           :track-id "left-track"}
                                          {:type     "track"
                                           :track-id "right-track"}
                                          {:type      "dialog"
                                           :action-id "finish-dialog"}]}
                                 {:id    "left-track"
                                  :title "Track left"
                                  :nodes []}
                                 {:id    "right-track"
                                  :title "Track right"
                                  :nodes []}]}})

(defn park-ball-position
  [side balls-number]
  (let [balls-in-row 5
        step-left-y 160
        step-left-x 160
        start-left-y 10
        start-left-x 10

        step-right-y 160
        step-right-x -160
        start-right-y 10
        start-right-x 1730]
    (case side
      "left" {:y (+ start-left-y (* step-left-y (int (/ balls-number balls-in-row))))
              :x (+ start-left-x (* step-left-x (int (mod balls-number balls-in-row))))}
      "right" {:y (+ start-right-y (* step-right-y (int (/ balls-number balls-in-row))))
               :x (+ start-right-x (* step-right-x (int (mod balls-number balls-in-row))))})))

(defn- ball-dialog
  [suffix]
  (common/make-name-unique-by-suffix "ball-dialog" suffix))

(defn- ball
  [suffix]
  (common/make-name-unique-by-suffix "ball" suffix))

(defn- ball-group
  [suffix]
  (common/make-name-unique-by-suffix "ball-group" suffix))

(defn- ball-img
  [suffix]
  (common/make-name-unique-by-suffix "ball-img" suffix))

(defn- ball-text
  [suffix]
  (common/make-name-unique-by-suffix "ball-text" suffix))

(defn add-ball
  [scene-data {:keys [side img text]}]
  (let [suffix (common/get-unique-suffix scene-data)
        ball-dialog-name (ball-dialog suffix)
        ball-name (ball suffix)
        ball-group-name (ball-group suffix)
        balls (get-in scene-data [:metadata :balls (keyword side)])
        ball-img-name (ball-img suffix)
        ball-text-name (ball-text suffix)]
    [[(name (ball-group suffix))]
     {ball-name       {:type       "image"
                       :src        "/raw/img/rhyming/ball.png"
                       :x          100
                       :y          100
                       :origin {:type "center-center"}
                       :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}}
                       :transition (name ball-name)
                       :alias      "Concept wrapper"
                       :editable?  {:image-tags ["rhyming" "content wrapper"]
                                    :edit-tags ["wrapper"]
                                    :edit-form  {:select-image true
                                                 :scale true
                                                 :apply-to-all true}}}
      ball-img-name   (merge {:type    "image"
                              :src     (:src img)
                              :x       100
                              :y       85
                              :origin {:type "center-center"}
                              :alias   "Concept image"
                              :editable? {:edit-form {:select-image true
                                                      :upload-image true
                                                      :scale true
                                                      :flip true
                                                      :visible true}}}
                             concept-image-options)
      ball-text-name  {:type           "text"
                       :text           text
                       :x              100
                       :y              125
                       :origin {:type "center-center"}
                       :align          "center"
                       :vertical-align "middle"
                       :font-family    "Lexend Deca"
                       :font-size      30
                       :fill           "#000000"
                       :alias          "Concept text"
                       :editable? {}}
      ball-group-name {:type       "group"
                       :x          500
                       :y          500
                       :children   [ball-name
                                    ball-img-name
                                    ball-text-name]
                       :draggable  true,
                       :editable?  true
                       :transition (name ball-group-name)
                       :actions    {:drag-start
                                    {:type   "action",
                                     :on     "drag-start",
                                     :id     "start-drag"
                                     :params {:tap-dialog ball-dialog-name}}

                                    :drag-end
                                    {:id     "end-dragging",
                                     :on     "drag-end",
                                     :type   "action",
                                     :params {:gate           (str side "-gate")
                                              :side           side
                                              :target         ball-group-name
                                              :check-variable (str side "-selected")}}}
                       :states     {:park-position (park-ball-position side balls)}}}
     {ball-dialog-name (dialog/default text)}
     [{:url (:src img), :size 10, :type "image"}]
     ball-dialog-name
     ball-name]))

(defn f
  [args]
  (-> (common/init-metadata m t args)
      (assoc-in [:metadata :balls :left] 0)
      (assoc-in [:metadata :balls :right] 0)
      (assoc-in [:objects :left-gate-text :text] (:left args))
      (assoc-in [:objects :right-gate-text :text] (:right args))))

(defn add-ball-action
  [old-data {:keys [side] :as args}]
  (let [[scene-objects objects actions assets ball-dialog-name ball-name] (add-ball old-data args)]
    (-> old-data
        (update-in [:objects] merge objects)
        (update-in [:assets] concat assets)
        (update-in [:actions] merge actions)
        (update-in [:actions (keyword (str "read-all-word-" side)) :data]
                   concat [{:type "state" :target ball-name :id "highlighted"}
                           {:type "action" :id (name ball-dialog-name)}
                           {:type "state" :target ball-name :id "not-highlighted"}])
        (common/add-track-action {:track-name (str "Track " side)
                                  :type       "dialog"
                                  :action-id  (keyword ball-dialog-name)})
        (update-in [:metadata :balls (keyword side)] inc)
        (common/add-scene-object scene-objects)
        (common/update-unique-suffix)
        (update-in [:actions :correct-option :data 4 :value] inc))))

(defn- move-side-balls-to-remove-gaps
  [old-data side]
  (let [suffixes (as-> old-data dt
                   (get-in dt [:actions (keyword (str "read-all-word-" side)) :data])
                   (filter (fn [action]
                             (= (:type action) "action")) dt)
                   (map (fn [action]
                          (println (:id action))
                          (last (str/split (:id action) #"-"))) dt))
        objects (into {} (map-indexed (fn [idx suffix]
                                        (let [ball-group-name (ball-group suffix)]
                                          [ball-group-name
                                           (-> old-data
                                               (get-in [:objects ball-group-name])
                                               (assoc-in [:states :park-position] (park-ball-position side idx)))])
                                        ) suffixes))]
    (update-in old-data [:objects] merge objects)))

(defn remove-ball-action
  [old-data {:keys [object] :as args}]
  (let [[_ _ suffix] (str/split object #"-")

        ball-dialog-name (ball-dialog suffix)
        ball-name (ball suffix)
        ball-group-name (ball-group suffix)
        ball-img-name (ball-img suffix)
        ball-text-name (ball-text suffix)
        side (get-in old-data [:objects ball-group-name :actions :drag-end :params :side])]
    (-> old-data
        (common/remove-asset (get-in old-data [:objects ball-img-name :src]))
        (common/remove-object ball-group-name)
        (common/remove-object ball-text-name)
        (common/remove-object ball-img-name)
        (common/remove-object ball-name)
        (update-in [:actions] dissoc ball-dialog-name)
        (update-in [:actions (keyword (str "read-all-word-" side)) :data]
                   common/remove-actions-by-key-value
                   :target
                   (name ball-name))
        (update-in [:actions (keyword (str "read-all-word-" side)) :data]
                   common/remove-actions-by-key-value
                   :id
                   (name ball-dialog-name))
        (update-in [:metadata :balls (keyword side)] dec)
        (update-in [:actions :correct-option :data 4 :value] dec)
        (move-side-balls-to-remove-gaps side)
        (common/remove-action-from-tracks ball-dialog-name))))

(defn fu
  [old-data {:keys [action-name] :as args}]
  (case action-name
    "add-ball" (add-ball-action old-data args)
    "remove-ball" (remove-ball-action old-data args)))

(core/register-template
  m f fu)

