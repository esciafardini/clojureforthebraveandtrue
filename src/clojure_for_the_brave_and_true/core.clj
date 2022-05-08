(ns clojure-for-the-brave-and-true.core
  (:gen-class)
  (:require
   [clojure.string :as str]))

(vals (group-by type [1 :a 2 :b 3 :c]))

(def my-seq (map #(str/replace % #"[^a-zA-Z0-9]" "")
                 (str/split "Fools fall for foolish follies." #" ")))

(def lower-case-comparator
  (fn [a b] (compare (str/lower-case a) (str/lower-case b))))

(fn [strr] (sort (fn [a b] (compare (str/lower-case a) (str/lower-case b)))
                 (map #(str/replace % #"[^a-zA-Z0-9]" "")
                      (str/split strr  #" "))))

;comparator - its a function that takes two arguments 
;  & sorts with a compare

(defn -main
  "Could it be run as a jar bro?"
  []
  (doseq [string my-seq]
    (println string))
  (println "This clojure program has been run by java bro. CAN YOU BLEIVETDAT SHIT")
  (println "WOW"))

