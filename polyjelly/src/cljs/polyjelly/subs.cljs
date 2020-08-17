(ns polyjelly.subs
  (:require
   [re-frame.core :as re-frame]
   [polyjelly.db :as db]

   [re-frame.core :refer [reg-sub subscribe]]))

(def db @db/poly-db)

(re-frame/reg-sub
 :name
 (fn [db _]
   (:name db)))

(re-frame/reg-sub
 :user-token
 (fn [db _]
  (:access-token @db/poly-db)))

(comment
  (defn check-sub []
    (re-frame/subscribe [:user-token]))
  (check-sub))
