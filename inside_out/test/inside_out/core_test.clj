(ns inside-out.core-test
  (:require [clojure.test :refer :all]
            [inside-out.core :refer :all]
            [clojure.string :as s]))
(def test-board ["1", "2", "3", "4", "5", "6", "7", "8", "9"])
(def empty-board-str "|1|2|3|\n|4|5|6|\n|7|8|9|\n")

(deftest blank-board-test
  (testing "Create empty board string."
    (is (= empty-board-str (str-board test-board)))))

(deftest board-test
  (testing "Create empty board"
    (is (= test-board (board)))))

(deftest update-board-test
  (testing "Update the board with a new value"
    (is (= ["X" "*"] (update-board ["*" "*"] 1 "X")))))

(deftest ask-player-where-to-play-test
  (testing "check that all values work"
    ; this should be parameterized
    (is (= 1 (with-in-str (str 1 "\n") (ask-player-where-to-play))))
    (is (= 2 (with-in-str (str 2 "\n") (ask-player-where-to-play))))
    (is (= 3 (with-in-str (str 3 "\n") (ask-player-where-to-play))))
    (is (= 4 (with-in-str (str 4 "\n") (ask-player-where-to-play))))
    (is (= 5 (with-in-str (str 5 "\n") (ask-player-where-to-play))))
    (is (= 6 (with-in-str (str 6 "\n") (ask-player-where-to-play))))
    (is (= 7 (with-in-str (str 7 "\n") (ask-player-where-to-play))))
    (is (= 8 (with-in-str (str 8 "\n") (ask-player-where-to-play))))
    (is (= 9 (with-in-str (str 9 "\n") (ask-player-where-to-play)))))
  (testing "Get a number between 1-9"
    (is (= "Please pick a number between 1 and 9.\nPlease pick a number between 1 and 9.\n"
           (with-out-str (with-in-str "b\n1\n" (ask-player-where-to-play)))))))

(deftest computer-select-location-test
  (testing ""
    (testing (is (true? (boolean (some #(= "O" %1) (computer-select-location test-board))))))))

(deftest main-test
  (testing "Print out blank board"
    (is (.contains (with-out-str
                     (with-redefs [exit-now! (constantly "we exit now")]
                       (with-in-str "1\n2\n3\n4\n5\n" (-main))))
                   empty-board-str))))