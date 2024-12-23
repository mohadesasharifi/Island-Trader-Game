To build and run Island Trader Game from the command line:
-----------------------------------------------------
1. To import the jar file in Eclipse, create a new project inside the workspace dir and follow the instracutions below:
	a. File -> New -> Java Project
	b. Import -> General -> File System then choose files.


2. Ensure you are in the root project directory. This directory contains this README, the src and doc directories.
	To run the jar file ensure Java 14 is installed on the machine, and enter the following command:
	java -jar zkh22_msh233_Island_Trader.jar

3. Run the following command to compile the Java source code and place the resulting compiled classes into the
   bin directory:

	javac -d bin -cp src src/main/Main.java

4. To start Island Trader with a graphical user interface run:

     	java -cp bin main.Main

   To start Island Trader with a command line interface run:

	java -cp bin main.Main cmd






