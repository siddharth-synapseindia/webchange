(ns webchange.editor-v2.dialog.dialog-form.views-form-diagram
  (:require
    [re-frame.core :as re-frame]
    [reagent.core :as r]
    [cljs-react-material-ui.icons :as ic]
    [cljs-react-material-ui.reagent :as ui]
    [webchange.editor-v2.dialog.dialog-form.state.actions :as dialog-form.actions]
    [webchange.editor-v2.dialog.utils.dialog-action :as actions-defaults]
    [webchange.editor-v2.translator.translator-form.state.scene :as translator-form.scene]
    [webchange.editor-v2.translator.translator-form.diagram.views :refer [diagram]]
    [webchange.editor-v2.dialog.dialog-form.diagram.items-factory.nodes-factory :refer [get-diagram-items]]
    [webchange.editor-v2.diagram-utils.diagram-widget :refer [diagram-widget]]
    [webchange.editor-v2.diagram-utils.diagram-model.init :refer [init-diagram-model]]
    [webchange.editor-v2.translator.translator-form.state.actions :as translator-form.actions]
    [webchange.editor-v2.translator.translator-form.state.form :as translator-form]))

(defn- add-action
  [action path]
  (let [scene-data @(re-frame/subscribe [::translator-form.scene/scene-data])
        node-list-count (count (get-in scene-data (concat path [:data])))
        node {:path (concat path [(if (= node-list-count 0) 0 (dec node-list-count))])}
        relative-position (if (= node-list-count 0) :before :after)
        ]
    (re-frame/dispatch [::dialog-form.actions/add-new-scene-action action relative-position node]))
  )

(defn- add-concept-action
  [path]
  (let [
        scene-data @(re-frame/subscribe [::translator-form.scene/scene-data])
        node-list-count (count (get-in scene-data (concat path [:data])))
        node {:path (concat path [(if (= node-list-count 0) 0 (dec node-list-count))])}
        relative-position (if (= node-list-count 0) :before :after)
        ]
    (re-frame/dispatch [::dialog-form.actions/add-new-phrase-concept-action relative-position node])))

(def actions {:insert-after         {:text    "Insert activity"
                                     :handler (partial add-action actions-defaults/default-action)}
              :insert-concept-after {:text    "Insert concept"
                                     :handler (partial add-concept-action)}})

(defn menu
  [path]
  (r/with-let [menu-anchor (r/atom nil)]
    (let [close-menu #(reset! menu-anchor nil)]
      (when-not (empty? actions)
        [:div.diagram-menu-container
         [ui/icon-button
          {:on-click #(reset! menu-anchor (.-currentTarget %))}
          [ic/more-vert {:style {:color "#323232"}}]]
         [ui/menu
          {:anchor-el @menu-anchor
           :open      (boolean @menu-anchor)
           :on-close  close-menu}
          (for [[key {:keys [text handler]}] actions]
            ^{:key key}
            [ui/menu-item
             {:on-click #(do (handler path)
                             (close-menu))}
             text])]]))))

(defn simple-dialog
  [{:keys [path]}]                                          ;; data coming in is a string
  (let [scene-data @(re-frame/subscribe [::translator-form.scene/scene-data])
        {:keys [nodes links]} (get-diagram-items scene-data path)
        {:keys [engine]} (init-diagram-model :dialog nodes links {:locked? true})]
    [diagram-widget {:engine engine
                     :zoom?  true}]))

(defn diagram-block
  []
  (let [settings @(re-frame/subscribe [::translator-form/components-settings :diagram])
        dialog-action @(re-frame/subscribe [::translator-form.actions/current-dialog-action-info])
        path (:path dialog-action)]
    (when-not (:hide? settings)
      [:div.diagram-block
       [menu path]
       [simple-dialog {:path path}]])))
