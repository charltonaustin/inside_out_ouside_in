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
  (vec (range 1 10)))

(defn update-board [a-board position value]
  (assoc a-board (- position 1) value))

(defn ask-player-where-to-play [a-board]
  (loop []
    (println "Please pick an available space [1-9].")
    (let [user-input (read-string (read-line))]
      (if (contains? (set (filter number? a-board)) user-input)
        (update-board a-board user-input "X")
        (recur)))))

(defn print-to-screen [str-rep]
  (clear-screen)
  (print str-rep))

(defn computer-select-location [a-board]
  (let [position (rand-nth (filter number? a-board))]
    (update-board a-board position "O")))

(defn exit-now! []
  (System/exit 0))

(def turn-function
  (take 9 (cycle [ask-player-where-to-play computer-select-location])))

(defn check-horizontal-winner [a-board char]
  (loop [a-board a-board
         row 0]
    (if (> row 6)
      nil
      (if (every? #(= char %1) [(get a-board row) (get a-board (+ row 1)) (get a-board (+ row 2))])
        char
        (recur a-board (+ row 3))))))

(defn check-vertical-winner [a-board char]
  (loop [a-board a-board
         column 0]
    (if (> column 3)
      nil
      (if (every? #(= char %1) [(get a-board column) (get a-board (+ column 3)) (get a-board (+ column 6))])
        char
        (recur a-board (+ column 1))))))

(defn -main
  [& args]
  (loop [a-board (board)
         number-of-moves 0]
    (if (>= number-of-moves 9)
      (do
        (print-to-screen (str-board a-board))
        (exit-now!))
      (do
        (print-to-screen (str-board a-board))
        (let [player-function (nth turn-function number-of-moves)
              new-board (player-function a-board)]
          (recur new-board
            (inc number-of-moves)))))))


