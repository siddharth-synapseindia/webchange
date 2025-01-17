(ns webchange.editor-v2.activity-dialogs.menu.sections.delay.views
  (:require
    [re-frame.core :as re-frame]
    [webchange.editor-v2.activity-dialogs.menu.sections.common.section-block.views :refer [section-block]]
    [webchange.editor-v2.activity-dialogs.menu.sections.delay.state :as state]
    [webchange.ui-framework.components.index :refer [input]]))

(defn form
  []
  (let [value @(re-frame/subscribe [::state/current-value])
        handle-change #(re-frame/dispatch [::state/set-delay %])]
    [:div.delay-form
     [section-block {:title "Action Delay"}
      [input {:value     value
             :type      "int"
             :on-change handle-change}]
     [:span.input-label.thin "ms"]]]))
