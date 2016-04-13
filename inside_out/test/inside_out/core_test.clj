(ns inside-out.core-test
  (:require [clojure.test :refer :all]
            [inside-out.core :refer :all]))
(def test-board '("*", "*", "*", "*", "*", "*", "*", "*", "*"))
(def empty-board-str "|*|*|*|\n|*|*|*|\n|*|*|*|\n")

(deftest blank-board-test
  (testing "Create empty board string."
    (is (=  empty-board-str (str-board test-board)))))

(deftest board-test
  (testing "Create empty board"
    (is (=  test-board (board)))))

(deftest main-test
  (testing "Print out blank board"
    (is (= empty-board-str (with-out-str (-main))))))