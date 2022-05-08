##DAMN DANIEL
#A playground for me to re-visit, REPL-ify, and document concepts & exercises in `Clojure For The Brave & True` by Daniel Higginbotham

#Chapter 11

core async implements CSP
`communicating sequential processes`

Inidividual process 
communicating with eachother
over a communication method

This communication method is the channel (chan)
`many to many channel`
This channel allows multiple writers and multiple readers
The channel will perform all the necessary blocking
so that race conditions / memory overriding does not occur

>  The arrow points in the direction that the data is going
!  The first exclamation mark signifies a side effect-y operation on the channel
!  The second exclamation mark says this may block the thread

Channels (like futures) implement a First In First Out queue



#Chapter 12

The power of the JVM
1. You can run Clojure programs (as jar files) with the `java` command
2. You can use Java core functionality & objects if desired
3. You can use all them Java libraries within your Clojure programs

JVM (abstraction): General Model 
JVM (process): an instance of a running program

1. Java compiler compiles classes and source code into java bytecode
2. Compiler outputs bytecode into a JAR file
3. JVM executes the java bytecode (JAR)
4. The JVM sends machine instructions to the CPU

Ok Smarty, You have a java class sitting in a project
Next step to getting the thing running is to compile the source code:

`javac PiratePhrases.java`
will generate a compiled

1. you have a file called `DipShit.java`
2. you compile the file `javac DipShit.java`
3. you have two files now => `DipShit.java` and `DipShit.class`
    SO NOW: the DipShit.class file has *compiled* java bytecode that can be executed
4. execute the program by typing in terminal `java DipShit` - NOTICE: no file extension

##CLASSPATH
What the FUDGE is the classpath
`By default, the classpath includes the directory YOU ARE IN when you run the java command`
`The CLASSPATH is the list of filesystem paths that the JVM searches to find a file that defines a class`
Ok Feynman Time
Filesystem paths exist in the hierarchical structure. These paths are set...somewhere?....and the JVM can search along these paths to find....
classes?

Okay, I think that makes sense.



