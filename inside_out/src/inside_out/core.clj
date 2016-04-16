(ns inside-out.core
  (:gen-class)
  (:require [clojure.core.reducers :as r]))

(defn clear-screen []
  (print (str (char 27) "[;H"))
  (print (str (char 27) "[2J")))

(defn str-board [a-board]
  (reduce
    (fn [init x] (str init x))
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

(defn ask-player-where-to-play []
  (loop []
    (let [user-input (read-string (read-line))]
      (if (contains? (set (range 1 9)) user-input)
        user-input
        (do
          (println "Please pick a number between 1 and 9.")
          (recur))))))

(defn print-to-screen [str-rep]
  (clear-screen)
  (print str-rep))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (print-to-screen (str-board (board))))


