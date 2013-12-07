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

Level Builder Instruction
--------------------------

- Run the BuilderModel.java class to start the level builder
- Choose the level to  edit using the checkbox
- Select the terrain type for each row of the selected level 
- Enter the number of zombies for each row of grass terrain type
- Select the type and enter the turn number for each zombie.
- After all the information is entered for each level click the "Write to   File Button" to write to the file

- Do the same for the next selected level if any left.

-At the end of the level selections. Choose to quit or reset

- Quit: quits the level builder
- Reset: undo all the changes made in the level builder and set to default level values

The project contains 5 text files for each level and it already has the information required for each level from previous milestones. The levels created using the level builder overwrites the exiting default level files. If you choose to "RESET" the file is reset to the default values and the new changes made to the file using the level builder is deleted.	
