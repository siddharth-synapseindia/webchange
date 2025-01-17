(ns webchange.question.common.task-text
  (:require
    [webchange.question.common.params :as common-params]
    [webchange.question.common.voice-over-button :as voice-over]
    [webchange.question.utils :refer [merge-data]]))

(defn create
  [{:keys [object-name text-object-name x y width height text question-id on-task-voice-over-click]}]
  (let [button-name (str object-name "-button")]
    (merge-data {:objects {(keyword object-name)      {:type     "group"
                                                       :x        (+ x common-params/block-padding)
                                                       :y        (+ y common-params/block-padding)
                                                       :children [button-name text-object-name]}
                           (keyword text-object-name) {:type           "text"
                                                       :text           text
                                                       :x              (+ voice-over/default-size common-params/voice-over-button-margin)
                                                       :y              0
                                                       :width          (- width
                                                                          voice-over/default-size
                                                                          common-params/voice-over-button-margin
                                                                          (* 2 common-params/block-padding))
                                                       :word-wrap      true
                                                       :font-size      common-params/font-size-task-text
                                                       :vertical-align "top"
                                                       :editable?      {:select true}}}}
                (voice-over/create {:object-name button-name
                                    :x           0
                                    :y           0
                                    :on-click    on-task-voice-over-click
                                    :question-id question-id}))))
