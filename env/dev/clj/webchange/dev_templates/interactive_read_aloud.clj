(ns webchange.dev-templates.interactive-read-aloud
  (:require [webchange.dev-templates :as t]
            [webchange.templates.core :as templates]
            [webchange.course.core :as core]))

(comment
  (def test-course-slug (-> (t/create-test-course) :slug))
  (def scene-slug "test-activity-17")

  (t/update-activity test-course-slug scene-slug :actions [:dialog-intro :page-cover-action])

  (let [data {:activity-name "Book"
              :template-id   32

              :characters    [{:name     "teacher"
                               :skeleton "senoravaca"}]
              :name          "Book"
              :lang          "English"
              :skills        []

              :cover-layout  "title-top"
              :cover-title   "my title"
              :course-name   "my title"
              :cover-image   {:src "/upload/BYBCRIPVTBJJTLAQ.png"}

              :illustrators  ["illustrator"]
              :authors       ["author"]}
        activity (templates/activity-from-template data)
        metadata (templates/metadata-from-template data)
        [_ {scene-slug :scene-slug}] (core/create-scene! activity metadata test-course-slug scene-slug [] t/user-id)]
    (str "/courses/" test-course-slug "/editor-v2/" scene-slug))

  ;add left page
  (let [data {:data
              {:action-name   "add-page"
               :type          "page"
               :page-layout   "text-small-at-bottom"
               :spread-layout "text-right-top"
               :text          "left page"
               :image         {:src "/upload/RGKDBQVKBIMYBBMS.png"}}}
        scene-data (core/get-scene-data test-course-slug scene-slug)
        activity (templates/update-activity-from-template scene-data data)]
    (-> (core/save-scene! test-course-slug scene-slug activity t/user-id)
        first))

  ;add right page
  (let [data {:data
              {:action-name   "add-page"
               :type          "page"
               :page-layout   "text-small-at-bottom"
               :spread-layout "text-right-top"
               :text          "right page"
               :image         {:src "/upload/RGKDBQVKBIMYBBMS.png"}}}
        scene-data (core/get-scene-data test-course-slug scene-slug)
        activity (templates/update-activity-from-template scene-data data)]
    (-> (core/save-scene! test-course-slug scene-slug activity t/user-id)
        first))

  ;add dialog
  (let [data {:data {:action-name "add-dialog" :dialog "dialog one"}}
        scene-data (core/get-scene-data test-course-slug scene-slug)
        activity (templates/update-activity-from-template scene-data data)]
    (-> (core/save-scene! test-course-slug scene-slug activity t/user-id)
        first))

  ;add question
  (let [data {:data
              {:action-name   "add-question"
               :question-page {:answers  [{:text "answer one"}
                                          {:text "answer two"}
                                          {:text "answer three" :checked true}
                                          {:text "answer four"}]
                               :question "Question one"
                               :img      "/upload/DQHMQLASWJIKMETH.png"}}}
        scene-data (core/get-scene-data test-course-slug scene-slug)
        activity (templates/update-activity-from-template scene-data data)]
    (-> (core/save-scene! test-course-slug scene-slug activity t/user-id)
        first)))