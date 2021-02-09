(ns webchange.editor-v2.layout.flipbook.views
  (:require
    [webchange.editor-v2.layout.components.activity-action.views :refer [activity-actions]]
    [webchange.editor-v2.layout.components.activity-stage.views :refer [select-stage]]
    [webchange.editor-v2.layout.components.interpreter_stage.views :refer [interpreter-stage]]
    [webchange.editor-v2.layout.components.share-button.views :refer [share-button]]
    [webchange.editor-v2.scene-diagram.views-diagram :refer [dialogs-diagram]]
    [webchange.editor-v2.layout.flipbook.views-skeleton :refer [skeleton]]
    [webchange.editor-v2.layout.flipbook.views-stage-text :refer [stage-text]]))

(defn layout
  [{:keys [course-id scene-data]}]
  [skeleton {:top-left-component  [:div
                                     [select-stage]
                                     [activity-actions {:course-id  course-id
                                                        :scene-data scene-data}]
                                     [share-button]]
               :top-right-component [interpreter-stage]
               :middle-component    [stage-text]}])
