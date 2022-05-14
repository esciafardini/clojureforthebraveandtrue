(ns clojure-for-the-brave-and-true.13-extending-abstractions-defmulti)

;MULTIMETHODS?!?!?!?!?!?!?!?

;abstractions.....
;`red honkable spherical nose adornment` => `clown nose` 

;abstractions.....
;collection of operations & data types that implement abstractions

;SEQ ABSTRACTION
(first [1 2 3 4 5]) ; 1
(first {:a 1 :b 2 :c 3}) ; [:a 1]
(rest {:a 1 :b 2 :c 3}) ; ([:b 2] [:c 3])
(first '(1 2 3 4 5 6)) ; 1

;let's not forget our old fiend the Persistent Queue
(def que (atom clojure.lang.PersistentQueue/EMPTY))
(swap! que conj 12 123 1234)
(first @que) ; 12
(rest @ que) ; (123 1234)

;All implementations of the seq abstractions
;  respond to seq operations

;Vectors, Lists, Maps, Queues are INSTANCES of the seq data type


;POLYMORPHISM
;associating an operation name with more than one algorithm

;conj
;add an element to this data structure (regardless of type)

;the algorithm for performing conj is polymorphic

;Multimethods (A TOOL FOR POLYMORPHISM)
;
;associate a name with multiple implementations via dispatch fns


;defmulti => defines the dispatching function!
(defmulti at-the-show-behavior
  (fn [music-fan] (:archetype music-fan)))

(defmethod at-the-show-behavior :screamo-freak
  [music-fan]
  (str (:name music-fan) " will play with his phone between sets."))

(defmethod at-the-show-behavior :aggro-metal
  [music-fan]
  (str (:name music-fan) " will mosh and punch your friend."))

(defmethod at-the-show-behavior :sad-boi
  [music-fan]
  (str (:name music-fan) " will cry during the set."))

(defmethod at-the-show-behavior :default
  [music-fan]
  (str (:name music-fan) " is an unrecognized archetype. The council will determine their judgment in time."))

(defmethod at-the-show-behavior nil
  [music-fan]
  (str (:name music-fan) " DEFIES CATEGORIZATION"))

(at-the-show-behavior 
  {:name "Ricky"
   :archetype :aggro-metal}) ; "Ricky will mosh and punch your friend."

(at-the-show-behavior
  {:name "Felippe"
   :archetype :sad-boi}) ; "Felippe will cry during the set."

(at-the-show-behavior
  {:name "Max"
   :HP 90000001323})

(at-the-show-behavior
  {:name "Jimme"
   :archetype :bluegrass-boy
   :HP 90000001323})


;Based on the result of the dispatching function,
;  the specific method is used 

;1. The form is evaluated
;2. at-the-show-behavior's dispatch function is run, returning the
;   archetype as the dispatching value
;3. Clojure compares the dispatching value of :archetype to the dispatch values
;   of the methods
;4. When there is a match on the method dispatch value, the algorithm runs

;The dispatch function returns a value!
;The method is called based on that value!

;MORE DEFMULTI
(defmulti tell-me-the-types (fn [a b] [(class a) (class b)]))

(defmethod tell-me-the-types [java.lang.String java.lang.String]
  [a b]
  "TWO STRINGS")

(defmethod tell-me-the-types [java.lang.Long clojure.lang.Keyword]
  [a b]
  "A number! Then a keyword!")

(tell-me-the-types "what is" "ok no")
(tell-me-the-types 1000 :gecs)
