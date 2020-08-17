(ns polyjelly.config)

(def client-id "8398a53b14db4dbbb71bd850af30351b")

(def authlink (str "https://accounts.spotify.com/authorize?client_id=" client-id
                   "&redirect_uri=http:%2F%2Fpolyjamoury.lvh.me:8280&
scope=user-read-private%20user-top-read%20user-library-read&response_type=token&state=123
"))

(def spotify-base-URL "https://api.spotify.com/v1/")

(def debug?
  ^boolean goog.DEBUG)
