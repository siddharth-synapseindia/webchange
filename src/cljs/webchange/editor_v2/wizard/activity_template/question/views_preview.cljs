(ns webchange.editor-v2.wizard.activity-template.question.views-preview
  (:require
    [webchange.interpreter.components :refer [get-scene-objects-data-by-scene-data get-activity-resources]]
    [webchange.interpreter.renderer.scene.modes.modes :as modes]
    [webchange.interpreter.renderer.stage :refer [stage]]
    [webchange.question.create :as question]
    [webchange.question.get-question-data :refer [form->question-data]]
    [webchange.utils.scene-data :as scene-utils]
    [webchange.logger.index :as logger]))

(defn question-preview
  [data]
  (logger/trace "Form data" data)
  [:div.preview-wrapper
   (let [id (random-uuid)
         scene-data (->> (question/create (logger/->>with-trace "Question data"
                                                                (form->question-data data))
                                          {:action-name "question-action" :object-name "question"}
                                          {:visible? true})
                         (question/add-to-scene scene-utils/empty-data)
                         (logger/->>with-trace-folded "Scene data"))

         objects (get-scene-objects-data-by-scene-data scene-data)
         resources (get-activity-resources nil scene-data)]
     ^{:key id}
     [stage {:mode                ::modes/preview
             :scene-data          {:scene-id  (str "question-preview-" id)
                                   :objects   objects
                                   :resources resources}
             :show-loader-screen? false
             :reset-resources?    false}])
   [:div.preview-overlay]])
