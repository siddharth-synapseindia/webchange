(ns webchange.book-creator.text-form.utils
  (:require
    [webchange.editor-v2.graph-builder.scene-parser.scene-parser :refer [parse-data]]
    [webchange.utils.flipbook :as utils]))

(defn- page-idx->data
  [scene-data page-idx]
  (-> (utils/get-pages-data scene-data)
      (nth page-idx)))

(defn- find-node
  [graph predicate]
  (some (fn [[node-name node-data]]
          (and (predicate node-name node-data)
               [node-name node-data]))
        graph))

(defn- get-phrase-node
  [scene-data page-data text-name]
  (let [action-name (-> page-data (get :action) (keyword))
        graph (parse-data scene-data action-name)
        [text-animation-node] (find-node graph (fn [_ node-data]
                                                 (let [{:keys [type target]} (get-in node-data [:data])]
                                                   (and (= type "text-animation")
                                                        (= target (clojure.core/name text-name))))))]
    (find-node graph (fn [_ {:keys [children]}]
                       (some #{text-animation-node} children)))))

(defn- populate-page-text-data
  [page-data scene-data text-name]
  (-> page-data
      (assoc :text {:name text-name
                    :data (get-in scene-data [:objects text-name])})
      (assoc :phrase-action-path (-> (get-phrase-node scene-data page-data text-name) (second) (:path)))))

(defn- page-in-stage?
  [scene-data stage-idx page-idx]
  (->> (utils/get-stage-data scene-data stage-idx)
       (:pages-idx)
       (some #{page-idx})))

(defn get-page-data
  [scene-data stage-idx text-name page-idx]
  (when (page-in-stage? scene-data stage-idx page-idx)
    (let [data (page-idx->data scene-data page-idx)]
      (if (some? text-name)
        (populate-page-text-data data scene-data text-name)
        data))))