(ns inside-out.core-test
  (:require [clojure.test :refer :all]
            [inside-out.core :refer :all]))


(deftest blank-board-test
  (testing "Create basic board."
    (is (= "|*|*|*|\n|*|*|*|\n|*|*|*|\n" (blank-board)))))

(deftest main-test
  (testing "Print out blank board"
    (is (= "|*|*|*|\n|*|*|*|\n|*|*|*|\n" (with-out-str (-main))))))