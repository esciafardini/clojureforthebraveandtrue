(ns clojure-for-the-brave-and-true.13-extending-abstractions-defrecord)

;records are map like
;   associative
;   immutable
;
;but.... 
;you store fields for records

(defrecord WereWolf [name title])

(WereWolf. "David" "London Tourist")
(->WereWolf "Jacov" "Lead Shirt")
(map->WereWolf {:name "Lucian" :title "CEO of Melodrama"})  
; {:name "Lucian", :title "CEO of Melodrama"}

(map->WereWolf {:name "Lucian" :HUH? "CEO of Melodrama"})  
; {:name "Lucian", :title nil, :HUH? "CEO of Melodrama"}


;method 1:
; because records are classes, you can use java interop to initialize one

;when a record is made, the functions ->RecordName && map->RecordName are created

;method 2 & 3:
; these use the `factory functions` created by defrecord
; ->WereWolf takes values in the order they are defined
; map->WereWolf takes a map with matching keys

(def ted (->WereWolf "Ted" "The Ultimate Dogg"))

(:name ted)
(get ted :name)
(.name ted) ; FUNKYYY

;equality check says `na` when comparing records to maps
(= ted {:name "Ted" :title "The Ultimate Dogg"}) ; false
(= ted (map->WereWolf {:name "Ted" :title "The Ultimate Dogg"}))  ; true
;but they behave much the same

(assoc ted :weight "55 gigapounds") 
; {:name "Ted", :title "The Ultimate Dogg", :weight "55 gigapounds"}

;records are faster than maps w/r/t data access
;WARNINGGGGGG
(type ted) ; 13_extending_abstractions_defrecord.WereWolf
(type (assoc ted :ok "ricky get it")) ; 13_extending_abstractions_defrecord.WereWolf
(type (dissoc ted :name)) ; clojure.lang.PersistentArrayMap
;D'OH

;the most confusing part always comes at the end.......
(defprotocol Frick
  (how-it-does-it [x]))

(defrecord FrackleRock [fname archetype]
  Frick
  (how-it-does-it [x]
    (str "oh " fname " does it with the fizz on the top!!! that's becuz him is a " archetype)))

(how-it-does-it (map->FrackleRock {:fname "Johnston" :archetype "Mad Dingo"}))  
; "oh Johnston does it with the fizz on the top!!! that's becuz him is a Mad Dingo"

;(defrecord WereWolf [name title])
;this was our original bb record...
;pretty cool that they can take protocols and extend them huh?
