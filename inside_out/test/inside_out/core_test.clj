(ns inside-out.core-test
  (:require [clojure.test :refer :all]
            [inside-out.core :refer :all]))


(deftest a-test
  (testing "Create basic board."
    (is (= "|*|*|*|\n|*|*|*|\n|*|*|*|\n" (blank-board)))))
