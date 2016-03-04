(ns outside-in.core-test
  (:require [clojure.test :refer :all]
            [outside-in.core :refer :all])
  (:import (java.io ByteArrayInputStream InputStreamReader BufferedReader)))


(defn create-out-put [message]
  (new BufferedReader (new InputStreamReader (new ByteArrayInputStream (.getBytes message)) "UTF-8")))

(deftest function-test
  (binding [*in* (create-out-put "Charlie!")]
    (let [application-output (with-out-str (-main))]
      (is (= application-output "Hello, Charlie!\n")))))
