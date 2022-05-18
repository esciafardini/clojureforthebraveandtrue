(ns clojure-for-the-brave-and-true.13-extending-abstractions-defprotocol
  (:import [clojure_for_the_brave_and_true.13_extending_abstractions_defrecord WereWolf]))

;importing a defrecord real quick...nbd...
(WereWolf. "david" "jack")

;DEFPROTOCOLS?!!!?!?!?!?!?

;approximately 93.58 percent of the time you want to dispatch on TYPE (lol)

;enter Protocols
;more efficient than multimethods && built to be used for types

;Multimethod VS. Protocol?
;Multimethod is singular polymorphic operation
;Protocol is a collection of one or more polymorphic operations

(println "shit bitch this a lot of notes shit what the fuck")

;protocol methods are called Methods (similar to multimethod // similar to god damn JAVA methods)

;while MULTIMETHODS are dispatched on the return value of DISPATCH FUNCTIONS::::
;PROTOCOLS are dispatched based on the TYPE OF THE FIRST ARGUMENT

;methods cannot have rest arguments [NO & ARGS]

;THE ABSTRACTION IS IMPLEMENTED
;OKAY THEN
;the generic abstractions defined above are now being defined based on type

;THE ABSTRACTION IS DEFINED
(defprotocol Psychodynamics
  "Plumb the inner depths of your data types." ;don't even trip, this is just a docstring
  (thoughts [x] "The data type's innermost thoughts")
  ;name     arg             docstring
  (feelings-about [x] [x y] "Feelings about self or other"))
  ;name           arg  args           docstring

(extend-type clojure.lang.Keyword
  Psychodynamics
  (thoughts [x] (get {:gnarly "FUCK THAT" :sad "Oh god why" :happy "I think I'm God"} x))
  (feelings-about
    ([x] (get {:ice-cream "ITS GOOD" :meditation "ITS GOOD THOUGH" :books "THEYRE GOOD BRO"} x))
    ([x y] (assoc {:ice-cream "ITS GOOD" :meditation "ITS GOOD THOUGH" :books "THEYRE GOOD BRO"} x "damn I feel too much" y "I got 2 many feelings haha"))))

(extend-type java.lang.String
  Psychodynamics
  (thoughts [x] (str "I think the " x " is nutcracked"))
  (feelings-about
    ([x] (str "HAHAHAHA I FEEL AFRAID OF " x))
    ([x y] (str x " feels okay - but " y " feels great. "))))

(extend-type java.lang.Object
  ;since all java/clojure types are descendants of java.lang.Object,
  ;this is a default or catch-all
  Psychodynamics
  (thoughts [x] (str "Uh, I haven't really thought about " x ", sorry."))
  (feelings-about
    ([x] (str "HAHAHAHA I DO NOT FEEL ANYTHING ABOUT " x))
    ([x y] (str x " and/or " y "? Feel nothing. "))))

;option to keep everything in one place:
(extend-protocol Psychodynamics
  java.lang.String
  (thoughts [x] (str "I think the " x " is nutcracked"))
  (feelings-about
    ([x] (str "HAHAHAHA I FEEL AFRAID OF " x))
    ([x y] (str x " feels okay - but " y " feels great. ")))
  clojure.lang.Keyword
  (thoughts [x] (get {:gnarly "FUCK THAT" :sad "Oh god why" :happy "I think I'm God"} x))
  (feelings-about
    ([x] (get {:ice-cream "ITS GOOD" :meditation "ITS GOOD THOUGH" :books "THEYRE GOOD BRO"} x))
    ([x y] (assoc {:ice-cream "ITS GOOD" :meditation "ITS GOOD THOUGH" :books "THEYRE GOOD BRO"} x "damn I feel too much" y "I never felt about this before")))
  java.lang.Object
  (thoughts [x] (str "Uh, I haven't really thought about " x ", sorry."))
  (feelings-about
    ([x] (str "HAHAHAHA I DO NOT FEEL ANYTHING ABOUT " x))
    ([x y] (str x " and " y "? Feel nothing. "))))

(thoughts "other guy")
(feelings-about "driving")
(feelings-about "faeries" "christmas")
(thoughts :sad)
(thoughts :happy)
(feelings-about :ice-cream)
(feelings-about :ricky :james)

(thoughts 3)
(feelings-about {:a 123 :v 14242})
(feelings-about [1 2 3] '(111 1112 2232))


