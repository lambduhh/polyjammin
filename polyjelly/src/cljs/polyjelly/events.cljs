(ns polyjelly.events
  (:require
   [re-frame.core :as re-frame]
   [polyjelly.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   ))

(re-frame/reg-event-db
 :initialize-db
 (fn [db]
   @db/poly-db))



(defn initialize-page [])
