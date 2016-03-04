(ns outside-in.core-test
  (:require [clojure.test :refer :all]
            [outside-in.core :refer :all]))

(deftest a-test
  (let [application-output (with-out-str (-main))]
    (is (= application-output "Hello, World!\n"))))
