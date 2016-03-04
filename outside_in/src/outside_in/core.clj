(ns outside-in.core
  (:gen-class))

(defn -main
  [& args]
  (println (str "Hello, " (read-line))))
