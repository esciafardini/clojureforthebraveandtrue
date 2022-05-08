(ns clojure-for-the-brave-and-true.11-core-async
  (:require [clojure.core.async
             :as a
             :refer [<! >! >!! <!! go chan buffer close! thread alts! alts!! timeout]]))

(let [c (chan)]
  ;future creates a thread that writes to the channel
  (future
    (dotimes [n 5]  (>!! c (str "Thread 1: " n))))
  (future
    (dotimes [n 6]  (>!! c (str "Thread 2: " n))))
  ;future creates another thread that pulls out the value
  (future
    ;this is what is causing the print outs - note the order
    (dotimes [n 11]  (println (<!! c)))))
