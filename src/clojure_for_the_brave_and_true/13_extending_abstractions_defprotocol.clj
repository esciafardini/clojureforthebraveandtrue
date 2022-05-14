(ns clojure-for-the-brave-and-true.13-extending-abstractions-defprotocol)

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

;THE ABSTRACTION IS DEFINED
(defprotocol Psychodynamics
  "Plumb the inner depths of your data types." ;don't even trip, this is just a docstring

  ;method signature (name, argument specification, docstring)
  (thoughts [x] "The data type's innermost thoughts")
  ;name     arg             docstring

  ;method signature
  (feelings-about [x] [x y] "Feelings about self or other")
  ;name           arg  args           docstring
  )

;uh OH
;methods cannot have rest arguments [NO & ARGS]

;THE ABSTRACTION IS IMPLEMENTED
;OKAY THEN
;the generic abstractions defined above are now being defined based on type
(extend-type clojure.lang.Keyword
  Psychodynamics
  (thoughts [x] (get {:gnarly "FUCK THAT" :sad "Oh god why" :happy "I think I'm God"} x))
  (feelings-about
    ([x] (get {:ice-cream "ITS GOOD" :meditation "ITS GOOD THOUGH" :books "THEYRE GOOD BRO"} x))
    ([x y] (assoc {:ice-cream "ITS GOOD" :meditation "ITS GOOD THOUGH" :books "THEYRE GOOD BRO"} x "damn I feel too much" y "I got 2 many feelings haha"))))

(thoughts :sad)
(thoughts :happy)
(feelings-about :ice-cream)
(feelings-about :ricky :james)


(extend-type java.lang.String
  Psychodynamics
  (thoughts [x] (str "I think the nutcracker is so go go " x))
  (feelings-about
    ([x] (str "HAHAHAHA " x " I FEEL THIS WAY ABOUT " x))
    ([x y] (str "THE NUTCARCKER BARBIE DOLL SPECIAL " x " I CAN NOT FEEL MY LEGS AND I FEEL SO " y))))

(thoughts "funked")
(feelings-about "driving")
(feelings-about "faeries" "christmas")
