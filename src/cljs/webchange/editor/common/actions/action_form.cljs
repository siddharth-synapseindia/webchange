(ns webchange.editor.common.actions.action-form
  (:require
    [re-frame.core :as re-frame]
    [reagent.core :as r]
    [webchange.common.kimage :refer [kimage]]
    [webchange.common.anim :refer [anim animations init-spine-player]]
    [webchange.interpreter.core :refer [get-data-as-url]]
    [webchange.interpreter.events :as ie]
    [webchange.editor.common.actions.action-forms.animation-sequence :refer [animation-sequence-panel]]
    [webchange.editor.common.actions.action-types :refer [action-types]]
    [webchange.editor.events :as events]
    [webchange.editor.subs :as es]
    [webchange.editor.common.actions.events :as actions.events]
    [webchange.editor.common.actions.subs :as actions.subs]
    [webchange.editor.form-elements :as f]
    [webchange.editor.form-elements.wavesurfer :as ws]
    [webchange.subs :as subs]
    [konva :refer [Transformer]]
    [react-konva :refer [Stage Layer Group Rect Text Custom]]
    [sodium.core :as na]
    [sodium.extensions :as nax]
    [soda-ash.core :as sa :refer [Divider
                                  Form
                                  FormDropdown
                                  FormField
                                  FormInput]]))

(defn- key-value-param [key value on-change on-remove]
  (let [props (r/atom nil)]
    (fn [key value on-change on-remove]
      [sa/Item {}
       (if @props
         [sa/ItemContent {}
          [na/form-input {:label "key" :default-value key :on-change #(swap! props assoc :key (-> %2 .-value keyword)) :inline? true}]
          [na/form-input {:label "value" :default-value value :on-change #(swap! props assoc :value (-> %2 .-value)) :inline? true}]

          [na/button {:basic? true :content "save" :on-click #(do (on-change (:key @props) (:value @props))
                                                                  (reset! props nil))}]]
         [sa/ItemContent {}
          [:a (str (name key) ": " value)]
          [:div {:style {:float "right"}}
           [na/icon {:name "edit" :link? true
                     :on-click #(reset! props {:key key :value value})}]
           [na/icon {:name "remove" :link? true
                     :on-click on-remove}]]])
       ])))

(defn- key-value-params [props field-name]
  [:div
   [sa/ItemGroup {}
    [sa/Item {}
     [sa/ItemContent {}
      [na/header {:as "h4" :floated "left" :content "Params"}]
      [:div {:style {:float "right"}}
       [na/icon {:name "add" :link? true :on-click #(swap! props assoc-in [field-name :empty] "empty")}]]]]

    (for [[key value] (field-name @props)]
      ^{:key (str key)}
      [key-value-param
       key value
       (fn [new-key new-value]
         (swap! props update-in [field-name] dissoc key)
         (swap! props assoc-in [field-name new-key] new-value))
       (fn [] (swap! props update-in [field-name] dissoc key))
       ])]

   ])

(defn- vector-param
  [value on-change on-remove]
  (let [props (r/atom value)]
    (fn [value on-change on-remove]
      [sa/Item {}
       (if @props
         [sa/ItemContent {}
          [na/form-input {:label         "value"
                          :default-value value
                          :on-change     #(reset! props (-> %2 .-value))
                          :inline?       true}]

          [na/button {:basic?   true
                      :content  "save"
                      :on-click #(do (on-change @props)
                                     (reset! props value))}]]
         [sa/ItemContent {}
          [:a (str value)]
          [:div {:style {:float "right"}}
           [na/icon {:name     "edit"
                     :link?    true
                     :on-click #(reset! props value)}]
           [na/icon {:name     "remove"
                     :link?    true
                     :on-click on-remove}]]])
       ])))

(defn- vector-params
  [props field-name label]
  (let [values (or (field-name @props) [])
        next-item-name #(str "item-" (+ (count values) 1))]
    [:div
     [sa/ItemGroup {}
      [sa/Item {}
       [sa/ItemContent {}
        [na/header {:as "h4" :floated "left" :content (or label "Params")}]
        [:div {:style {:float "right"}}
         [na/icon {:name "add" :link? true :on-click #(swap! props assoc field-name (conj values (next-item-name)))}]]]]

      (for [[idx value] (map-indexed (fn [idx itm] [idx itm]) values)]
        ^{:key (str idx)}
        [vector-param
         value
         (fn [new-value] (swap! props assoc-in [field-name idx] new-value))
         (fn [] (swap! props update-in [field-name] #(vec (concat (subvec % 0 idx) (subvec % (inc idx))))))
         ])]

     ]))

(defn- action-panel
  [props]
  [:div
   [f/action-dropdown props :id]
   [na/checkbox {:label "Return immediately"
                 :default-checked? (:return-immediately @props)
                 :on-change #(swap! props assoc :return-immediately (.-checked %2))}]])

(defn- state-panel [props {:keys [scene-id]}]
  [:div
   [f/object-dropdown props :target scene-id]
   [f/object-states-dropdown props :id (:target @props) scene-id]])

(defn- animation-panel [props]
  [:div
   [f/target-animation-dropdown props :target]
   [na/form-input {:label "id" :default-value (:id @props) :on-change #(swap! props assoc :id (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "track" :default-value (:track @props) :on-change #(swap! props assoc :track (-> %2 .-value)) :inline? true}]])

(defn- add-animation-panel [props]
  [:div
   [f/target-animation-dropdown props :target]
   [na/form-input {:label "id" :default-value (:id @props) :on-change #(swap! props assoc :id (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "track" :default-value (:track @props) :on-change #(swap! props assoc :track (-> %2 .-value)) :inline? true}]
   [na/checkbox {:label "loop" :default-checked? (:loop @props) :on-change #(swap! props assoc :loop (.-checked %2))}]])

(defn- start-animation-panel [props]
  [:div
   [f/target-animation-dropdown props :target]])

(defn- remove-animation-panel [props]
  [:div
   [f/target-animation-dropdown props :target]
   [na/form-input {:label "track" :default-value (:track @props) :on-change #(swap! props assoc :track (-> %2 .-value)) :inline? true}]])

(defn- set-skin-panel [props]
  [:div
   [f/target-animation-dropdown props :target]
   [na/form-input {:label "skin" :default-value (:skin @props) :on-change #(swap! props assoc :skin (-> %2 .-value)) :inline? true}]])

(defn- animation-props-panel [props]
  [:div
   [f/target-animation-dropdown props :target]
   [key-value-params props :props]])

(defn- audio-panel
  [props {:keys [scene-id]}]
  (r/with-let [input-values (r/atom @props)]
              [:div
               [f/audio-asset-dropdown props :id scene-id]
               [na/form-input {:label "start" :value (:start @input-values) :on-change #(do
                                                                                          (swap! input-values assoc :start (-> %2 .-value))
                                                                                          (swap! props assoc :start (-> %2 .-value js/parseFloat))) :inline? true}]
               [na/form-input {:label "duration" :value (:duration @input-values) :on-change #(do
                                                                                                (swap! input-values assoc :duration (-> %2 .-value))
                                                                                                (swap! props assoc :duration (-> %2 .-value js/parseFloat))) :inline? true}]
               [na/form-input {:label "offset" :default-value (:offset @props) :on-change #(swap! props assoc :offset (-> %2 .-value js/parseFloat)) :inline? true}]
               [na/form-input {:label "loop" :default-value (:loop @props) :on-change #(swap! props assoc :loop (-> %2 .-value (= "true"))) :inline? true}]

               [ws/audio-waveform-modal
                {:key (:id @props) :start (:start @props) :end (+ (:start @props) (:duration @props))}
                (fn [{:keys [start duration]}]
                  (swap! input-values assoc :start start)
                  (swap! props assoc :start start)
                  (swap! input-values assoc :duration duration)
                  (swap! props assoc :duration duration))]

               ]))

(defn- scene-panel
  [props]
  [:div
   [f/scene-dropdown props :scene-id]])

(defn- empty-panel
  [props]
  [:div
   [na/form-input {:label "duration" :default-value (:duration @props) :on-change #(swap! props assoc :duration (-> %2 .-value js/parseInt)) :inline? true}]])

(defn- sequence-panel
  [props {:keys [scene-id]}]
  (let [edit-mode (r/atom false)
        current-value (atom (clojure.string/join "\n" (:data @props)))]
    (fn [props]
      [:div
       [na/form-input {:label "type" :default-value (:type @props) :on-change #(swap! props assoc :type (-> %2 .-value)) :inline? true}]
       (if @edit-mode
         [:div
          [na/text-area {:label "data"
                         :default-value @current-value
                         :on-change #(reset! current-value (-> %2 .-value))}]
          [na/form-button {:content "OK" :on-click #(do
                                                      (swap! props assoc :data (clojure.string/split @current-value "\n"))
                                                      (reset! edit-mode false))}]]
         [:div
          (for [action (:data @props)]
            ^{:key (str action)}
            [:p [:a {:on-click #(re-frame/dispatch [::events/select-scene-action action scene-id])} (str action)]])
          [na/form-button {:content "Edit" :on-click #(reset! edit-mode true)}]])
       ])))

(defn- data-panel [props]
  (let [{data :data} @(re-frame/subscribe [::actions.subs/form-data])]
    (swap! props assoc :data data)
    [sa/ItemGroup {}
     [sa/Item {}
      [sa/ItemContent {}
       [na/header {:as "h4" :floated "left" :content "Elements"}]
       [:div {:style {:float "right"}}
        [na/icon {:name "add" :link? true :on-click #(re-frame/dispatch [::actions.events/selected-action-add-above-action 0])}]]]]
     (for [[index action] (map-indexed (fn [idx itm] [idx itm]) data)]
       ^{:key (str index action)}
       [sa/Item {}
        [sa/ItemContent {}
         [:a {:on-click #(re-frame/dispatch [::actions.events/select-action-data-path index])} (str (:type action) " - " (:target action))]
         [:div {:style {:float "right"}}
          [na/icon {:name "copy outline" :link? true
                    :on-click #(re-frame/dispatch [::actions.events/selected-action-copy-data index])}]
          [na/icon {:name "arrow up" :link? true
                    :on-click #(re-frame/dispatch [::actions.events/selected-action-order-up index])}]
          [na/icon {:name "arrow down" :link? true
                    :on-click #(re-frame/dispatch [::actions.events/selected-action-order-down index])}]
          [na/icon {:name "level up" :link? true
                    :on-click #(re-frame/dispatch [::actions.events/selected-action-add-above-action index])}]
          [na/icon {:name "level down" :link? true
                    :on-click #(re-frame/dispatch [::actions.events/selected-action-add-below-action index])}]
          [na/icon {:name "remove" :link? true
                    :on-click #(re-frame/dispatch [::actions.events/selected-action-remove-data index])}]]
         ]])]))

(defn- parallel-panel [props]
  [:div
   [data-panel props]
   ])

(defn- sequence-data-panel [props]
  [:div
   [data-panel props]
   ])

(defn- common
  [props]
  [:div
   [FormField {} [FormDropdown {:label         "Type"
                                :search        true
                                :selection     true
                                :options       action-types
                                :inline        true
                                :default-value (:type @props)
                                :on-change     #(swap! props assoc :type (.-value %2))}]]
   [FormField {} [FormInput {:label         "Description"
                             :default-value (:description @props)
                             :on-change     #(swap! props assoc :description (-> %2 .-value))
                             :inline        true}]]])

(defn- add-alias-panel [props {:keys [scene-id]}]
  [:div
   [f/object-dropdown props :target scene-id]
   [na/form-input {:label "Alias" :default-value (:alias @props) :on-change #(swap! props assoc :alias (-> %2 .-value)) :inline? true}]
   [f/object-states-dropdown props :state (:target @props) scene-id]])

(defn- transition-panel [props]
  [:div
   [f/transition-dropdown props :transition-id]
   [key-value-params props :to]])

(defn- test-transitions-collide-panel [props]
  [:div
   [f/transition-dropdown props :transition-1 "Transition 1"]
   [f/transition-dropdown props :transition-2 "Transition 2"]
   [f/action-dropdown props :success "success"]
   [f/action-dropdown props :fail "fail"]])

(defn- remove-flows-panel [props]
  [:div
   [na/form-input {:label "Flow tag" :default-value (:flow-tag @props) :on-change #(swap! props assoc :flow-tag (-> %2 .-value)) :inline? true}]])

(defn- dataset-var-provider-panel [props]
  [:div
   [na/form-input {:label "Provider id" :default-value (:provider-id @props) :on-change #(swap! props assoc :provider-id (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "From" :default-value (:from @props) :on-change #(swap! props assoc :from (-> %2 .-value)) :inline? true}]
   [na/divider {}]
   [vector-params props :variables "Variables"]
   [na/divider {}]])

(defn- lesson-var-provider-panel [props]
  [:div
   [na/form-input {:label "Provider id" :default-value (:provider-id @props) :on-change #(swap! props assoc :provider-id (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "From" :default-value (:from @props) :on-change #(swap! props assoc :from (-> %2 .-value)) :inline? true}]
   [na/divider {}]
   [vector-params props :variables "Variables"]
   [na/divider {}]
   [na/form-input {:label "limit" :default-value (:limit @props) :on-change #(swap! props assoc :limit (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "repeat" :default-value (:repeat @props) :on-change #(swap! props assoc :repeat (-> %2 .-value)) :inline? true}]
   [na/checkbox {:label "shuffled" :default-checked? (:shuffled @props) :on-change #(swap! props assoc :shuffled (.-checked %2))}]])

(defn- vars-var-provider-panel [props]
  [:div
   [na/form-input {:label "Provider id" :default-value (:provider-id @props) :on-change #(swap! props assoc :provider-id (-> %2 .-value)) :inline? true}]
   [na/divider {}]
   [vector-params props :from "From"]
   [na/divider {}]
   [vector-params props :variables "Variables"]
   [na/divider {}]
   [na/form-input {:label "on-end" :default-value (:repeat @props) :on-change #(swap! props assoc :repeat (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "limit" :default-value (:limit @props) :on-change #(swap! props assoc :limit (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "repeat" :default-value (:repeat @props) :on-change #(swap! props assoc :repeat (-> %2 .-value)) :inline? true}]
   [na/checkbox {:label "shuffled" :default-checked? (:shuffled @props) :on-change #(swap! props assoc :shuffled (.-checked %2))}]])

(defn- test-var-scalar-panel [props]
  [:div
   [na/form-input {:label "value1" :default-value (:value1 @props) :on-change #(swap! props assoc :value1 (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "value2" :default-value (:value2 @props) :on-change #(swap! props assoc :value2 (-> %2 .-value)) :inline? true}]
   [f/action-dropdown props :success "success"]
   [f/action-dropdown props :fail "fail"]])

(defn- test-var-list-panel [props]
  [:div
   [na/divider {}]
   [vector-params props :var-names "var names"]
   [na/divider {}]
   [vector-params props :values "values"]
   [na/divider {}]
   [f/action-dropdown props :success "success"]
   [f/action-dropdown props :fail "fail"]])

(defn- case-option-item-panel [key action]
  (let [edit (r/atom nil)]
    (fn [key action]
      [sa/Item {}
       [sa/ItemContent {}
        (if @edit
          [:div {:style {:float "left"}} [na/form-input {:label "New key" :size "mini" :default-value (name key) :on-change #(reset! edit (-> %2 .-value keyword)) :inline? true}]]
          [:a {:on-click #(re-frame/dispatch [::actions.events/select-action-options-path key])} (str (name key) " : " (:type action))])

        [:div {:style {:float "right"}}
         (if @edit
           [na/icon {:name "checkmark" :link? true :on-click #(do
                                                                (re-frame/dispatch [::actions.events/rename-selected-action-option key @edit])
                                                                (reset! edit nil))}]
           [na/icon {:name "pencil" :link? true
                     :on-click #(reset! edit key)}])
         [na/icon {:name "remove" :link? true
                   :on-click #(re-frame/dispatch [::actions.events/selected-action-remove-option key])}]]
        ]])))

(defn- case-options-panel [props]
  (let [new-name (r/atom nil)]
    (fn [props]
      (let [{options :options} @(re-frame/subscribe [::actions.subs/form-data])]
        (swap! props assoc :options options)
        [sa/ItemGroup {}
         [sa/Item {}
          [sa/ItemContent {}
           [na/grid {:columns 3}
            [na/grid-column {}
             [na/header {:as "h4" :content "Options"}]]
            [na/grid-column {}
             [:div
              [na/form-input {:size "mini" :on-change #(reset! new-name (-> %2 .-value)) :inline? true}]]]
            [na/grid-column {}
             [:div {:style {:float "right"}}
              [na/icon {:name "add" :link? true :on-click #(do
                                                             (re-frame/dispatch [::actions.events/selected-action-add-option @new-name])
                                                             (reset! new-name nil))}]]]]]]
         (for [[key action] options]
           ^{:key (str key)}
           [case-option-item-panel key action])]))))

(defn- case-panel [props]
  [:div
   [na/form-input {:label "value" :default-value (:value @props) :on-change #(swap! props assoc :value (-> %2 .-value)) :inline? true}]
   [case-options-panel props]])

(defn- counter-panel [props]
  [:div
   [na/form-input {:label "id" :default-value (:counter-id @props) :on-change #(swap! props assoc :counter-id (-> %2 .-value)) :inline? true}]
   [f/counter-action-dropdown props :counter-action]])

(defn- set-variable-panel [props]
  [:div
   [na/form-input {:label "name" :default-value (:var-name @props) :on-change #(swap! props assoc :var-name (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "value" :default-value (:var-value @props) :on-change #(swap! props assoc :var-value (-> %2 .-value)) :inline? true}]])

(defn- set-progress-panel [props]
  [:div
   [na/form-input {:label "name" :default-value (:var-name @props) :on-change #(swap! props assoc :var-name (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "value" :default-value (:var-value @props) :on-change #(swap! props assoc :var-value (-> %2 .-value)) :inline? true}]])

(defn- copy-variable-panel [props]
  [:div
   [na/form-input {:label "name" :default-value (:var-name @props) :on-change #(swap! props assoc :var-name (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "from" :default-value (:from @props) :on-change #(swap! props assoc :from (-> %2 .-value)) :inline? true}]])

(defn text-animation-item [item on-change on-remove]
  (let [props (r/atom nil)]
    (fn [{:keys [at chunk] :as item} on-change on-remove]
      [sa/Item {}
       (if @props
         [sa/ItemContent {}
          [na/form-input {:label "at" :default-value at :on-change #(swap! props assoc :at (-> %2 .-value js/parseFloat)) :inline? true}]
          [na/form-input {:label "chunk" :default-value chunk :on-change #(swap! props assoc :chunk (-> %2 .-value js/parseInt)) :inline? true}]

          [na/button {:basic? true :content "save" :on-click #(do (on-change @props)
                                                                  (reset! props nil))}]]
         [sa/ItemContent {}
          [:a (str "at: " at " chunk: " chunk)]
          [:div {:style {:float "right"}}
           [na/icon {:name "edit" :link? true
                     :on-click #(reset! props item)}]
           [na/icon {:name "remove" :link? true
                     :on-click on-remove}]]])
       ])))

(defn text-animation-items [props]
  [:div
   [sa/ItemGroup {}
    [sa/Item {}
     [sa/ItemContent {}
      [na/header {:as "h4" :floated "left" :content "Items"}]
      [:div {:style {:float "right"}}
       [na/icon {:name "add" :link? true :on-click #(swap! props update-in [:data] conj {})}]]]]

    (for [[idx item] (map-indexed (fn [idx itm] [idx itm]) (:data @props))]
      ^{:key (str (:start item))}
      [text-animation-item
       item
       (fn [item] (swap! props assoc-in [:data idx] item))
       (fn [] (swap! props update-in [:data] #(vec (concat (subvec % 0 idx) (subvec % (inc idx))))))
       ])]

   ])

(defn text-animation-panel
  [props {:keys [scene-id]}]
  [:div
   [f/object-dropdown props :target scene-id]
   [na/form-input {:label "animation" :default-value (:animation @props) :on-change #(swap! props assoc :animation (-> %2 .-value)) :inline? true}]
   [na/divider {}]
   [sa/FormGroup {}
    [f/audio-asset-dropdown props :audio]
    [na/button {:basic? true :content "Upload new" :on-click #(re-frame/dispatch [::events/show-upload-asset-form])}]]
   [na/form-input {:label "start" :value (:start @props) :on-change #(swap! props assoc :start (-> %2 .-value)) :inline? true}]
   [na/form-input {:label "duration" :value (:duration @props) :on-change #(swap! props assoc :duration (-> %2 .-value)) :inline? true}]
   [ws/text-animation-waveform-modal
    {:key (:audio @props) :start (:start @props) :end (+ (:start @props) (:duration @props)) :sequence-data (:data @props)}
    (fn [{:keys [start duration regions]}]
      (swap! props assoc :start start)
      (swap! props assoc :duration duration)
      (swap! props assoc :data (->> regions
                                    (map-indexed (fn [idx region] (assoc region :at (:start region) :chunk idx)))
                                    vec)))]
   [na/divider {}]
   [text-animation-items props]
   [na/divider {}]])

(defn dispatch
  [props params]
  [:div
   [common props]
   [Divider]
   (case (-> @props :type keyword)
     :action [action-panel props]
     :audio [audio-panel props params]
     :state [state-panel props params]
     :add-alias [add-alias-panel props params]
     :empty [empty-panel props]
     :animation [animation-panel props]
     :add-animation [add-animation-panel props]
     :start-animation [start-animation-panel props]
     :remove-animation [remove-animation-panel props]
     :set-skin [set-skin-panel props]
     :animation-props [animation-props-panel props]
     :animation-sequence [animation-sequence-panel props params]
     :scene [scene-panel props]
     :transition [transition-panel props]
     :test-transitions-collide [test-transitions-collide-panel props]

     :sequence [sequence-panel props params]
     :parallel [parallel-panel props]
     :sequence-data [sequence-data-panel props]
     :remove-flows [remove-flows-panel props]

     :dataset-var-provider [dataset-var-provider-panel props]
     :lesson-var-provider [lesson-var-provider-panel props]
     :vars-var-provider [vars-var-provider-panel props]
     :test-var-scalar [test-var-scalar-panel props]
     :test-var-list [test-var-list-panel props]
     :test-value [test-var-scalar-panel props]
     :case [case-panel props]
     :counter [counter-panel props]
     :set-variable [set-variable-panel props]
     :set-progress [set-progress-panel props]
     :copy-variable [copy-variable-panel props]

     :text-animation [text-animation-panel props params]
     nil)])

(defn main-action-form
  [props {:keys [current-tab
                 change-current-tab] :as params}]
  [:div
   [na/menu {:tabular? true}
    [na/menu-item {:name     "general"
                   :active?  (= current-tab :general)
                   :on-click #(change-current-tab :general)}]
    [na/menu-item {:name     "params"
                   :active?  (= current-tab :params)
                   :on-click #(change-current-tab :params)}]
    [na/menu-item {:name     "from params"
                   :active?  (= current-tab :from-params)
                   :on-click #(change-current-tab :from-params)}]
    [na/menu-item {:name     "from var"
                   :active?  (= current-tab :from-var)
                   :on-click #(change-current-tab :from-var)}]]

   (case current-tab
     :general [dispatch props params]
     :params [key-value-params props :params]
     :from-params [key-value-params props :params]
     :from-var [key-value-params props :params])])

(defn action-form
  [props {:keys [scene-id]}]
  (when-not scene-id (throw (js/Error. "Scene id is not defined")))
  (r/with-let [_ (re-frame/dispatch [::ie/load-scene scene-id])
               tab (r/atom :general)]
              (let [scene @(re-frame/subscribe [::subs/scene scene-id])
                    params {:scene-id               scene-id
                            :scene-objects          (:objects scene)
                            :show-upload-asset-form #(re-frame/dispatch [::events/show-upload-asset-form])
                            :current-tab            @tab
                            :change-current-tab     #(reset! tab %)}]
                [main-action-form props params])))
