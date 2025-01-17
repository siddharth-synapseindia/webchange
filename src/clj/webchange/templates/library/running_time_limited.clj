(ns webchange.templates.library.running-time-limited
  (:require
    [webchange.templates.core :as core]
    [webchange.templates.utils.dialog :as dialog]))

(def m {:id          34
        :name        "Running (time limited)"
        :tags        ["Independent Practice"]
        :description "Users move a character around a race track filled with answer images.  Before time runs out, users must steer the character to as many correct answer options as possible while avoiding incorrect answer images."
        :lesson-sets ["concepts-group" "concepts-single"]
        :props       {:game-changer? true}
        :fields      [{:name "image-src"
                       :type "image"}
                      {:name "concept-name"
                       :type "string"}
                      {:name "letter"
                       :type "string"}]
        :version     2
        :options     {:time {:label       "Time in seconds"
                             :type        "lookup"
                             :description "Time in seconds"
                             :options     [{:name "30" :value 30}
                                           {:name "45" :value 45}
                                           {:name "60" :value 60}]}}})

(def t {:assets        [{:url "/raw/img/running-with-letters/bg_01.jpg" :type "image"}
                        {:url "/raw/img/running-with-letters/bg_02.png" :type "image"}
                        {:url "/raw/img/running-with-letters/box.png" :type "image"}]
        :objects       {:background            {:type   "carousel"
                                                :x      0
                                                :y      0
                                                :width  1920
                                                :height 1080
                                                :first  "/raw/img/running-with-letters/bg_02.png"
                                                :last   "/raw/img/running-with-letters/bg_02.png"
                                                :next   "/raw/img/running-with-letters/bg_02.png"}
                        :frame                 {:type          "rectangle"
                                                :x             676
                                                :y             64
                                                :fill          0xFFFFFF
                                                :width         568
                                                :height        152
                                                :border-radius 24}
                        :timer                 {:type           "timer"
                                                :transition     "timer"
                                                :x              1126
                                                :y              129
                                                :show-minutes   true
                                                :show-progress  true
                                                :time           60
                                                :font-size      21
                                                :thickness      12
                                                :font-weight    "normal"
                                                :font-family    "Roboto"
                                                :progress-color 0xff9000
                                                :color          0x010101
                                                :filters        [{:name "brightness" :value 0}]
                                                :actions        {:end {:on "end" :type "action" :id "finish-game"}}}
                        :target-group          {:type     "group"
                                                :x        676
                                                :y        64
                                                :children ["letter-background" "letter-target"
                                        ;           "box-target-background" "box-target"
                                                           "counter-background" "counter"]}
                        :letter-background     {:type          "rectangle"
                                                :x             40
                                                :y             24
                                                :fill          0xFF9000
                                                :width         104
                                                :height        104
                                                :border-radius 52
                                                :filters       [{:name "brightness" :value 0}]
                                                :transition    "letter-target-background"}
                        :letter-target         {:type           "text"
                                                :x              56
                                                :y              35
                                                :width          72
                                                :height         72
                                                :transition     "letter-target"
                                                :align          "center"
                                                :fill           0xFFFFFF
                                                :font-family    "Lexend Deca"
                                                :font-size      72
                                                :text           "a"
                                                :vertical-align "middle"}
                        :box-target-background {:type          "rectangle"
                                                :x             168
                                                :y             24
                                                :fill          0xECECEC
                                                :width         104
                                                :height        104
                                                :border-radius 52}
                        :box-target            {:type       "image",
                                                :x          184,
                                                :y          40,
                                                :transition "box-target"
                                                :width      72,
                                                :height     72,
                                                :src        ""}
                        :counter-background    {:type          "rectangle"
                                                :x             232
                                                :y             24
                                                :fill          0xECECEC
                                                :width         104
                                                :height        104
                                                :border-radius 52
                                                :filters       [{:name "brightness" :value 0}
                                                                {:name "glow" :outer-strength 0 :color 0xffd700}]
                                                :transition    "counter-background"}
                        :counter               {:type        "counter"
                                                :transition  "counter"
                                                :x           271
                                                :y           48,
                                                :font-family "Roboto"
                                                :font-size   48
                                                :color       0x000000}

                        :line-1                {:type    "transparent"
                                                :x       0
                                                :y       610
                                                :width   1920
                                                :height  150
                                                :actions {:click       {:id "go-line-check" :on "click" :type "action" :params {:line "box1"}}
                                                          :pointerdown {:id "go-line-check" :on "pointerdown" :type "action" :params {:line "box1"}}
                                                          :pointerover {:id "go-line-check" :on "pointerover" :type "action" :params {:line "box1"}}}}
                        :line-2                {:type    "transparent"
                                                :x       0
                                                :y       780
                                                :width   1920
                                                :height  170
                                                :actions {:click       {:id "go-line-check" :on "click" :type "action" :params {:line "box2"}}
                                                          :pointerdown {:id "go-line-check" :on "pointerdown" :type "action" :params {:line "box2"}}
                                                          :pointerover {:id "go-line-check" :on "pointerover" :type "action" :params {:line "box2"}}}}
                        :line-3                {:type    "transparent"
                                                :x       0
                                                :y       950
                                                :width   1920
                                                :height  180
                                                :actions {:click       {:id "go-line-check" :on "click" :type "action" :params {:line "box3"}}
                                                          :pointerdown {:id "go-line-check" :on "pointerdown" :type "action" :params {:line "box3"}}
                                                          :pointerover {:id "go-line-check" :on "pointerover" :type "action" :params {:line "box3"}}}}

                        :mari                  {:type        "animation"
                                                :x           1365
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
                        :vera-group            {:type       "group"
                                                :x          500
                                                :y          865
                                                :transition "vera-group"
                                                :children   ["vera" "vera-collision-test"]}
                        :emit-group            {:type "group"}
                        :vera                  {:type       "animation"
                                                :x          0
                                                :y          -55
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
                                                :start      true}
                        :vera-collision-test   {:type        "transparent"
                                                :x           150
                                                :y           -55
                                                :width       10
                                                :height      10
                                                :transition  "vera-collision-test"
                                                :collidable? true
                                                :actions     {:collide {:on "collide" :test ["#^target-letter-.*"] :type "action" :id "check-box" :pick-event-param ["custom-data" "transition-name"]}}}}
        :scene-objects [["background"]
                        ["frame"]
                        ["emit-group"]
                        ["vera-group" "mari"]
                        ["target-group" "timer" "line-1" "line-2" "line-3"]]
        :actions       {:dialog-1-welcome        {:type                 "sequence-data"
                                                  :editor-type          "dialog"
                                                  :available-activities ["highlight-target-letter", "highlight-timer" "highlight-counter"]
                                                  :concept-var          "current-concept"
                                                  :data                 [{:type "sequence-data"
                                                                          :data [{:type "empty" :duration 0}
                                                                                 {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase               "welcome"
                                                  :phrase-description   "Welcome dialog"
                                                  :dialog-track         "1 Welcome"
                                                  :skippable            true}
                        :dialog-2-intro-concept  {:type               "sequence-data"
                                                  :editor-type        "dialog"
                                                  :available-activities ["highlight-target-letter", "highlight-timer" "highlight-counter"]
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
                                                  :available-activities ["highlight-target-letter", "highlight-timer" "highlight-counter"]
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
                                                  :available-activities ["highlight-target-letter", "highlight-timer" "highlight-counter"]
                                                  :concept-var        "current-concept"
                                                  :data               [{:type "sequence-data"
                                                                        :data [{:type "empty" :duration 0}
                                                                               {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase             "ready-go"
                                                  :phrase-description "Ready-Go"
                                                  :dialog-track       "3 Start"}
                        :highlight-target-letter {:type               "transition"
                                                  :transition-id      "letter-target-background"
                                                  :return-immediately true
                                                  :from               {:brightness 0 :hue 0},
                                                  :to                 {:brightness 1 :yoyo true :duration 0.5 :repeat 5}
                                                  }
                        :highlight-counter       {:type               "transition"
                                                  :transition-id      "counter-background"
                                                  :return-immediately true
                                                  :from               {:brightness 0 :glow 0}
                                                  :to                 {:brightness 0.1 :glow 10 :yoyo true :duration 0.5 :repeat 5}
                                                  }
                        :highlight-timer         {:type               "transition"
                                                  :transition-id      "timer"
                                                  :return-immediately true
                                                  :from               {:brightness 0 :hue 0},
                                                  :to                 {:brightness 1 :yoyo true :duration 0.5 :repeat 5}
                                                  }
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
                                                  :available-activities ["highlight-target-letter", "highlight-timer" "highlight-counter"]
                                                  :concept-var        "current-concept"
                                                  :data               [{:type "sequence-data"
                                                                        :data [{:type "empty" :duration 0}
                                                                               {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase             "correct"
                                                  :phrase-description "Correct dialog"
                                                  :dialog-track       "4 Options"}
                        :dialog-7-wrong          {:type               "sequence-data"
                                                  :editor-type        "dialog"
                                                  :available-activities ["highlight-target-letter", "highlight-timer" "highlight-counter"]
                                                  :concept-var        "current-concept"
                                                  :data               [{:type "sequence-data"
                                                                        :data [{:type "empty" :duration 0}
                                                                               {:type "animation-sequence" :phrase-text "New action" :audio nil}]}]
                                                  :phrase             "wrong"
                                                  :phrase-description "Wrong dialog"
                                                  :dialog-track       "4 Options"}

                        :go-line-check           {:type        "test-value"
                                                  :fail        "go-line"
                                                  :from-var    [{:var-name "current-line" :action-property "value1"}]
                                                  :from-params [{:param-property "line" :action-property "value2"}]}
                        :go-line                 {:type "sequence-data"
                                                  :data [{:type        "set-variable"
                                                          :var-name    "current-line"
                                                          :from-params [{:param-property "line" :action-property "var-value"}]}
                                                         {:type     "case"
                                                          :options  {:box1 {:id "go-to-box1-line" :type "action"}
                                                                     :box2 {:id "go-to-box2-line" :type "action"}
                                                                     :box3 {:id "go-to-box3-line" :type "action"}}
                                                          :from-var [{:var-name "current-line" :action-property "value"}]}]}

                        :go-to-box1-line         {:type          "transition"
                                                  :to            {:y 685 :duration 0.5}
                                                  :transition-id "vera-group"}
                        :go-to-box2-line         {:type          "transition"
                                                  :to            {:y 865 :duration 0.5}
                                                  :transition-id "vera-group"}
                        :go-to-box3-line         {:type          "transition"
                                                  :to            {:y 1040 :duration 0.5}
                                                  :transition-id "vera-group"}
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
                                                         #_{:type      "set-attribute",
                                                            :target    "box-target"
                                                            :from-var  [{:var-name "current-concept" :var-property "image-src", :action-property "attr-value"}],
                                                            :attr-name "src"}
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
                                                         {:type "action" :id "dialog-3-intro-timer"}]}

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
                                                                                                      :y           685
                                                                                                      :custom-data ""
                                                                                                      :children    ["target-letter-box"
                                                                                                                    "target-letter-text"]}
                                                                                 :target-letter-box  {:type "image"
                                                                                                      :x    -95
                                                                                                      :y    -135
                                                                                                      :src  "/raw/img/running-with-letters/box.png"
                                                                                                      }
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
                                                                                                      :y           865
                                                                                                      :custom-data ""
                                                                                                      :children    ["target-letter-box"
                                                                                                                    "target-letter-text"]}
                                                                                 :target-letter-box  {:type "image"
                                                                                                      :x    -95
                                                                                                      :y    -135
                                                                                                      :src  "/raw/img/running-with-letters/box.png"
                                                                                                      }
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
                                                                                                      :y           1040
                                                                                                      :custom-data ""
                                                                                                      :children    ["target-letter-box"
                                                                                                                    "target-letter-text"]}
                                                                                 :target-letter-box  {:type "image"
                                                                                                      :x    -95
                                                                                                      :y    -135
                                                                                                      :src  "/raw/img/running-with-letters/box.png"
                                                                                                      }
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
                                                         "init-current-concept"
                                                         "welcome"
                                                         "intro"
                                                         "start"
                                                         "init-vars"
                                                         "start-timer"
                                                         "emit-objects"]}

                        :finish-game             {:type "set-variable" :var-name "game-finished" :var-value true}
                        :start-timer             {:type "timer-start" :target "timer"}
                        :stay-on-line            {:type "empty" :duration 100}
                        :stop-scene              {:type "sequence" :data ["stop-activity"]}
                        :start-activity          {:type "start-activity"}
                        :stop-activity           {:type "stop-activity"}
                        :finish-activity         {:type "sequence-data"
                                                  :data [{:type "action" :id "finish-activity-dialog"}
                                                         {:type "finish-activity"}]}
                        :finish-activity-dialog  (-> (dialog/default "Finish activity dialog")
                                                     (assoc :concept-var "current-concept")
                                                     (assoc :available-activities ["highlight-target-letter", "highlight-timer", "highlight-counter"]))
                        :wait-for-box-animations {:type "empty" :duration 100}}
        :triggers      {:stop {:on "back" :action "stop-scene"} :start {:on "start" :action "start-scene"}}
        :metadata      {:autostart true}})

(defn f
  [t args]
  (assoc-in t [:objects :timer :time] (:time args)))

(core/register-template
  m
  (partial f t))
