(ns polyjelly.db
  (:require [reagent.core :as reagent]))


(defonce poly-db (reagent/atom {}))


(def default-db
  {:name "re-frame"})
