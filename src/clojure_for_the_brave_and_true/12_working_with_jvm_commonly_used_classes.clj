(ns clojure-for-the-brave-and-true.12-working-with-jvm-commonly-used-classes
  (:import
   [java.util Date Stack]
   [java.io File StringReader StringWriter]))
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

;hello stack, my old friend
(def richard (new Stack))

;THE HOME STRETCH:
;LOL STRETCH: LOL

;File IO
;Java's Approach to IO thru the lens of Clojure

;IO involves resources (be they files, sockets, buffers, whatever)
(def my-beautiful-file (new File "/"))
;does the file exist
(println (.exists my-beautiful-file))
;is the file readable/writable?
(println (.canRead my-beautiful-file))
(println (.canWrite my-beautiful-file))
;where is the file?
(println (.getPath my-beautiful-file))

;OKAY THEN>>>>

;all reader classes have `read` `close`
;all writer classes have `write` `close` `flush` `append`

;I suppose I don't truly understand what the new File is doing
;looks like I cannot target my personal file system?
(spit "~/rick.txt" "hello rick")
; (err) Execution error (FileNotFoundException) at java.io.FileOutputStream/open0 (FileOutputStream.java:-2).
; (err) ~/rick.txt (No such file or directory)

;maybe the JVM has it's own directory/file structure?
(spit "/tmp/ricky" "end up in the hospital changing my shape I feel like an accident\nOH YEAH\nONCE IN A WHILE")
(slurp "/tmp/ricky")
;seems to be the case...

;Strings can also be IO'd upon:

(let [s (new StringWriter)]
  (spit s "I am still waiting")
  (spit s " - remain in light\n OKAY")
  (.toString s))


(let [s (new StringReader "Wowee Zowee")]
  (slurp s))

;Utilize the with-open macro becuz it closes the resource at the end of its body
(with-open [richard-the-file (clojure.java.io/reader "/tmp/ricky")]
  (println (second (line-seq richard-the-file))))

;line-seq is specific to readers....converts all the lines into a lazy seq! NEAT!
