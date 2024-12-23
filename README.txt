To build and run Island Trader Game from the command line:
-----------------------------------------------------
 to import the jar file in Eclipse, create a new project inside the workspace dir and follow the instracutions below:
	a. File -> New -> Java Project
	b. Import -> General -> File System then choose files.



1. Ensure you are in the root project directory. This directory contains this README, the src and doc directories.

2. Run the following command to compile the Java source code and place the resulting compiled classes into the
   bin directory:

	javac -d bin -cp src src/main/Main.java

3. To start RocketManager with a graphical user interface run:

     	java -cp bin main.Main

   To start RocketManager with a command line interface run:

	java -cp bin main.Main cmd






