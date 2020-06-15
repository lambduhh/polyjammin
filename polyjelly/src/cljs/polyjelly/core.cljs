(ns polyjelly.core
  (:require
   [reagent.core :as reagent]
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [polyjelly.events :as events]
   [polyjelly.views :as views]
   [polyjelly.config :as config]
   [polyjelly.db :as db]
   [polyjelly.sidecar :as sidecar]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn get-token []
  (-> js/window.location.href
      (clojure.string/replace #"http://polyjamoury.lvh.me:8280/#access_token="
                              "")
      (clojure.string/replace #"&.*"
                              "")))

(defn init []
  (swap! db/poly-db assoc :access-token (get-token))
  (dev-setup)
  (mount-root))
