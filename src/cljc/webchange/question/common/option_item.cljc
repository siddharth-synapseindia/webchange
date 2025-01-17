(ns webchange.question.common.option-item
  (:require
    [webchange.question.common.params :as common-params]
    [webchange.question.common.voice-over-button :as voice-over]
    [webchange.question.params :as p]
    [webchange.question.utils :refer [merge-data]]))

(defn- create-image
  [{:keys [object-name x y width height border-radius src]
    :or   {x             0
           y             0
           border-radius 0}}]
  (let [image-name (str object-name "-image")]
    {:objects {(keyword object-name) {:type        "group"
                                      :object-name object-name
                                      :x           x
                                      :y           y
                                      :children    [image-name]}
               (keyword image-name)  {:type          "image"
                                      :src           src
                                      :x             (/ width 2)
                                      :y             (/ height 2)
                                      :width         (- width (* p/option-padding 2))
                                      :height        (- height (* p/option-padding 2))
                                      :border-radius border-radius
                                      :image-size    "contain"
                                      :origin        {:type "center-center"}
                                      :editable?     {:select true}}}
     :assets  [{:url  src
                :size 1
                :type "image"}]}))

(defn- create-substrate
  [{:keys [object-name x y width height border-radius question-id value actions]}]
  {:pre [(string? object-name) (string? question-id) (string? value)]}
  (let [{:keys [background-color border-color border-width]} common-params/option
        {default-color :default active-color :active} border-color
        action-activate-name (str object-name "-activate")
        action-inactivate-name (str object-name "-inactivate")]
    {:objects {(keyword object-name) (cond-> {:type          "rectangle"
                                              :x             x
                                              :y             y
                                              :width         width
                                              :height        height
                                              :border-radius border-radius
                                              :border-width  border-width
                                              :border-color  default-color
                                              :states        {:default {:border-color default-color}
                                                              :active  {:border-color active-color}}
                                              :fill          background-color}
                                             (some? actions) (assoc :actions actions))}
     :actions {(keyword action-activate-name)   {:type   "state"
                                                 :target object-name
                                                 :id     "active"
                                                 :tags   [(str "activate-option-" value "-" question-id)]}
               (keyword action-inactivate-name) {:type   "state"
                                                 :target object-name
                                                 :id     "default"
                                                 :tags   [(str "inactivate-options-" question-id)
                                                          (str "inactivate-option-" value "-" question-id)]}}}))

(defn- create-text
  [{:keys [object-name x y width height text actions]}]
  {:objects {(keyword object-name) (cond-> {:type           "text"
                                            :text           text
                                            :x              x
                                            :y              (+ y (/ height 2))
                                            :width          width
                                            :word-wrap      true
                                            :font-size      p/option-font-size
                                            :vertical-align "middle"
                                            :editable?      {:select true}}
                                           (some? actions) (assoc :actions actions))}})

(defn- get-option-actions
  [{:keys [on-option-click value]}]
  (cond-> {}
          (some? on-option-click) (assoc :click (cond-> {:type       "action"
                                                         :on         "click"
                                                         :id         on-option-click
                                                         :unique-tag "question-action"}
                                                        (some? value) (assoc :params {:value value})))))

(defn- create-image-with-text-options
  [{:keys [object-name x y width height
           img text label-type value question-id
           on-option-voice-over-click]
    :as   props}]
  (let [show-text? (= label-type "audio-text")
        show-voice-over? (not= label-type "none")

        border-radius (get-in common-params/option [:border-radius :image])
        substrate-name (str object-name "-substrate")
        image-name (str object-name "-image")
        text-name (str object-name "-text")
        button-name (str object-name "-button")

        label-height 80
        image-ratio 1.25
        image-width width
        image-height (min (* image-width image-ratio)
                          (- height label-height p/options-gap))

        text-x (+ voice-over/default-size p/voice-over-margin)
        text-y (+ image-height p/options-gap)
        text-width (- width text-x)
        text-height label-height

        button-x (if show-text? 0 (- (/ width 2)
                                     (/ voice-over/default-size 2)))

        actions (get-option-actions props)]
    (cond-> {:objects {(keyword object-name) {:type        "group"
                                              :object-name object-name
                                              :x           x
                                              :y           y
                                              :children    (cond-> [substrate-name]
                                                                   show-voice-over? (conj button-name)
                                                                   :always (conj image-name)
                                                                   show-text? (conj text-name))}}}
            :always (merge-data (create-substrate {:object-name   substrate-name
                                                   :x             0
                                                   :y             0
                                                   :width         image-width
                                                   :height        image-height
                                                   :border-radius border-radius
                                                   :question-id   question-id
                                                   :value         value
                                                   :actions       actions}))
            :always (merge-data (create-image {:object-name image-name
                                               :src         img
                                               :width       image-width
                                               :height      image-height
                                               :actions     actions}))
            show-text? (merge-data (create-text {:object-name text-name
                                                 :x           text-x
                                                 :y           text-y
                                                 :width       text-width
                                                 :height      text-height
                                                 :text        text
                                                 :actions     actions}))
            show-voice-over? (merge-data (voice-over/create {:object-name     button-name
                                                             :x               button-x
                                                             :y               (+ image-height p/options-gap)
                                                             :question-id     question-id
                                                             :value           value
                                                             :on-click        on-option-voice-over-click
                                                             :on-click-params {:value value}})))))

(defn- create-text-option
  [{:keys [object-name x y width height
           text question-id value
           on-option-voice-over-click]
    :as   props}]
  (let [{voice-over-size :size voice-over-margin :margin} common-params/voice-over
        border-radius (get-in common-params/option [:border-radius :text])
        text-padding (get-in common-params/option [:padding :text])

        voice-over-name (str object-name "-voice-over")
        substrate-name (str object-name "-substrate")
        text-name (str object-name "-text")

        content-x (+ voice-over-size voice-over-margin)
        content-y 0
        content-width (- width content-x)
        content-height height

        actions (get-option-actions props)]
    (merge-data {:objects {(keyword object-name) {:type     "group"
                                                  :x        x
                                                  :y        y
                                                  :children [substrate-name voice-over-name text-name]}}}
                (create-substrate {:object-name   substrate-name
                                   :x             content-x
                                   :y             content-y
                                   :width         content-width
                                   :height        content-height
                                   :border-radius border-radius
                                   :question-id   question-id
                                   :value         value
                                   :actions       actions})
                (create-text {:object-name text-name
                              :x           (+ content-x text-padding)
                              :y           (+ content-y text-padding)
                              :width       (- content-width (* 2 text-padding))
                              :height      (- content-height (* 2 text-padding))
                              :text        text
                              :actions     actions})
                (voice-over/create {:object-name     voice-over-name
                                    :question-id     question-id
                                    :value           value
                                    :on-click        on-option-voice-over-click
                                    :on-click-params {:value value}}))))

(defn- create-thumbs-option
  [{:keys [object-name x y width height
           img question-id value]
    :as   props}]
  (let [substrate-name (str object-name "-substrate")
        image-name (str object-name "-image")
        border-radius (/ width 2)
        actions (get-option-actions props)]
    (merge-data {:objects {(keyword object-name) {:type     "group"
                                                  :x        x
                                                  :y        y
                                                  :children [substrate-name image-name]}}}
                (create-substrate {:object-name   substrate-name
                                   :x             0
                                   :y             0
                                   :width         width
                                   :height        height
                                   :border-radius border-radius
                                   :question-id   question-id
                                   :value         value
                                   :actions       actions})
                (create-image {:object-name   image-name
                               :src           img
                               :width         width
                               :height        height
                               :border-radius (- border-radius 10)
                               :actions       actions}))))

(defn create
  [{:keys [question-type] :as props}]
  (case question-type
    "multiple-choice-image" (create-image-with-text-options props)
    "multiple-choice-text" (create-text-option props)
    "thumbs-up-n-down" (create-thumbs-option props)))
