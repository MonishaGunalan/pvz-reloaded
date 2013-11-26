SYSC 3110 - Project: MileStone2
===============================

How to compile and run using eclipse
----------------------------------------------------------------------
Open Eclipse
File -> new -> project -> java project
Uncheck the "Use default location" box and provide the location to the folders you have checked out

Import the Jars in the folder Source files and add it to your build path
Add JUnit4 library to the build path.



How to compile using linux commands
-----------------------------------------------------------------------
Navigate to the Source files folder
Add JUnit4 jars to your classpath
javac -cp src/ src/pvz/GUI/GameController.java -d bin
javac -cp src/ src/test/*.java -d bin


How to run
-----------------------------------------------------------------------
The main methods are located in

pvz.level.GameModel for text version
pvz.GUI.GameController for GUI



How to run using linux commands 
---------------------------------------------------------------------
Navigate to the Source files folder
java -cp bin/ pvz.level.GameModel
java -cp bin/ pvz.GUI.GameController
java -cp bin/ test.AllTests


Authors
-------
Chris Nguyen 100793244
Tianming Zhuang  100875151
Arzaan Irani 100826631
Monisha Gunalan 100871444


Git Repository Link
--------------------
https://github.com/tmzhuang/pvz-reloaded.git


Deliverables
------------

Source Files
Input file: 
	level1.text
	level2.txt
	level3.txt
	level4.txt
	level5.txt
Documentation files:
UML diagram
Sequence Diagrams	
JavaDoc files
	
	
Comments
-----------
Data structure fix from milestone 1 using  array of ArrayList of Map.Entry to an arraylist with its own objects encapsulatint
Package variables are only used in the gui so the controller and view doesn't have to have so many getters
