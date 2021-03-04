(ns webchange.templates.library.running_time_limited
  (:require
    [webchange.templates.core :as core]))

(def m {:id          33
        :name        "Running (time limited)"
        :tags        ["Independent Practice"]
        :description "Running"
        :lesson-sets ["concepts-group"]
        :fields      [{:name "image-src"
                       :type "image"}
                      {:name "concept-name"
                       :type "string"}
                      {:name "letter"
                       :type "string"}]
        :options     {:time {:label   "Time in seconds"
                             :type    "lookup"
                             :options [{:name "30" :value 30}
                                       {:name "45" :value 45}
                                       {:name "60" :value 60}]}}})

(def t {:assets        [{:url "/raw/img/stadium/running/bg_01.jpg" :type "image"}
                        {:url "/raw/img/stadium/running/bg_02.jpg" :type "image"}
                        {:url "/raw/img/stadium/running/bg_03.jpg" :type "image"}]
        :objects       {:background          {:type   "carousel"
                                              :x      0
                                              :y      0
                                              :width  1920
                                              :height 1080
                                              :first  "/raw/img/stadium/running/bg_01.jpg"
                                              :last   "/raw/img/stadium/running/bg_03.jpg"
                                              :next   "/raw/img/stadium/running/bg_02.jpg"}
                        :timer               {:type          "timer"
                                              :transition    "timer"
                                              :x             900
                                              :y             100
                                              :show-minutes  false
                                              :show-progress true
                                              :time          60
                                              :actions       {:end {:on "end" :type "action" :id "finish-game"}}
                                              :states        {:highlighted {:filter "glow"}
                                                              :normal      {:filter ""}}}

                        :target-group        {:type     "group"
                                              :x        260
                                              :y        60
                                              :children ["letter-target" "box-target" "counter"]}
                        :letter-target       {:type           "text"
                                              :x              0
                                              :y              -10
                                              :width          150
                                              :height         150
                                              :transition     "letter-target"
                                              :align          "center"
                                              :fill           0xff9000
                                              :font-family    "Lexend Deca"
                                              :font-size      120
                                              :shadow-blur    5
                                              :shadow-color   "#1a1a1a"
                                              :shadow-opacity 0.5
                                              :text           "a"
                                              :vertical-align "middle"
                                              :states         {:highlighted {:filter "glow"}
                                                               :normal      {:filter ""}}}
                        :box-target          {:type        "animation"
                                              :x           250
                                              :y           150
                                              :width       671
                                              :height      633
                                              :scale       {:x 0.25 :y 0.25}
                                              :scene-name  "box-target"
                                              :transition  "box-target"
                                              :anim        "idle2"
                                              :anim-offset {:x 0 :y -300}
                                              :loop        true
                                              :name        "boxes"
                                              :skin        "qwestion"
                                              :speed       0.3
                                              :start       true}

                        :counter             {:type       "counter"
                                              :transition "counter"
                                              :x          400
                                              :y          40}

                        :line-1              {:type    "transparent"
                                              :x       0
                                              :y       630
                                              :width   1920
                                              :height  85
                                              :actions {:click {:id "go-line" :on "click" :type "action" :params {:line "box1"}}}}
                        :line-2              {:type    "transparent"
                                              :x       0
                                              :y       717
                                              :width   1920
                                              :height  105
                                              :actions {:click {:id "go-line" :on "click" :type "action" :params {:line "box2"}}}}
                        :line-3              {:type    "transparent"
                                              :x       0
                                              :y       825
                                              :width   1920
                                              :height  155
                                              :actions {:click {:id "go-line" :on "click" :type "action" :params {:line "box3"}}}}

                        :mari                {:type        "animation"
                                              :x           1265
                                              :y           311
                                              :width       473
                                              :height      511
                                              :scene-name  "mari"
                                              :transition  "mari"
                                              :anim        "idle"
                                              :anim-offset {:x 0 :y -150}
                                              :name        "mari"
                                              :scale-x     0.5
                                              :scale-y     0.5
                                              :speed       0.5
                                              :start       true}
                        :vera-group          {:type       "group"
                                              :x          500
                                              :y          775
                                              :transition "vera-group"
                                              :children   ["vera" "vera-collision-test"]}
                        :emit-group          {:type "group"}
                        :vera                {:type       "animation"
                                              :x          0
                                              :y          0
                                              :width      727
                                              :height     1091
                                              :scene-name "vera"
                                              :transition "vera"
                                              :anim       "run"
                                              :meshes     true
                                              :name       "vera-90"
                                              :scale-x    0.4
                                              :scale-y    0.4
                                              :skin       "default"
                                              :speed      1
                                              :start      true
                                              :editable?  true}
                        :vera-collision-test {:type        "transparent"
                                              :x           150
                                              :y           -55
                                              :width       10
                                              :height      10
                                              :transition  "vera-collision-test"
                                              :collidable? true
                                              :actions     {:collide {:on "collide" :test ["#^target-letter-.*"] :type "action" :id "check-box" :pick-event-param ["custom-data" "transition-name"]}}}}
        :scene-objects [["background"]
                        ["emit-group"]
                        ["vera-group" "mari"]
                        ["target-group" "timer" "line-1" "line-2" "line-3"]]
        :actions       {:dialog-1-welcome        {:type               "sequence-data"
                                                  :editor-type        "dialog"
                                                  :concept-var        "current-concept"
                                                  :data               [{:type "sequence-data"
                                                                        :data [{:type "empty" :duration 0}
                                                                               {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase             "welcome"
                                                  :phrase-description "Welcome dialog"
                                                  :dialog-track       "1 Welcome"
                                                  :skippable          true}
                        :dialog-2-intro-concept  {:type               "sequence-data"
                                                  :editor-type        "dialog"
                                                  :concept-var        "current-concept"
                                                  :data               [{:type "sequence-data"
                                                                        :data [{:type "empty" :duration 0}
                                                                               {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase             "concept"
                                                  :phrase-description "Introduce concept"
                                                  :dialog-track       "2 Introduce"
                                                  :tags               ["instruction"]}
                        :dialog-3-intro-timer    {:type               "sequence-data"
                                                  :editor-type        "dialog"
                                                  :concept-var        "current-concept"
                                                  :data               [{:type "sequence-data"
                                                                        :data [{:type "empty" :duration 0}
                                                                               {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase             "timer"
                                                  :phrase-description "Introduce timer"
                                                  :dialog-track       "2 Introduce"
                                                  :tags               ["instruction"]}
                        :dialog-4-ready-go       {:type               "sequence-data"
                                                  :editor-type        "dialog"
                                                  :concept-var        "current-concept"
                                                  :data               [{:type "sequence-data"
                                                                        :data [{:type "empty" :duration 0}
                                                                               {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase             "ready-go"
                                                  :phrase-description "Ready-Go"
                                                  :dialog-track       "3 Start"}
                        :dialog-5-starting-noise {:type               "sequence-data"
                                                  :editor-type        "dialog"
                                                  :concept-var        "current-concept"
                                                  :data               [{:type "sequence-data"
                                                                        :data [{:type "empty" :duration 0}
                                                                               {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase             "noise"
                                                  :phrase-description "Starting noise"
                                                  :dialog-track       "3 Start"}

                        :dialog-6-correct        {:type               "sequence-data"
                                                  :editor-type        "dialog"
                                                  :concept-var        "current-concept"
                                                  :data               [{:type "sequence-data"
                                                                        :data [{:type "empty" :duration 0}
                                                                               {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase             "correct"
                                                  :phrase-description "Correct dialog"
                                                  :dialog-track       "4 Options"}
                        :dialog-7-wrong          {:type               "sequence-data"
                                                  :editor-type        "dialog"
                                                  :concept-var        "current-concept"
                                                  :data               [{:type "sequence-data"
                                                                        :data [{:type "empty" :duration 0}
                                                                               {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase             "wrong"
                                                  :phrase-description "Wrong dialog"
                                                  :dialog-track       "4 Options"}

                        :go-line                 {:type "sequence-data"
                                                  :data [{:type     "set-variable"
                                                          :var-name "previous-line"
                                                          :from-var [{:var-name "current-line" :action-property "var-value"}]}
                                                         {:type        "set-variable"
                                                          :var-name    "current-line"
                                                          :from-params [{:param-property "line" :action-property "var-value"}]}
                                                         {:type     "case"
                                                          :options  {:box1 {:id "vera-go-line-1" :type "action"}
                                                                     :box2 {:id "vera-go-line-2" :type "action"}
                                                                     :box3 {:id "vera-go-line-3" :type "action"}}
                                                          :from-var [{:var-name "current-line" :action-property "value"}]}]}

                        :vera-go-line-1          {:type     "test-value"
                                                  :from-var [{:var-name "previous-line" :action-property "value2"}]
                                                  :value1   "box1"
                                                  :success  "stay-on-line"
                                                  :fail     "go-to-box1-line"}

                        :vera-go-line-2          {:type     "test-value"
                                                  :from-var [{:var-name "previous-line" :action-property "value2"}]
                                                  :value1   "box2"
                                                  :success  "stay-on-line"
                                                  :fail     "go-to-box2-line"}

                        :vera-go-line-3          {:type     "test-value"
                                                  :from-var [{:var-name "previous-line" :action-property "value2"}]
                                                  :value1   "box3"
                                                  :success  "stay-on-line"
                                                  :fail     "go-to-box3-line"}

                        :go-to-box1-line         {:type "sequence-data"
                                                  :data [{:type "set-variable" :var-name "current-line-pos" :var-value {:y 675 :duration 0.5}}
                                                         {:type          "transition"
                                                          :from-var      [{:var-name "current-line-pos" :action-property "to"}]
                                                          :transition-id "vera-group"}]}
                        :go-to-box2-line         {:type "sequence-data"
                                                  :data [{:type "set-variable" :var-name "current-line-pos" :var-value {:y 775 :duration 0.5}}
                                                         {:type          "transition"
                                                          :from-var      [{:var-name "current-line-pos" :action-property "to"}]
                                                          :transition-id "vera-group"}]}
                        :go-to-box3-line         {:type "sequence-data"
                                                  :data [{:type "set-variable" :var-name "current-line-pos" :var-value {:y 895 :duration 0.5}}
                                                         {:type          "transition"
                                                          :from-var      [{:var-name "current-line-pos" :action-property "to"}]
                                                          :transition-id "vera-group"}]}

                        :init-vars               {:type "parallel"
                                                  :data [{:type "set-variable" :var-name "game-finished" :var-value false}
                                                         {:type "set-variable" :var-name "current-line" :var-value "box2"}
                                                         {:from        "concepts-group"
                                                          :type        "lesson-var-provider"
                                                          :limit       3
                                                          :repeat      4
                                                          :shuffled    false
                                                          :variables   ["item-1" "item-2" "item-3" "item-4" "item-5" "item-6" "item-7" "item-8"]
                                                          :provider-id "words-set"}]}

                        :init-current-concept    {:type "sequence-data"
                                                  :data [{:from        "concepts-single"
                                                          :type        "lesson-var-provider"
                                                          :limit       1
                                                          :variables   ["current-concept"]
                                                          :provider-id "current-concept-provider"}
                                                         {:type       "set-slot"
                                                          :target     "box-target"
                                                          :from-var   [{:var-name "current-concept" :var-property "image-src" :action-property "image"}]
                                                          :slot-name  "box1"
                                                          :attachment {:x 40 :scale-x 4 :scale-y 4}}
                                                         {:type      "set-attribute"
                                                          :target    "letter-target"
                                                          :from-var  [{:var-name "current-concept" :var-property "letter" :action-property "attr-value"}]
                                                          :attr-name "text"}]}

                        :check-box               {:type        "test-value"
                                                  :from-var    [{:action-property "value1" :var-name "current-concept" :var-property "letter"}]
                                                  :from-params [{:action-property "value2" :param-property "custom-data"}]
                                                  :success     "pick-correct"
                                                  :fail        "pick-wrong"}

                        :pick-correct            {:type "sequence-data"
                                                  :data [{:id "dialog-6-correct" :type "action" :return-immediately true}
                                                         {:type        "set-attribute" :attr-name "visible" :attr-value false
                                                          :from-params [{:action-property "target" :param-property "transition-name"}]}
                                                         {:type "counter-inc" :target "counter"}
                                                         {:data [{:data               [{:id "run_jump" :type "animation" :target "vera" :loop false}
                                                                                       {:id "run" :loop true :type "add-animation" :target "vera"}]
                                                                  :return-immediately true
                                                                  :type               "sequence-data"}]
                                                          :type "parallel"}]}

                        :pick-wrong              {:type "sequence-data"
                                                  :data [{:id "dialog-7-wrong" :type "action"}]}

                        :welcome                 {:type "sequence-data"
                                                  :data [{:type "action" :id "dialog-1-welcome"}]}

                        :intro                   {:type "sequence-data"
                                                  :data [{:type "action" :id "dialog-2-intro-concept"}
                                                         {:type "state" :id "highlighted" :target "letter-target"}
                                                         {:type "empty" :duration 2000}
                                                         {:type "state" :id "normal" :target "letter-target"}
                                                         {:type "action" :id "dialog-3-intro-timer"}
                                                         {:type "state" :id "highlighted" :target "timer"}
                                                         {:type "empty" :duration 2000}
                                                         {:type "state" :id "normal" :target "timer"}]}

                        :start                   {:type "sequence-data"
                                                  :data [{:type "action" :id "dialog-4-ready-go"}
                                                         {:type "action" :id "dialog-5-starting-noise"}]}

                        :emit-objects            {:type "sequence-data"
                                                  :data [{:type "action" :id "shuffle-boxes"}
                                                         {:type "parallel"
                                                          :data [{:type "action" :id "emit-object-line-1"}
                                                                 {:type "action" :id "emit-object-line-2"}
                                                                 {:type "action" :id "emit-object-line-3"}]}
                                                         {:type "empty" :duration 1500}
                                                         {:type     "test-var-scalar"
                                                          :var-name "game-finished"
                                                          :value    false
                                                          :success  "emit-objects"
                                                          :fail     "finish-activity"}]}

                        :shuffle-boxes           {:type "sequence-data"
                                                  :data [{:from      ["item-1" "item-2" "item-3" "item-4" "item-5" "item-6" "item-7" "item-8"]
                                                          :type      "vars-var-provider"
                                                          :unique    true
                                                          :from-var  [{:var-key         "concept-name"
                                                                       :var-name        "current-concept"
                                                                       :var-property    "concept-name"
                                                                       :action-property "exclude-property-values"}]
                                                          :shuffled  true
                                                          :variables ["pair-concept-1" "pair-concept-2"]}
                                                         {:from      ["current-concept" "pair-concept-1" "pair-concept-2"]
                                                          :type      "vars-var-provider"
                                                          :shuffled  true
                                                          :variables ["box1" "box2" "box3"]}]}

                        :emit-object-line-1      {:type    "test-random"
                                                  :chance  0.7
                                                  :success {:type               "create-object"
                                                            :target             "emit-group"
                                                            :root-object        "target-letter"
                                                            :return-immediately true
                                                            :on-emit            {:type "action" :id "move-emitted-letter"}
                                                            :data               {:target-letter      {:type        "group"
                                                                                                      :x           2100
                                                                                                      :y           700
                                                                                                      :custom-data ""
                                                                                                      :children    ["target-letter-text"]}
                                                                                 :target-letter-text {:type        "text"
                                                                                                      :x           0
                                                                                                      :y           0
                                                                                                      :align       "center"
                                                                                                      :fill        0x000000
                                                                                                      :font-family "Lexend Deca"
                                                                                                      :font-size   120
                                                                                                      :text        ""}}
                                                            :from-var           [{:var-name "box1" :var-property "letter" :action-property "data.target-letter.custom-data"}
                                                                                 {:var-name "box1" :var-property "letter" :action-property "data.target-letter-text.text"}]}}

                        :emit-object-line-2      {:type    "test-random"
                                                  :chance  0.7
                                                  :success {:type               "create-object"
                                                            :target             "emit-group"
                                                            :root-object        "target-letter"
                                                            :return-immediately true
                                                            :on-emit            {:type "action" :id "move-emitted-letter"}
                                                            :data               {:target-letter      {:type        "group"
                                                                                                      :x           2200
                                                                                                      :y           800
                                                                                                      :custom-data ""
                                                                                                      :children    ["target-letter-text"]}
                                                                                 :target-letter-text {:type        "text"
                                                                                                      :x           0
                                                                                                      :y           0
                                                                                                      :align       "center"
                                                                                                      :fill        0x000000
                                                                                                      :font-family "Lexend Deca"
                                                                                                      :font-size   120
                                                                                                      :text        ""}}
                                                            :from-var           [{:var-name "box2" :var-property "letter" :action-property "data.target-letter.custom-data"}
                                                                                 {:var-name "box2" :var-property "letter" :action-property "data.target-letter-text.text"}]}}

                        :emit-object-line-3      {:type    "test-random"
                                                  :chance  0.7
                                                  :success {:type               "create-object"
                                                            :target             "emit-group"
                                                            :root-object        "target-letter"
                                                            :return-immediately true
                                                            :on-emit            {:type "action" :id "move-emitted-letter"}
                                                            :data               {:target-letter      {:type        "group"
                                                                                                      :x           2300
                                                                                                      :y           930
                                                                                                      :custom-data ""
                                                                                                      :children    ["target-letter-text"]}
                                                                                 :target-letter-text {:type        "text"
                                                                                                      :x           0
                                                                                                      :y           0
                                                                                                      :align       "center"
                                                                                                      :fill        0x000000
                                                                                                      :font-family "Lexend Deca"
                                                                                                      :font-size   120
                                                                                                      :text        ""}}
                                                            :from-var           [{:var-name "box3" :var-property "letter" :action-property "data.target-letter.custom-data"}
                                                                                 {:var-name "box3" :var-property "letter" :action-property "data.target-letter-text.text"}]}}

                        :move-emitted-letter     {:type        "transition"
                                                  :from-params [{:param-property "transition", :action-property "transition-id"}]
                                                  :to          {:x -700 :duration 5}}

                        :start-scene             {:type "sequence"
                                                  :data ["start-activity"
                                                         "welcome"
                                                         "intro"
                                                         "start"
                                                         "init-vars"
                                                         "init-current-concept"
                                                         "start-timer"
                                                         "emit-objects"]}

                        :finish-game             {:type "set-variable" :var-name "game-finished" :var-value true}
                        :start-timer             {:type "timer-start" :target "timer"}
                        :stay-on-line            {:type "empty" :duration 100}
                        :stop-scene              {:type "sequence" :data ["stop-activity"]}
                        :start-activity          {:type "start-activity"}
                        :stop-activity           {:type "stop-activity"}
                        :finish-activity         {:type "finish-activity"}
                        :wait-for-box-animations {:type "empty" :duration 100}}
        :triggers      {:stop {:on "back" :action "stop-scene"} :start {:on "start" :action "start-scene"}}
        :metadata      {:autostart true}})

(defn f
  [t args]
  (assoc-in t [:objects :timer :time] (:time args)))

(core/register-template
  m
  (partial f t))