(ns webchange.question.common.voice-over-button
  (:require
    [webchange.question.common.params :as params]
    [webchange.question.utils :refer [get-voice-over-tag merge-data]]))

(def default-size 80)

(defn- create-background
  [{:keys [object-name size]}]
  (let [{active-colors :active default-colors :default} (:color params/voice-over)]
    {:objects {(keyword object-name) {:type          "rectangle"
                                      :x             0
                                      :y             0
                                      :width         size
                                      :height        size
                                      :border-radius (/ size 2)
                                      :fill          (:background default-colors)
                                      :states        {:default {:fill (:background default-colors)}
                                                      :active  {:fill (:background active-colors)}}}}}))

(defn- create-icon
  [{:keys [object-name size]}]
  (let [icon-width 41
        icon-height 32
        {active-colors :active default-colors :default} (:color params/voice-over)]
    {:objects {(keyword object-name) {:type       "svg-path"
                                      :scene-name "letter-tutorial-trace"
                                      :data       "M20.86 0.199576C20.5352 0.0590597 20.1789 0.00723956 19.8276 0.0494034C19.4762 0.0915673 19.1423 0.226205 18.86 0.439575L9.3 7.99958H2C1.46957 7.99958 0.960859 8.21029 0.585786 8.58536C0.210714 8.96043 0 9.46914 0 9.99957V21.9996C0 22.53 0.210714 23.0387 0.585786 23.4138C0.960859 23.7889 1.46957 23.9996 2 23.9996H9.3L18.76 31.5596C19.1119 31.8419 19.5489 31.997 20 31.9996C20.2987 32.0045 20.5941 31.9358 20.86 31.7996C21.2003 31.6375 21.4879 31.3825 21.6897 31.064C21.8914 30.7455 21.9989 30.3765 22 29.9996V1.99958C21.9989 1.6226 21.8914 1.25361 21.6897 0.935133C21.4879 0.616658 21.2003 0.361673 20.86 0.199576ZM18 25.8396L11.24 20.4396C10.8881 20.1573 10.4511 20.0022 10 19.9996H4V11.9996H10C10.4511 11.997 10.8881 11.8419 11.24 11.5596L18 6.15957V25.8396ZM35.32 4.67958C34.9434 4.30297 34.4326 4.09139 33.9 4.09139C33.3674 4.09139 32.8566 4.30297 32.48 4.67958C32.1034 5.05618 31.8918 5.56697 31.8918 6.09958C31.8918 6.63218 32.1034 7.14297 32.48 7.51958C33.6577 8.69545 34.5779 10.1034 35.1823 11.6541C35.7866 13.2047 36.0617 14.8641 35.9902 16.5268C35.9188 18.1895 35.5022 19.8192 34.7671 21.3122C34.0319 22.8053 32.9943 24.1291 31.72 25.1996C31.4105 25.4642 31.1892 25.8169 31.0856 26.2107C30.982 26.6045 31.001 27.0205 31.1402 27.4032C31.2793 27.7858 31.5319 28.1169 31.8643 28.3521C32.1966 28.5874 32.5929 28.7156 33 28.7196C33.4673 28.7205 33.9202 28.5577 34.28 28.2596C35.9819 26.8342 37.3685 25.0702 38.3517 23.0798C39.3349 21.0895 39.8932 18.9163 39.991 16.6985C40.0888 14.4807 39.7241 12.2668 38.9199 10.1977C38.1157 8.12848 36.8898 6.24928 35.32 4.67958ZM29.66 10.3396C29.4735 10.1531 29.2521 10.0052 29.0085 9.90425C28.7649 9.80333 28.5037 9.75139 28.24 9.75139C27.9763 9.75139 27.7152 9.80333 27.4715 9.90425C27.2279 10.0052 27.0065 10.1531 26.82 10.3396C26.6335 10.5261 26.4856 10.7474 26.3847 10.9911C26.2838 11.2347 26.2318 11.4959 26.2318 11.7596C26.2318 12.0233 26.2838 12.2844 26.3847 12.5281C26.4856 12.7717 26.6335 12.9931 26.82 13.1796C27.5712 13.9263 27.9955 14.9404 28 15.9996C28.0005 16.5823 27.8736 17.158 27.6284 17.6866C27.3831 18.2152 27.0253 18.6837 26.58 19.0596C26.3775 19.2275 26.2101 19.4337 26.0874 19.6664C25.9647 19.8991 25.8891 20.1537 25.8649 20.4156C25.8408 20.6776 25.8685 20.9417 25.9466 21.1929C26.0247 21.4441 26.1516 21.6775 26.32 21.8796C26.4893 22.0806 26.6966 22.2464 26.93 22.3672C27.1634 22.4881 27.4184 22.5618 27.6803 22.5841C27.9422 22.6064 28.2059 22.5769 28.4564 22.4972C28.7069 22.4174 28.9392 22.2891 29.14 22.1196C30.0342 21.3698 30.7536 20.4335 31.2477 19.3763C31.7417 18.319 31.9985 17.1665 32 15.9996C31.9887 13.8798 31.1489 11.8485 29.66 10.3396Z"
                                      :x          (- (/ size 2) (/ icon-width 2))
                                      :y          (- (/ size 2) (/ icon-height 2))
                                      :width      icon-width
                                      :height     icon-height
                                      :fill       (:icon default-colors)
                                      :states     {:default {:fill (:icon default-colors)}
                                                   :active  {:fill (:icon active-colors)}}}}}))

(defn create
  [{:keys [object-name x y size question-id value on-click on-click-params]
    :or   {x     0
           y     0
           size  default-size
           value "task"}}]
  (let [background-name (str object-name "-background")
        icon-name (str object-name "-icon")

        action-activate-name (str object-name "-activate")
        action-inactivate-name (str object-name "-inactivate")

        {activate-tag       :activate
         inactivate-tag     :inactivate
         inactivate-all-tag :inactivate-all} (get-voice-over-tag {:question-id question-id :value value})

        actions (cond-> {}
                        (some? on-click) (assoc :click (cond-> {:type       "action"
                                                                :on         "click"
                                                                :id         on-click
                                                                :unique-tag "question-action"}
                                                               (some? on-click-params) (assoc :params on-click-params))))]
    (merge-data {:objects {(keyword object-name) {:type     "group"
                                                  :x        x
                                                  :y        y
                                                  :children [background-name icon-name]
                                                  :actions  actions}}
                 :actions {(keyword action-activate-name)   {:type "sequence-data"
                                                             :tags [activate-tag]
                                                             :data [{:type   "state"
                                                                     :target background-name
                                                                     :id     "active"}
                                                                    {:type   "state"
                                                                     :target icon-name
                                                                     :id     "active"}]}
                           (keyword action-inactivate-name) {:type "sequence-data"
                                                             :tags [inactivate-all-tag inactivate-tag]
                                                             :data [{:type   "state"
                                                                     :target background-name
                                                                     :id     "default"}
                                                                    {:type   "state"
                                                                     :target icon-name
                                                                     :id     "default"}]}}}
                (create-background {:object-name background-name
                                    :size        size})
                (create-icon {:object-name icon-name
                              :size        size}))))
