(ns webchange.templates.library.categorize.round-3)

(def template-round-3 {:assets        [{:url "/raw/img/categorize/01.png", :size 10, :type "image"}
                 {:url "/raw/img/categorize/02.png", :size 10, :type "image"}
                 {:url "/raw/img/categorize/03.png", :size 10, :type "image"}
                 {:url "/raw/img/categorize/yellow_box.png", :size 10, :type "image"}
                 {:url "/raw/img/categorize/blue_box.png", :size 10, :type "image"}
                 {:url "/raw/img/categorize/red_box.png", :size 10, :type "image"}
                 {:url "/raw/img/categorize/blue_crayons.png", :size 10, :type "image"}
                 {:url "/raw/img/categorize/red_crayons.png", :size 10, :type "image"}
                 {:url "/raw/img/categorize/yellow_crayons.png", :size 10, :type "image"}],
 :objects       {:layered-background {:type       "layered-background",
                                      :background {:src "/raw/img/categorize/01.png"},
                                      :decoration {:src "/raw/img/categorize/04.png"},
                                      :surface    {:src "/raw/img/categorize/02.png"}
                                      }
                 :yellow-group       {
                                      :type       "group",
                                      :children   [],
                                      :draggable  true,
                                      :transition "yellow-group",
                                      :states     {:group-all {:children ["yellow-box" "yellow-crayon"]}
                                                   :ungroup   {:children []}
                                                   :hidden    {:visible false}},

                                      :actions    {:drag-start {
                                                                :type   "action",
                                                                :on     "drag-start",
                                                                :id     "start-drag",
                                                                :params {:colliders ["blue-box" "red-box"
                                                                                     "purple-table" "orange-table"
                                                                                     "green-table"]
                                                                         :self      "yellow-group"
                                                                         :crayon    "yellow-crayon"
                                                                         }}
                                                   :drag-end   {
                                                                :id     "stop-drag-hide",
                                                                :on     "drag-end",
                                                                :type   "action",
                                                                :params {
                                                                         :colliders     ["blue-box" "red-box"
                                                                                         "purple-table" "orange-table"
                                                                                         "green-table"]
                                                                         :self          "yellow-group"
                                                                         :target        "purple-table"
                                                                         :crayon        "yellow-crayon"
                                                                         :init-position {:x        100,
                                                                                         :y        600,
                                                                                         :duration 1}}}},

                                      }
                 :yellow-group-1     {
                                      :type       "group",
                                      :children   [],
                                      :draggable  true,
                                      :transition "yellow-group-1",
                                      :states     {:group-all {:children ["yellow-box" "yellow-crayon"]}
                                                   :ungroup   {:children []}
                                                   :hidden    {:visible false}},

                                      :actions    {:drag-start {
                                                                :type   "action",
                                                                :on     "drag-start",
                                                                :id     "start-drag",
                                                                :params {:colliders ["blue-box" "red-box"
                                                                                     "purple-table" "orange-table"
                                                                                     "green-table"]
                                                                         :self      "yellow-group-1"
                                                                         :crayon    "yellow-crayon"
                                                                         }}
                                                   :drag-end   {
                                                                :id     "stop-drag-hide",
                                                                :on     "drag-end",
                                                                :type   "action",
                                                                :params {
                                                                         :colliders     ["blue-box" "red-box"
                                                                                         "purple-table" "orange-table"
                                                                                         "green-table"]
                                                                         :self          "yellow-group"
                                                                         :target        "green-table"
                                                                         :crayon        "yellow-crayon"
                                                                         :init-position {:x        100,
                                                                                         :y        600,
                                                                                         :duration 1}}}},

                                      }
                 :blue-group         {
                                      :type       "group",
                                      :children   [],
                                      :draggable  true,
                                      :transition "blue-group",
                                      :states     {:group-all {:children ["blue-box" "blue-crayon"]}
                                                   :ungroup   {:children []}
                                                   :hidden    {:visible false}},

                                      :actions    {:drag-start {
                                                                :type   "action",
                                                                :on     "drag-start",
                                                                :id     "start-drag",
                                                                :params {:colliders ["yellow-box" "red-box"
                                                                                     "purple-table" "orange-table"
                                                                                     "green-table"]
                                                                         :self      "blue-group"}}
                                                   :drag-end   {
                                                                :id     "stop-drag-hide",
                                                                :on     "drag-end",
                                                                :type   "action",
                                                                :params {
                                                                         :colliders     ["blue-box" "red-box"
                                                                                         "purple-table" "orange-table"
                                                                                         "green-table"]
                                                                         :self          "blue-group"
                                                                         :target        "green-table"
                                                                         :init-position {:x        100,
                                                                                         :y        600,
                                                                                         :duration 1}}}},
                                      }
                 :red-group          {
                                      :type       "group",
                                      :children   [],
                                      :draggable  true,
                                      :transition "red-group",
                                      :states     {:group-all {:children ["red-box" "red-crayon"]}
                                                   :ungroup   {:children []}
                                                   :hidden    {:visible false}},

                                      :actions    {:drag-start {
                                                                :type   "action",
                                                                :on     "drag-start",
                                                                :id     "start-drag",
                                                                :params {:colliders ["blue-box" "yellow-box"
                                                                                     "purple-table" "orange-table"
                                                                                     "green-table"]
                                                                         :self      "red-group"}}
                                                   :drag-end   {
                                                                :id     "stop-drag-hide",
                                                                :on     "drag-end",
                                                                :type   "action",
                                                                :params {
                                                                         :colliders     ["blue-box" "red-box"
                                                                                         "purple-table" "orange-table"
                                                                                         "green-table"]
                                                                         :self          "red-group"
                                                                         :target        "orange-table"
                                                                         :init-position {:x        100,
                                                                                         :y        600,
                                                                                         :duration 1}}}},
                                      }

                 :yellow-box
                                     {:type       "image",
                                      :x          786,
                                      :y          874,
                                      :width      428,
                                      :height     549,
                                      :scale      0.5,
                                      :src        "/raw/img/categorize/yellow_box.png",
                                      :transition "yellow-box",
                                      :states     {:highlighted   {:highlight true} :not-highlighted {:highlight false}
                                                   :init-position {:x 786, :y 874, :visible true}
                                                   },
                                      },
                 :blue-box           {:type       "image",
                                      :x          320,
                                      :y          874,
                                      :width      428,
                                      :height     549,
                                      :scale      0.5,
                                      :transition "blue-box",
                                      :src        "/raw/img/categorize/blue_box.png",
                                      :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}},
                                      }
                 :red-box            {:type       "image",
                                      :x          1252,
                                      :y          874,
                                      :width      428,
                                      :height     549,
                                      :scale      0.5,
                                      :transition "red-box",
                                      :src        "/raw/img/categorize/red_box.png",
                                      :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}},
                                      },
                 :red-crayon         {
                                      :type       "image",
                                      :x          100,
                                      :y          600,
                                      :width      100,
                                      :height     224,
                                      :rotation   -90,
                                      :scale      0.35,
                                      :src        "/raw/img/categorize/red_crayons.png",
                                      :transition "red-crayon",
                                      :states     {:highlighted   {:highlight true} :not-highlighted {:highlight false}
                                                   :hidden        {:visible false}, :init-position {:x 100, :y 600,}
                                                   :not-draggable {:draggable false}, :draggable {:draggable true}
                                                   },
                                      :draggable  true,
                                      :actions    {:drag-start {
                                                                :type   "action",
                                                                :on     "drag-start",
                                                                :id     "start-drag",
                                                                :params {:colliders ["yellow-box" "blue-box" "red-box"
                                                                                     "purple-table" "orange-table"
                                                                                     "green-table"]
                                                                         :self      "red-crayon"}}
                                                   :drag-end   {
                                                                :id     "stop-drag-hide",
                                                                :on     "drag-end",
                                                                :type   "action",
                                                                :params {
                                                                         :colliders     ["yellow-box" "blue-box" "red-box"
                                                                                         "purple-table" "orange-table"
                                                                                         "green-table"]
                                                                         :self          "red-crayon"
                                                                         :target        "red-box"
                                                                         :init-position {:x        100,
                                                                                         :y        600,
                                                                                         :duration 1}}}},},
                 :purple-crayon      {
                                      :type       "image",
                                      :x          200,
                                      :y          700,
                                      :width      100,
                                      :height     224,
                                      :rotation   -90,
                                      :scale      0.35,
                                      :src        "/raw/img/categorize/purple_crayons.png",
                                      :transition "purple-crayon",
                                      :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}
                                                   :hidden      {:visible false}, :init-position {:x 200,
                                                                                                  :y 700}
                                                   },
                                      :draggable  true,
                                      :actions    {:drag-start {
                                                                :type   "action",
                                                                :on     "drag-start",
                                                                :id     "start-drag",
                                                                :params {:colliders ["yellow-box" "blue-box" "red-box"
                                                                                     "purple-table" "orange-table"
                                                                                     "green-table"]
                                                                         :self      "purple-crayon"}}
                                                   :drag-end   {
                                                                :id     "stop-drag-hide",
                                                                :on     "drag-end",
                                                                :type   "action",
                                                                :params {
                                                                         :colliders     ["yellow-box" "blue-box" "red-box"
                                                                                         "purple-table" "orange-table"
                                                                                         "green-table"]
                                                                         :self          "purple-crayon"
                                                                         :target        "purple-table"
                                                                         :init-position {:x        200,
                                                                                         :y        700,
                                                                                         :duration 1}}}}
                                      },
                 :yellow-crayon      {
                                      :type       "image",
                                      :x          150,
                                      :y          800,
                                      :width      100,
                                      :height     224,
                                      :rotation   -90,
                                      :scale      0.35,
                                      :src        "/raw/img/categorize/yellow_crayons.png",
                                      :transition "yellow-crayon",
                                      :states     {:highlighted   {:highlight true} :not-highlighted {:highlight false}
                                                   :hidden        {:visible false}, :init-position {:x 150, :y 800, :visible true}
                                                   :not-draggable {:draggable false}, :draggable {:draggable true}
                                                   },
                                      :draggable  true,
                                      :actions    {:drag-start {
                                                                :type   "action",
                                                                :on     "drag-start",
                                                                :id     "start-drag",
                                                                :params {:colliders ["yellow-box" "blue-box" "red-box"
                                                                                     "purple-table" "orange-table"
                                                                                     "green-table"]
                                                                         :self      "yellow-crayon"}}
                                                   :drag-end   {
                                                                :id     "stop-drag-hide",
                                                                :on     "drag-end",
                                                                :type   "action",
                                                                :params {
                                                                         :colliders     ["yellow-box" "blue-box" "red-box"
                                                                                         "purple-table" "orange-table"
                                                                                         "green-table"]
                                                                         :self          "yellow-crayon"
                                                                         :target        "yellow-box"
                                                                         :init-position {:x        150,
                                                                                         :y        800,
                                                                                         :duration 1}}}}
                                      },
                 :green-crayon       {
                                      :type       "image",
                                      :x          550,
                                      :y          800,
                                      :width      100,
                                      :height     224,
                                      :rotation   -90,
                                      :scale      0.35,
                                      :src        "/raw/img/categorize/green_crayons.png",
                                      :transition "green-crayon",
                                      :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}
                                                   :hidden      {:visible false}, :init-position {:x 550,
                                                                                                  :y 800,}
                                                   },
                                      :draggable  true,
                                      :actions    {:drag-start {
                                                                :type   "action",
                                                                :on     "drag-start",
                                                                :id     "start-drag",
                                                                :params {:colliders ["yellow-box" "blue-box" "red-box"
                                                                                     "purple-table" "orange-table"
                                                                                     "green-table"]
                                                                         :self      "green-crayon"}}
                                                   :drag-end   {
                                                                :id     "stop-drag-hide",
                                                                :on     "drag-end",
                                                                :type   "action",
                                                                :params {
                                                                         :colliders     ["yellow-box" "blue-box" "red-box"
                                                                                         "purple-table" "orange-table"
                                                                                         "green-table"]
                                                                         :self          "green-crayon"
                                                                         :target        "green-table"
                                                                         :init-position {:x        550,
                                                                                         :y        800,
                                                                                         :duration 1}}}}
                                      },
                 :blue-crayon        {
                                      :type       "image",
                                      :x          46,
                                      :y          650,
                                      :width      100,
                                      :height     224,
                                      :rotation   -90,
                                      :scale      0.35,
                                      :src        "/raw/img/categorize/blue_crayons.png",
                                      :transition "blue-crayon",
                                      :states     {:highlighted   {:highlight true} :not-highlighted {:highlight false}
                                                   :hidden        {:visible false}, :init-position {:x 46, :y 650,}
                                                   :not-draggable {:draggable false}, :draggable {:draggable true}
                                                   },
                                      :draggable  true,
                                      :actions    {:drag-start {
                                                                :type   "action",
                                                                :on     "drag-start",
                                                                :id     "start-drag",
                                                                :params {:colliders ["yellow-box" "blue-box" "red-box"
                                                                                     "purple-table" "orange-table"
                                                                                     "green-table"]
                                                                         :self      "blue-crayon"}}
                                                   :drag-end   {
                                                                :id     "stop-drag-hide",
                                                                :on     "drag-end",
                                                                :type   "action",
                                                                :params {
                                                                         :colliders     ["yellow-box" "blue-box" "red-box"
                                                                                         "purple-table" "orange-table"
                                                                                         "green-table"]
                                                                         :self          "blue-crayon"
                                                                         :target        "blue-box"
                                                                         :init-position {:x        46,
                                                                                         :y        650,
                                                                                         :duration 1}}}}
                                      },
                 :orange-crayon      {
                                      :type       "image",
                                      :x          350,
                                      :y          800,
                                      :width      100,
                                      :height     224,
                                      :rotation   -90,
                                      :scale      0.35,
                                      :src        "/raw/img/categorize/orange_crayons.png",
                                      :transition "orange-crayon",
                                      :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}
                                                   :hidden      {:visible false}, :init-position {:x 350,
                                                                                                  :y 800,}
                                                   },
                                      :draggable  true,
                                      :actions    {:drag-start {
                                                                :type   "action",
                                                                :on     "drag-start",
                                                                :id     "start-drag",
                                                                :params {:colliders ["yellow-box" "blue-box" "red-box"
                                                                                     "purple-table" "orange-table"
                                                                                     "green-table"]
                                                                         :self      "orange-crayon"}}
                                                   :drag-end   {
                                                                :id     "stop-drag-hide",
                                                                :on     "drag-end",
                                                                :type   "action",
                                                                :params {
                                                                         :colliders     ["yellow-box" "blue-box" "red-box"
                                                                                         "purple-table" "orange-table"
                                                                                         "green-table"]
                                                                         :self          "orange-crayon"
                                                                         :target        "orange-table"
                                                                         :init-position {:x        350,
                                                                                         :y        800,
                                                                                         :duration 1}}}}
                                      },
                 :purple-table
                                     {:type       "image",
                                      :x          286,
                                      :y          374,
                                      :width      428,
                                      :height     549,
                                      :scale      0.5,
                                      :src        "/raw/img/categorize/purple_table.png",
                                      :transition "purple-table",
                                      :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}},
                                      }
                 :green-table
                                     {:type       "image",
                                      :x          756,
                                      :y          374,
                                      :width      428,
                                      :height     549,
                                      :scale      0.5,
                                      :src        "/raw/img/categorize/green_table.png",
                                      :transition "green-table",
                                      :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}},
                                      }
                 :orange-table
                                     {:type       "image",
                                      :x          1086,
                                      :y          374,
                                      :width      428,
                                      :height     549,
                                      :scale      0.5,
                                      :src        "/raw/img/categorize/orange_table.png",
                                      :transition "orange-table",
                                      :states     {:highlighted {:highlight true} :not-highlighted {:highlight false}},
                                      }

                 },

 :scene-objects [["layered-background"]
                 ["yellow-box" "blue-box" "red-box"]
                 ["purple-table" "orange-table" "green-table"]
                 ["red-crayon" "purple-crayon" "yellow-crayon" "green-crayon" "blue-crayon" "orange-crayon"]
                 ["yellow-group-1" "yellow-group" "red-group" "blue-group"]
                 ],
 :actions       {
                 :object-revert               {:type        "state",
                                               :id          "init-position"
                                               :from-params [{:action-property "target" :param-property "self"}]
                                               }
                 ;{:type        "transition",
                 ; :from-params [{:action-property "transition-id" :param-property "self"}
                 ;               {:action-property "to" :param-property "init-position"}]
                 ; }
                 :correct-answer-group        {:type "sequence-data"
                                               :data [
                                                      {
                                                       :id       "group-all",
                                                       :type     "state",
                                                       :from-var [{:action-property "target" :var-name "group-name"}]
                                                       }
                                                      {:type        "state"
                                                       :id          "not-draggable"
                                                       :from-params [{:action-property "target" :param-property "self"}]}
                                                      {:type "action"
                                                       :id   "correct-answer-dialog"
                                                       }
                                                      {:type     "action"
                                                       :from-var [{:var-name "next-task", :action-property "id"}]
                                                       }
                                                      ]
                                               }
                 :correct-answer-single       {:type "sequence-data",
                                               :data [
                                                      {:type        "state",
                                                       :id          "init-position"
                                                       :from-params [{:action-property "target" :param-property "self"}]
                                                       }
                                                      ;{:type        "state"
                                                      ; :id          "hidden"
                                                      ; :from-params [{:action-property "target" :param-property "self"}]}
                                                      {:type "action"
                                                       :id   "correct-answer-dialog"
                                                       }
                                                      {:type     "action"
                                                       :from-var [{:var-name "next-task", :action-property "id"}]
                                                       }
                                                      ]
                                               },
                 :correct-answer-init-ungroup {:type "sequence-data",
                                               :data [
                                                      {
                                                       :id       "ungroup",
                                                       :type     "state",
                                                       :from-var [{:action-property "target" :var-name "group-name"}]
                                                       }
                                                      {:type     "state"
                                                       :id       "init-position"
                                                       :from-var [{:var-name "ungroup-object-1", :action-property "target"}]
                                                       }
                                                      {:type     "state"
                                                       :id       "init-position"
                                                       :from-var [{:var-name "ungroup-object-2", :action-property "target"}]
                                                       }
                                                      {:type        "state"
                                                       :id          "draggable"
                                                       :from-params [{:action-property "target" :param-property "crayon"}]}
                                                      {:type "action"
                                                       :id   "correct-answer-dialog"
                                                       }
                                                      {:type     "action"
                                                       :from-var [{:var-name "next-task", :action-property "id"}]
                                                       }

                                                      ]
                                               },
                 :blink-objects               {:type "sequence-data"
                                               :data [
                                                      {:type     "state"
                                                       :id       "highlighted"
                                                       :from-var [{:var-name "object-1", :action-property "target"}]
                                                       }
                                                      {:type     "state"
                                                       :id       "highlighted"
                                                       :from-var [{:var-name "object-2", :action-property "target"}]
                                                       }
                                                      {:type "empty", :duration 2000}
                                                      {:type     "state"
                                                       :id       "not-highlighted"
                                                       :from-var [{:var-name "object-1", :action-property "target"}]
                                                       }
                                                      {:type     "state"
                                                       :id       "not-highlighted"
                                                       :from-var [{:var-name "object-2", :action-property "target"}]
                                                       }
                                                      ]
                                               },
                 :wrong-answer                {:type "sequence-data"
                                               :data [{:type "action"
                                                       :id   "wrong-answer-dialog"}
                                                      {:type "action", :id "object-revert"}
                                                      {:type           "counter"
                                                       :counter-action "increase"
                                                       :counter-id     "wrong-answers-counter"}
                                                      {:type       "test-var-inequality"
                                                       :var-name   "wrong-answers-counter",
                                                       :value      2,
                                                       :inequality ">=",
                                                       :success    "blink-objects"
                                                       }
                                                      ]}
                 :check-answer                {:type     "test-var-list",
                                               :fail     "wrong-answer",
                                               :values   [true true],
                                               :from-var [{:var-name "check-collide", :action-property "var-names"}
                                                          {:var-name "correct-answer", :action-property "success"}]
                                               }
                 :stop-drag-hide              {:type "sequence-data"
                                               :data [
                                                      {:type        "test-var-list-at-least-one-true",
                                                       :success     "check-answer",
                                                       :fail        "object-revert"
                                                       :from-params [{:template        "colliding-%"
                                                                      :action-property "var-names" :param-property "colliders"}]}
                                                      {:type "remove-interval"
                                                       :id   "check-collide-3"}
                                                      {
                                                       :type        "mass-state"
                                                       :id          "not-highlighted"
                                                       :from-params [{:action-property "targets" :param-property "colliders"}]
                                                       }

                                                      ]

                                               },

                 :start-drag                  {:type "sequence-data"
                                               :data [
                                                      {:type     "set-variable-list"
                                                       :values   [false false],
                                                       :from-var [{:var-name "check-collide", :action-property "var-names"}]
                                                       }
                                                      {:type        "set-variable",
                                                       :var-value   true
                                                       :from-params [{
                                                                      :template       "colliding-object-%",
                                                                      :param-property "self", :action-property "var-name"}]
                                                       }
                                                      {:type     "set-interval"
                                                       :id       "check-collide-3"
                                                       :interval 100
                                                       :action   "check-collide"}]},
                 :check-collide               {:type "sequence-data"
                                               :data [
                                                      {:type        "test-transitions-and-pointer-collide",
                                                       :success     "highlight",
                                                       :fail        "unhighlight",
                                                       :from-params [{:param-property "colliders", :action-property "transitions"}]}]}
                 :highlight                   {:type "sequence-data"
                                               :data [{:type        "set-variable",
                                                       :var-value   true
                                                       :from-params [{:action-property "var-name",
                                                                      :template        "colliding-%",
                                                                      :param-property  "transition"}]
                                                       }
                                                      {:type        "state"
                                                       :id          "highlighted"
                                                       :from-params [{:action-property "target" :param-property "transition"}]
                                                       }]
                                               }
                 :unhighlight                 {:type "sequence-data"
                                               :data [
                                                      {:type        "set-variable",
                                                       :var-value   false
                                                       :from-params [{:action-property "var-name",
                                                                      :template        "colliding-%",
                                                                      :param-property  "transition"}]
                                                       }
                                                      {:type        "state"
                                                       :id          "not-highlighted"
                                                       :from-params [{:action-property "target" :param-property "transition"}]}]}

                 :intro                       {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "continue-sorting",
                                               :phrase-description "You are doing such a wonderful job of helping me clean up AND sorting colors! But I need your help one more time. This time, I will call out an item and tell you where to put it. Listen carefully, because it might be a different place than where you put it before!"
                                               ;:dialog-track       "Intro and finish"
                                               }
                 :instruction-1               {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-1",
                                               :phrase-description "Put the red crayon in its crayon box."
                                               ;:dialog-track       "First 6 tasks"
                                               }
                 :instruction-2               {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-2",
                                               :phrase-description "Put the purple crayon on its table."
                                               ;:dialog-track       "First 6 tasks"
                                               }
                 :instruction-3               {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-3",
                                               :phrase-description "Put the yellow crayon in its crayon box."
                                               ;:dialog-track       "First 6 tasks"
                                               }
                 :instruction-4               {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-4",
                                               :phrase-description "Put the green crayon on its table."
                                               ;:dialog-track       "First 6 tasks"
                                               }
                 :instruction-5               {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-5",
                                               :phrase-description "Put the blue crayon in its crayon box."
                                               ;:dialog-track       "First 6 tasks"
                                               }
                 :instruction-6               {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-6",
                                               :phrase-description "Put the orange crayon on its table."
                                               ;:dialog-track       "First 6 tasks"
                                               }
                 :instruction-7-1             {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-7-1",
                                               :phrase-description "Put the yellow crayon in its crayon box; then put the whole crayon box on the purple table."
                                               :dialog-track       "Second 4 tasks"
                                               }
                 :instruction-7-2             {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-7-2",
                                               :phrase-description "Before second part of first task"
                                               :dialog-track       "Second 4 tasks"
                                               }
                 :instruction-8-1             {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-8-1",
                                               :phrase-description "Put the blue crayon in its crayon box; then put the whole crayon box on the green table."
                                               :dialog-track       "Second 4 tasks"
                                               }
                 :instruction-8-2             {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-8-2",
                                               :phrase-description "Before second part of second task"
                                               :dialog-track       "Second 4 tasks"
                                               }
                 :instruction-9-1             {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-9-1",
                                               :phrase-description "Put the red crayon in its crayon box; then put the whole crayon box on the orange table."
                                               :dialog-track       "Second 4 tasks"
                                               }
                 :instruction-9-2             {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-9-2",
                                               :phrase-description "Before second part of third task"
                                               :dialog-track       "Second 4 tasks"
                                               }
                 :instruction-10-1            {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-10-1",
                                               :phrase-description "Put the yellow crayon in its crayon box; then put the whole crayon box on the green table."
                                               :dialog-track       "Second 4 tasks"
                                               }
                 :instruction-10-2            {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "instruction-10-2",
                                               :phrase-description "Before second part of fourth task"
                                               :dialog-track       "Second 4 tasks"
                                               }
                 :stop-activity               {:type "stop-activity", :id "categorize"},
                 :finish                      {:type "sequence-data",
                                               :data [
                                                      {:type "action"
                                                       :id   "finish-dialog"
                                                       }
                                                      {:type "action"
                                                       :id   "stop-activity"
                                                       }
                                                      ]
                                               }
                 :finish-dialog               {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "Finish dialog",
                                               :phrase-description "Finish dialog"
                                               }

                 :wrong-answer-dialog         {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "wrong-answer-dialog",
                                               :phrase-description "Wrong answer dialog"
                                               }
                 :correct-answer-dialog       {:type               "sequence-data",
                                               :editor-type        "dialog",
                                               :data               [{:type "sequence-data"
                                                                     :data [{:type "empty" :duration 0}
                                                                            {:type "animation-sequence", :phrase-text "New action", :audio nil}]}],
                                               :phrase             "correct-answer-dialog",
                                               :phrase-description "Correct answer dialog"
                                               }
                 :task-2                      {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "purple-crayon"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "purple-table"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-purple-crayon" "colliding-purple-table"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-3"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-single"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-2"}]}
                 :task-3                      {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "yellow-crayon"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "yellow-box"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-yellow-crayon" "colliding-yellow-box"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-4"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-single"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-3"}]
                                               }
                 :task-4                      {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "green-crayon"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "green-table"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-green-crayon" "colliding-green-table"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-5"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-single"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-4"}]
                                               }
                 :task-5                      {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "blue-crayon"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "blue-box"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-blue-crayon" "colliding-blue-box"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-6"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-single"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-5"}]
                                               }
                 :task-6                      {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "orange-crayon"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "orange-table"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-orange-crayon" "colliding-orange-table"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-7-1"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-single"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-6"}]
                                               }
                 :task-7-1                    {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "yellow-crayon"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "yellow-box"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-yellow-crayon" "colliding-yellow-box"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-7-2"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-group"}
                                                      {:type "set-variable", :var-name "group-name", :var-value "yellow-group"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-7-1"}]
                                               }
                 :task-7-2                    {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "yellow-group"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "purple-table"}
                                                      {:type "set-variable", :var-name "ungroup-object-1", :var-value "yellow-box"}
                                                      {:type "set-variable", :var-name "ungroup-object-2", :var-value "yellow-crayon"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-yellow-group" "colliding-purple-table"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-8-1"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-init-ungroup"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-7-2"}]}
                 :task-8-1                    {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "blue-crayon"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "blue-box"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-blue-crayon" "colliding-blue-box"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-8-2"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-group"}
                                                      {:type "set-variable", :var-name "group-name", :var-value "blue-group"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-8-1"}]}
                 :task-8-2                    {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "blue-group"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "green-table"}
                                                      {:type "set-variable", :var-name "ungroup-object-1", :var-value "blue-box"}
                                                      {:type "set-variable", :var-name "ungroup-object-2", :var-value "blue-crayon"}

                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-blue-group" "colliding-green-table"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-9-1"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-init-ungroup"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-8-2"}]
                                               }
                 :task-9-1                    {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "red-crayon"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "red-box"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-red-crayon" "colliding-red-box"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-9-2"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-group"}
                                                      {:type "set-variable", :var-name "group-name", :var-value "red-group"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-9-1"}]
                                               }
                 :task-9-2                    {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "red-group"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "orange-table"}
                                                      {:type "set-variable", :var-name "ungroup-object-1", :var-value "red-box"}
                                                      {:type "set-variable", :var-name "ungroup-object-2", :var-value "red-crayon"}

                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-red-group" "colliding-orange-table"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-10-1"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-init-ungroup"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-9-2"}]
                                               }
                 :task-10-1                   {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "yellow-crayon"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "yellow-box"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-yellow-crayon" "colliding-yellow-box"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-10-2"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-group"}
                                                      {:type "set-variable", :var-name "group-name", :var-value "yellow-group-1"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-10-1"}]
                                               }

                 :task-10-2                   {
                                               :type "sequence-data",
                                               :data [
                                                      {:type "set-variable", :var-name "object-1", :var-value "yellow-group-1"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "green-table"}
                                                      {:type "set-variable", :var-name "ungroup-object-1", :var-value "yellow-box"}
                                                      {:type "set-variable", :var-name "ungroup-object-2", :var-value "yellow-crayon"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-yellow-group-1" "colliding-green-table"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "finish"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-init-ungroup"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-10-2"}]
                                               }

                 :start-activity              {:type "sequence-data",
                                               :data [{:type "action", :id "intro"}
                                                      {:type "set-variable", :var-name "object-1", :var-value "red-crayon"}
                                                      {:type "set-variable", :var-name "object-2", :var-value "red-box"}
                                                      {:type "set-variable", :var-name "check-collide", :var-value ["colliding-object-red-crayon" "colliding-red-box"]}
                                                      {:type "set-variable", :var-name "next-task", :var-value "task-2"}
                                                      {:type "set-variable", :var-name "correct-answer", :var-value "correct-answer-single"}
                                                      {:type "counter" :counter-action "reset" :counter-id "wrong-answers-counter"}
                                                      {:type "action", :id "instruction-1"}
                                                      ]},
                 }


 :triggers      {:start {:on "start", :action "start-activity"}},
 :metadata      {:autostart true
                 :tracks    [
                             {:title "Round 3 - Intro and finish"
                              :nodes [{:type      "dialog"
                                       :action-id :intro}
                                      {:type      "dialog"
                                       :action-id :finish-dialog}
                                      ]}
                             {:title "Round 3 - Action result"
                              :nodes [{:type      "dialog"
                                       :action-id :wrong-answer-dialog}
                                      {:type      "dialog"
                                       :action-id :correct-answer-dialog}
                                      ]}
                             {:title "Round 3 - First 6 tasks"
                              :nodes [{:type "prompt"
                                       :text "Put the red crayon in its crayon box."}
                                      {:type      "dialog"
                                       :action-id :instruction-1}
                                      {:type "prompt"
                                       :text "Put the purple crayon on its table."}
                                      {:type      "dialog"
                                       :action-id :instruction-2}
                                      {:type "prompt"
                                       :text "Put the yellow crayon in its crayon box."}
                                      {:type      "dialog"
                                       :action-id :instruction-3}
                                      {:type "prompt"
                                       :text "Put the green crayon on its table."}
                                      {:type      "dialog"
                                       :action-id :instruction-4}
                                      {:type "prompt"
                                       :text "Put the blue crayon on its table."}
                                      {:type      "dialog"
                                       :action-id :instruction-5}
                                      {:type "prompt"
                                       :text "Put the orange crayon on its table."}
                                      {:type      "dialog"
                                       :action-id :instruction-6}
                                      ]}
                             {:title "Round 3 - Second 4 tasks"
                              :nodes [{:type "prompt"
                                       :text "Put the yellow crayon in its crayon box"}
                                      {:type      "dialog"
                                       :action-id :instruction-7-1}
                                      {:type "prompt"
                                       :text "then put the whole crayon box on the purple table."}
                                      {:type      "dialog"
                                       :action-id :instruction-7-2}
                                      {:type "prompt"
                                       :text "Put the blue crayon in its crayon box"}
                                      {:type      "dialog"
                                       :action-id :instruction-8-1}
                                      {:type "prompt"
                                       :text "then put the whole crayon box on the green table."}
                                      {:type      "dialog"
                                       :action-id :instruction-8-2}
                                      {:type "prompt"
                                       :text "Put the red crayon in its crayon box"}
                                      {:type      "dialog"
                                       :action-id :instruction-9-1}
                                      {:type "prompt"
                                       :text "then put the whole crayon box on the orange table."}
                                      {:type      "dialog"
                                       :action-id :instruction-9-2}
                                      {:type "prompt"
                                       :text "Put the yellow crayon in its crayon box"}
                                      {:type      "dialog"
                                       :action-id :instruction-10-1}
                                      {:type "prompt"
                                       :text "then put the whole crayon box on the green table."}
                                      {:type      "dialog"
                                       :action-id :instruction-10-2}
                                      ]
                              }
                             ]
                 },
 })