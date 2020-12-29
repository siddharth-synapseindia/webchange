(ns webchange.editor-v2.scene.action.views
  (:require
    [re-frame.core :as re-frame]
    [reagent.core :as r]
    [cljs-react-material-ui.reagent :as ui]
    [webchange.subs :as subs]
    [webchange.editor-v2.scene.action.events :as scene-action.events]
    [webchange.interpreter.events :as interpreter.events]
    [webchange.editor-v2.wizard.activity-template.views :refer [template]]
    ))

(defn action-modal
  [course-id]
  (let [open? @(re-frame/subscribe [::scene-action.events/modal-state])
        current-action @(re-frame/subscribe [::scene-action.events/current-action])
        scene-id (re-frame/subscribe [::subs/current-scene])
        scene-data @(re-frame/subscribe [::subs/scene @scene-id])
        action (get-in scene-data [:metadata :actions current-action])
        data (r/atom {})
        close #(re-frame/dispatch [::scene-action.events/close])
        save #(re-frame/dispatch [::scene-action.events/save course-id @data @scene-id
                                  (fn [result]
                                    (re-frame/dispatch [::interpreter.events/set-scene @scene-id (:data result)])
                                    (re-frame/dispatch [::interpreter.events/store-scene @scene-id (:data result)])
                                    (close))])]
    (when open?
      [ui/dialog
       {:open       true
        :on-close   close
        :full-width true
        :max-width  "xl"}
       [ui/dialog-title (:title action)]
       [ui/dialog-content {:class-name "translation-form"}
        [template {:template action
                   :data     data}]]
       [ui/dialog-actions
        [ui/button {:on-click close}
         "Cancel"]
        [:div {:style {:position "relative"}}
         [ui/button {:color    "secondary"
                     :variant  "contained"
                     :on-click save}
          "Save"]]]])))

(defn action-button
  [name handle-click]
  [ui/form-control {:full-width true
                    :margin     "normal"}
   [ui/button
    {:on-click handle-click}
    name]])

(defn actions
  [{:keys [scene-data]}]
  (let [actions (get-in scene-data [:metadata :actions])]
    [:div (map (fn [[name action]]
                 [action-button (:title action) #(re-frame/dispatch [::scene-action.events/show-actions-form name])]
                 ) actions)]))