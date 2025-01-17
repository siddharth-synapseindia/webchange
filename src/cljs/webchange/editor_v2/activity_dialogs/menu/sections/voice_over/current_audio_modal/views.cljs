(ns webchange.editor-v2.activity-dialogs.menu.sections.voice-over.current-audio-modal.views
  (:require
   [re-frame.core :as re-frame]
   [reagent.core :as r]
   [webchange.ui-framework.components.index :refer [button dialog icon-button select]]
   [webchange.editor-v2.components.audio-wave-form.views :refer [audio-wave-form]]
   [webchange.editor-v2.components.audio-wave-form.utils :as ws-utils]
   [webchange.editor-v2.activity-dialogs.menu.sections.voice-over.current-audio-modal.state :as state]
   [webchange.editor-v2.activity-dialogs.menu.sections.voice-over.state :as voice-over-state]))

(defn current-audio-modal
  []
  (r/with-let [ws (atom nil)
               handle-ref #(reset! ws %)]
    (let [open? @(re-frame/subscribe [::state/modal-state])
          cancel #(re-frame/dispatch [::state/cancel])
          {:keys [audio] :as audio-data} @(re-frame/subscribe [::voice-over-state/current-audio])
          handle-change-region #(re-frame/dispatch [::voice-over-state/set-current-audio-region %])
          handle-recognition-retry #(re-frame/dispatch [::voice-over-state/recognition-retry %])
          handle-scroll-start (fn []
                                (let [{:keys [start]} audio-data]
                                  (ws-utils/center-to-time @ws start)))
          handle-scroll-finish (fn []
                                 (let [{:keys [start end duration]} audio-data
                                       end-time (or end (+ start duration))]
                                   (ws-utils/center-to-time @ws end-time)))
          options @(re-frame/subscribe [::voice-over-state/available-options])
          selected-option @(re-frame/subscribe [::voice-over-state/selected-option])
          handle-select-option #(re-frame/dispatch [::voice-over-state/recognition-select-option %])]
      (when open?
        [dialog
         {:title    "Edit current selection"
          :on-close cancel
          :actions  [[button {:on-click cancel
                              :variant  "outlined"
                              :size     "big"}
                      "Close"]]}
         [:div.actions-icn
          [icon-button {:icon     "restart"
                        :title    "Recognition retry"
                        :size     "small"
                        :on-click handle-recognition-retry}
           "Recognition"]
          [icon-button {:icon     "arrow-first"
                        :title    "Scroll to region start"
                        :size     "small"
                        :on-click handle-scroll-start}]
          [icon-button {:icon     "arrow-last"
                        :title    "Scroll to region end"
                        :size     "small"
                        :on-click handle-scroll-finish}]]
         [audio-wave-form (merge {:url audio}
                                 (select-keys audio-data [:start :end :duration])
                                 {:on-change      handle-change-region
                                  :show-controls? true
                                  :ref            handle-ref})]
         [select {:type "int"
                  :value selected-option
                  :options (->> options
                                (map-indexed (fn [idx region] {:text (:region-text region)
                                                               :value idx})))
                  :on-change handle-select-option}]]))))
