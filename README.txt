SYSC 3110 - Project
====================

How to compile and run using eclipse
----------------------------------------------------------------------
Open Eclipse
File -> new -> project -> java project
Uncheck the "Use default location" box and provide the location to the folders you have checked out



How to compile using linux commands
-----------------------------------------------------------------------
Navigate to the main folder
Add JUnit4 jars to your classpath
javac -cp ".;src/;$CLASSPATH" src/pvz/*.java
javac -cp ".;src/;$CLASSPATH" src/test/*.java


How to run
-----------------------------------------------------------------------
The main methods are located in

GameModel.java for text version
GameFrame.java for GUI



How to run using linux commands 
---------------------------------------------------------------------
Navigate to the main folder
java -cp ".;src/" pvz.GameModel
java -cp ".;src/" pvz.GameFrame
java -cp ".;src/$CLASSPATH" test.AllTests


Milestone 2
-----------

Authors
-------
Chris Nguyen 100793244
Tianming Zhuang  100875151
Arzaan Irani 
Monisha Gunalan 100871444

Deliverables
------------

Source Files
Input file: 
	level1.text 
Documentation files:
	Design-Documentaion.txt
UML diagram:
	pvz-reloaded.png 
Sequence Diagram :
	sequencediagram/GameModelSD.png			---Main sequence diagram, the others are just frames used in the main diagram
	sequencediagram/GetPlayCommandSD.png
	sequencediagram/GrowSD.png
	sequencediagram/levelCreationSD.png
Image folder:
	rsrc/*.png
	
JavaDoc files
	Combined javaDoc/
	
	

