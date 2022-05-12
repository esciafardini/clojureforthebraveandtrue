(ns clojure-for-the-brave-and-true.12-working-with-jvm 
  (:require
   [clojure.string :as str])
  (:import
   [java.util Date Stack]))

;JAR FILES
;allows dev to bundle all class files into a single file
;AAAANNNNNDDDDDDDD
;java can run these jar files which means anyone with java install can utilize a clojure program

;JAVA INTEROP
(.toUpperCase "What the World") ; "WHAT THE WORLD"
;=>
; "What the World".toUpperCase();

(.indexOf "Let's synergize our bleeding edges" "y") ; 7
;=>
; "Let's synergize our bleeding edges".indexOf("y");

(def string "foo\nbar\nbaz")

(let [vec-of-strs (str/split string #"\n")]
  (mapv (fn [s] {:line-number (.indexOf vec-of-strs s) :text s}) vec-of-strs))

;abs is the method
;java.lang.Math is the class
(java.lang.Math/abs -3) 
;=> 3
;
;java.lang classes are auto-imported to all Clojure projects

;Same class, but this time
;the static field PI
(java.lang.Math/PI) ; 3.141592653589793

;DOT SPECIAL FORM
;A Macro that enables java interop
(macroexpand-1 '(.toUpperCase "Yeaaaa"))  ; (. "Yeaaaa" toUpperCase)

(. "yeaaaaha" toUpperCase) ; "YEAAAAHA"

(macroexpand-1 '(java.lang.Math/abs -584)) ; (. java.lang.Math abs -584)

(. java.lang.Math abs -392) ; 392

; (object-expr-or-classname-symbol method-or-member-symbol optional-args*)

;Want to make a java object in Clojure? (vomits)
;A LIFO DATA STRUCTURE
(def stax (java.util.Stack.))
(.push stax 4343)
(.pop stax)
;mutable state, altering stax directly within Clojure using Java methods

;to see the new stack at the end of MF call
(doto stax 
  (.push "denise")
  (.push "richards")
  (.push "richard"))

(doto stax
  .pop
  .pop)

;oh shit you can call some seq fns on the mutable Stack
(count stax) ; 7

(def ricky (new java.util.Stack))

(doto ricky 
  (.push  "rikk"))

;^^^ DIFFERNT WAZE 2 DO THE SAME THANG

;FIFO DATA STRUCTURE
(def FIFO (atom clojure.lang.PersistentQueue/EMPTY))
(swap! FIFO conj 7)
(swap! FIFO conj 9)

;off topic, just remembered that weird clojure data structure....

(def LIFO (atom []))
(swap! LIFO conj 7)
(swap! LIFO conj 9)

;look at this difference tho:
(swap! LIFO pop)
(swap! FIFO pop)
;This means vectors are LIFO and PersistentQueues are FIFO

;NOTICE
;You can do clojure fns like `count` and `first` on java objects
;You can NOT do clojure fns like `conj` and `pop` on java objects

;doto is a macro for java objects similar to threadfirst ->
;doto puts the object in a let binding & then returns it at the end

(def java-stack (new java.util.Stack))
(.push java-stack 77)
(count java-stack)

;I AM HAVING FUN HAAHAHAHAHAH

;import some java classes in da namespace above
(import java.util.Stack)
(new Stack)
(Stack.)

(new Date)
(new Stack)
