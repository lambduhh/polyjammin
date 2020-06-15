(ns polyjelly.sidecar
  (:require
   [polyjelly.db :as db]
   [polyjelly.config :as config]
   [ajax.core :as ajax
    :refer [GET POST]]))

(def db db/poly-db)
(def db' @db)




(comment


  (GET (str config/spotify-base-URL
            "/me/tracks")
       {:headers {"Authorization" (str "Bearer "
                                       (:access-token @db/poly-db))}
        :handler (fn [data]
                   (tap> data)
                   (def my-data data))}))
