(ns inside-out.core-test
  (:require [clojure.test :refer :all]
            [inside-out.core :refer :all]
            [clojure.string :as s]))
(def test-board ["*", "*", "*", "*", "*", "*", "*", "*", "*"])
(def empty-board-str "|*|*|*|\n|*|*|*|\n|*|*|*|\n")

(deftest blank-board-test
  (testing "Create empty board string."
    (is (=  empty-board-str (str-board test-board)))))

(deftest board-test
  (testing "Create empty board"
    (is (=  test-board (board)))))

(deftest update-board-test
  (testing "Update the board with a new value"
    (is (= ["*" "X"] (update-board ["*" "*"] 1 "X")))))

(deftest ask-player-where-to-play-test
  (testing "Get a value from the player"
    (is (= 1 (with-in-str "1\n" (ask-player-where-to-play)))))
  (testing "Get a number between 1-9"
    (is (= "Please pick a number between 1 and 9.\n"
           (with-out-str (with-in-str "b\n1\n" (ask-player-where-to-play)))))))

(deftest main-test
  (testing "Print out blank board"
    (is (.contains (with-out-str (-main)) empty-board-str))))