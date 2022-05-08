(ns clojure-for-the-brave-and-true.12-working-with-jvm)

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


