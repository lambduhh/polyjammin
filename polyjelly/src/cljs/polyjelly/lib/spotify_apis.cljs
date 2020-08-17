(ns polyjelly.lib.spotify-apis
  (:require
   [re-frame.core :as re-frame]
   [re-frame.core :refer [subscribe reg-event-fx]]

   [clj-http.client :as client]
   [day8.re-frame.http-fx]
   [ajax.core :as ajax]

   [polyjelly.config :refer spotify-base-URL]
   [polyjelly.subs :as db]
   [polyjelly.subs :as subs]))

(def token @db-poly-db)


(defn get-users-profile-success
  [{:keys [db]} [event-name response]]
  {:db (assoc db response)})

(reg-event-db :get-users-profile-success get-users-profile-success)


#_(defn get-users-profile-failure
  [{:keys [db]} [event-name response]]
  {:db (assoc db response)})

(re-frame/reg-event-fx
 :get-users-profile
 (fn [{:keys [db]} [event-name _]]
   (let [token
         (:access-token db)]
     {:db db
      :http-xhrio {:method :get
                   :headers (str "Authorization: Bearer " token)
                   :uri (str spotify-base-URL "me")
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success [:get-users-profile-success response]}})))
