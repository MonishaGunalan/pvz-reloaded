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
javac -cp src/ src/pvz/GUI/BuilderController.java -d bin
javac -cp src/ src/test/*.java -d bin


How to play Game
-----------------------------------------------------------------------
The main methods are located in

pvz.level.GameModel for text version
pvz.GUI.GameController for GUI


How to play Level builder
----------------------------------------------------------------------
The main methods are located in

pvz.GUI.BuilderController


How to run using linux commands 
---------------------------------------------------------------------
Navigate to the Source files folder
java -cp bin/ pvz.level.GameModel
java -cp bin/ pvz.GUI.GameController
java -cp bin/ pvz.GUI.BuilderController
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
	
Additions 
--------------------------------------
Real Time added for bonus

-An option for real time is added when starting the gui.
-Pause and Resume added for gui
-Redo and Undo pauses the game
-Save and load the game
	
Fixes from previous milestones
------------------------------
Added test for
		GameModel
		New zombies and plants

Bug Fixes
	-Undoing then continuing cause any zombies on screen to remain stuck
	The Serializable did not restore observer/observable, created SerializableObservable to fix
	
