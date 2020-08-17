(ns polyjelly.views
  (:require
   [re-frame.core :as rf]
   [re-com.core :as re-com]
   [polyjelly.subs :as subs]
   [polyjelly.db :as db]
   [polyjelly.config :as config]))

(defn title []
  (let [name
        (rf/subscribe [::subs/name])]
    [re-com/title
     :label (str "Hello from " @name)
     :level :level1]))

(defn re-direct []
  [:a {:href config/authlink}
   "authorize spotify"])




(defn button []
  [re-com/button
   :label "get-user button"
   :on-click #(rf/dispatch [:get-users-profile]) ])

(defn main-panel []
  [:div
   [re-direct]
   [:<>
    (when (:access-token @db/poly-db)
      [button])]])
