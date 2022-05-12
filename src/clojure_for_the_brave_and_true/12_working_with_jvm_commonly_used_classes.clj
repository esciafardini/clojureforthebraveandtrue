(ns clojure-for-the-brave-and-true.12-working-with-jvm-commonly-used-classes
  (:import
   [java.util Date Stack]))
;ok bro, almost done this chapter

;THE SYSTEM CLASS
;useful fields and methods for interacting with the environment your program is running in

;all Environment Variables via getenv
(keys (System/getenv))

;this shit will kill your repl (and/or your program):
(comment 
(System/exit 0))

;You can read JVM properties

;the directory the JVM started from
(System/getProperty "user.dir")
;=> "/Users/neverhood/clojure_for_the_brave_and_true"

;the version of the running JVM
(System/getProperty "java.version")
;=> "18.0.1"

(new java.util.Date) ; #inst "2022-05-12T17:37:05.147-00:00"

;my failure unobscured and commented out
;(.format (new Date));

(.format (java.text.SimpleDateFormat. "MM/dd/yyyy") (Date.))
;wellllllll shittttt
;guess it needed a call to formatter

(.format (new java.text.SimpleDateFormat "MM/dd/yyyy") (new Date))
;I hate this dot syntax....
;but I feel it will come back to visit me in codebases.....
;all it does it create a new instance
;ok ok ok ok ok ok ok ok ok ok ok ok ok 


