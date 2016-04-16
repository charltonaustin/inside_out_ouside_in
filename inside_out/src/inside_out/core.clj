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
  (vec (map str (range 1 10))))

(defn update-board [a-board position value]
  (assoc a-board position value))

(defn ask-player-where-to-play []
  (loop []
    (println "Please pick a number between 1 and 9.")
    (let [user-input (read-string (read-line))]
      (if (contains? (set (range 1 10)) user-input)
        user-input
        (recur)))))

(defn print-to-screen [str-rep]
  (clear-screen)
  (print str-rep))

(defn exit-now! []
  (System/exit 0))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (loop [a-board (board)
         number-of-moves 0]
    (if (> number-of-moves 9)
      (exit-now!)
      (do
        (print-to-screen (str-board a-board))
        (let [user-input (ask-player-where-to-play)]
          (recur (update-board a-board user-input "X") (+ number-of-moves 2)))))))


