(ns inside-out.core
  (:gen-class)
  (:require [clojure.core.reducers :as r]))

(defn str-board [a-board]
  (reduce
    (fn [init x] (str init x))
    ""
    (map-indexed
      (fn [index value]
        (if (zero? (mod (inc index) 3))
          (str "|" value "|" "\n")
          (str "|" value)))
      a-board)))

(defn board []
  (vec (repeat 9 "*")))

(defn update-board [a-board position value]
  (assoc a-board position value))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (print (str-board (board))))


