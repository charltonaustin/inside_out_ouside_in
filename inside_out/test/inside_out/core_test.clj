(ns inside-out.core-test
  (:require [clojure.test :refer :all]
            [inside-out.core :refer :all]
            [clojure.string :as s]))
(def test-board [1 2 3 4 5 6 7 8 9])
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
    (is (= (assoc test-board 0 "X") (with-in-str (str 1 "\n") (ask-player-where-to-play test-board))))
    (is (= (assoc test-board 1 "X") (with-in-str (str 2 "\n") (ask-player-where-to-play test-board))))
    (is (= (assoc test-board 2 "X") (with-in-str (str 3 "\n") (ask-player-where-to-play test-board))))
    (is (= (assoc test-board 3 "X") (with-in-str (str 4 "\n") (ask-player-where-to-play test-board))))
    (is (= (assoc test-board 4 "X") (with-in-str (str 5 "\n") (ask-player-where-to-play test-board))))
    (is (= (assoc test-board 5 "X") (with-in-str (str 6 "\n") (ask-player-where-to-play test-board))))
    (is (= (assoc test-board 6 "X") (with-in-str (str 7 "\n") (ask-player-where-to-play test-board))))
    (is (= (assoc test-board 7 "X") (with-in-str (str 8 "\n") (ask-player-where-to-play test-board))))
    (is (= (assoc test-board 8 "X") (with-in-str (str 9 "\n") (ask-player-where-to-play test-board)))))
  (testing "Get a number between 1-9"
    (is (= "Please pick an available space [1-9].\nPlease pick an available space [1-9].\n"
           (with-out-str (with-in-str "b\n1\n" (ask-player-where-to-play test-board)))))))

(deftest computer-select-location-test
  (testing "" (is (true? (boolean (some #(= "O" %1) (computer-select-location test-board)))))))

(deftest check-across-winner-test
  (testing "that a board with a horizontal winner is found"
    (is (= "X" (check-horizontal-winner (assoc test-board 0 "X" 1 "X" 2 "X") "X")))
    (is (= "X" (check-horizontal-winner (assoc test-board 3 "X" 4 "X" 5 "X") "X")))
    (is (= "X" (check-horizontal-winner (assoc test-board 6 "X" 7 "X" 8 "X") "X")))
    (is (nil? (check-horizontal-winner (assoc test-board 0 "X" 1 "X" 2 "X") "O")))
    (is (nil? (check-horizontal-winner (assoc test-board 0 "X" 1 "O" 2 "X") "X")))))

(deftest check-vertical-winner-test
  (testing "that a board with a veritcal winner is found"
    (is (= "X" (check-vertical-winner (assoc test-board 1 "X" 4 "X" 7 "X") "X")))
    (is (= "X" (check-vertical-winner (assoc test-board 2 "X" 5 "X" 8 "X") "X")))
    (is (= "X" (check-vertical-winner (assoc test-board 3 "X" 6 "X" 9 "X") "X")))
    (is (nil? (check-vertical-winner (assoc test-board 1 "X" 4 "X" 7 "X") "O")))
    (is (nil? (check-vertical-winner (assoc test-board 1 "X" 4 "O" 7 "X") "X")))))


(deftest main-test
  (testing "Print out blank board"
    (is (.contains (with-out-str
                     (with-redefs [exit-now! (constantly "we exit now")]
                       (with-in-str "1\n2\n3\n4\n5\n6\n7\n8\n9\n" (-main))))
                   empty-board-str))))