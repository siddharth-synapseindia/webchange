(ns webchange.editor-v2.translator.text.views-text-animation-editor
  (:require
    [cljs-react-material-ui.reagent :as ui]
    [re-frame.core :as re-frame]
    [webchange.editor-v2.translator.translator-form.state.actions :as translator-form.actions]
    [webchange.editor-v2.translator.translator-form.state.scene :as translator-form.scene]
    [webchange.utils.text :refer [chunks->parts]]
    [webchange.editor-v2.translator.text.views-chunks-editor-form :refer [chunks-editor-form]]
    [webchange.editor-v2.translator.text.views-text-chunks :refer [text-chunks]]
    [webchange.editor-v2.components.audio-wave-form.views :refer [audio-wave-form]]
    [webchange.state.state :as state]
    [webchange.ui.components.message :refer [message]]))

(def modal-state-path [:editor-v2 :translator :text :chunks-modal-state])
(def selected-chunk-path [:editor-v2 :translator :text :chunks :selected])
(def audio-path [:editor-v2 :translator :text :chunks :audio])
(def bounds-path [:editor-v2 :translator :text :chunks :bounds])
(def data-path [:editor-v2 :translator :text :chunks :data])
(def text-data-path [:editor-v2 :translator :text :text-data])
(def text-name [:editor-v2 :translator :text :text-name])

(defn bound
  [{:keys [start end]} value]
  (cond
    (> start value) start
    (< end value) end
    :else value))

(defn- get-text-name
  [db]
  (get-in db text-name))

(defn- get-current-text-data
  [db]
  (get-in db text-data-path {}))

(re-frame/reg-sub
  ::current-text-data
  get-current-text-data)

(re-frame/reg-event-fx
  ::set-current-text-data
  (fn [{:keys [db]} [_ name data]]
    {:db (-> db
             (update-in text-data-path merge data)
             (assoc-in text-name name))}))

(re-frame/reg-event-fx
  ::reset-current-text-data
  (fn [{:keys [db]} [_]]
    {:db (assoc-in db text-data-path {})}))

(re-frame/reg-sub
  ::modal-state
  (fn [db]
    (-> db
        (get-in modal-state-path)
        boolean)))

(re-frame/reg-sub
  ::text-object
  (fn []
    [(re-frame/subscribe [::translator-form.actions/current-phrase-action])
     (re-frame/subscribe [::translator-form.scene/objects-data])
     (re-frame/subscribe [::current-text-data])])
  (fn [[{:keys [target]} objects current-text-data]]
    [target (-> objects (get (keyword target)) (merge current-text-data))]))

(re-frame/reg-sub
  ::selected-chunk
  (fn [db]
    (get-in db selected-chunk-path)))

(re-frame/reg-sub
  ::selected-audio
  (fn [db]
    (let [index (get-in db selected-chunk-path)
          audio (get-in db audio-path)
          data (get-in db data-path)
          bounds (get-in db bounds-path)
          {:keys [start end]} (->> data (filter #(= index (:chunk %))) first)]
      (when (some? audio)
        {:url   audio
         :start (bound bounds start)
         :end   (bound bounds end)}))))

(re-frame/reg-sub
  ::form-available?
  (fn []
    [(re-frame/subscribe [::selected-audio])])
  (fn [[selected-audio]]
    (some? selected-audio)))

(re-frame/reg-event-fx
  ::open
  (fn [{:keys [db]} [_ {:keys [audio start duration data target]}]]
    (let [chunks-count (-> (translator-form.scene/objects-data db)
                           (get (keyword target))
                           (get :chunks)
                           count)
          filtered-data (remove #(<= chunks-count (:chunk %)) data)]
      {:db       (-> db
                     (assoc-in modal-state-path true)
                     (assoc-in data-path filtered-data)
                     (assoc-in audio-path audio)
                     (assoc-in bounds-path {:start start :end (+ start duration)}))
       :dispatch [::select-chunk 0]})))

(re-frame/reg-event-fx
  ::cancel
  (fn [{:keys [db]} [_]]
    {:db       (assoc-in db modal-state-path false)
     :dispatch [::reset-current-text-data]}))

(re-frame/reg-event-fx
  ::apply
  (fn [{:keys [db]} [_]]
    (let [data (get-in db data-path)
          text-path [(get-text-name db)]
          text-data-patch (get-current-text-data db)]
      {:db         (assoc-in db modal-state-path false)
       :dispatch-n [[::translator-form.actions/update-action :phrase {:data data}]
                    [::translator-form.scene/update-object text-path text-data-patch]
                    [::reset-current-text-data]]})))

(re-frame/reg-event-fx
  ::select-chunk
  (fn [{:keys [db]} [_ index]]
    {:db (assoc-in db selected-chunk-path index)}))

(re-frame/reg-event-fx
  ::select-audio
  (fn [{:keys [db]} [_ {:keys [start end duration]}]]
    (let [index (get-in db selected-chunk-path)
          chunk {:at       start
                 :start    start
                 :end      end
                 :chunk    index
                 :duration duration}
          chunks (as-> (get-in db data-path) c
                       (remove #(= index (:chunk %)) c)
                       (conj c chunk)
                       (sort-by :chunk c))]
      {:db (assoc-in db data-path chunks)})))

(defn- get-styles
  []
  {:audio-container {:padding "16px"}})

(defn- text-chunks-form
  []
  (let [[text-object-name text-object-data] @(re-frame/subscribe [::text-object])
        selected-chunk @(re-frame/subscribe [::selected-chunk])
        selected-audio @(re-frame/subscribe [::selected-audio])
        parts (chunks->parts (:text text-object-data) (:chunks text-object-data))
        handle-chunks-change (fn [text-name text-data-patch]
                               (re-frame/dispatch [::set-current-text-data text-name text-data-patch]))
        styles (get-styles)]
    (if (or (nil? selected-audio)
            (nil? (:start selected-audio))
            (nil? (:end selected-audio)))
      [message {:type    "warn"
                :message "Select audio region in translation dialog to configure text animation"}]
      [ui/grid {:container true
                :spacing   16
                :justify   "space-between"}
       [ui/grid {:item true :xs 12}
        [ui/paper {:style (:audio-container styles)}
         [audio-wave-form (merge selected-audio
                                 {:height         96
                                  :on-change      #(re-frame/dispatch [::select-audio %])
                                  :show-controls? true})]]]
       [ui/grid {:item true :xs 12}
        [text-chunks {:parts              parts
                      :selected-chunk-idx selected-chunk
                      :on-click           #(re-frame/dispatch [::select-chunk %])}]]

       [ui/grid {:item true :xs 12}
        [chunks-editor-form (merge (select-keys text-object-data [:text :chunks])
                                   {:on-change             (fn [data] (handle-chunks-change (keyword text-object-name) data))
                                    :show-chunks?          false
                                    :origin-text-disabled? true})]]])))

(defn text-chunks-modal
  []
  (let [open? @(re-frame/subscribe [::modal-state])
        cancel #(re-frame/dispatch [::cancel])
        apply #(re-frame/dispatch [::apply])
        form-available? @(re-frame/subscribe [::form-available?])]
    (when open?
      [ui/dialog
       {:open       true
        :on-close   cancel
        :full-width true
        :max-width  "xl"}
       [ui/dialog-title
        "Edit text animation chunks"]
       [ui/dialog-content {:class-name "translation-form"}
        [text-chunks-form]]
       [ui/dialog-actions
        [ui/button {:on-click cancel}
         "Cancel"]
        [ui/button {:color    "secondary"
                    :variant  "contained"
                    :disabled (not form-available?)
                    :on-click apply}
         "Apply"]]])))
