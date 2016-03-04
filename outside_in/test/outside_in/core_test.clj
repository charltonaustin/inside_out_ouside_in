(ns outside-in.core-test
  (:require [clojure.test :refer :all]
            [outside-in.core :refer :all])
  (:import (java.io ByteArrayInputStream InputStreamReader BufferedReader)))

;ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
;System.setIn(in);

(defn write-to-stdin []
  (let [input-stream (new ByteArrayInputStream (.getBytes "Charlie"))]
    (System/setIn input-stream)))

(deftest a-test
  (binding [*in* (new BufferedReader (new InputStreamReader (new ByteArrayInputStream (.getBytes "Charlie!")) "UTF-8"))]
    (let [application-output (with-out-str (-main))]
      (is (= application-output "Hello, Charlie!\n")))))
