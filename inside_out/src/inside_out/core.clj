(ns inside-out.core
  (:gen-class))

(defn blank-board []
  "|*|*|*|\n|*|*|*|\n|*|*|*|\n")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (print (blank-board)))


