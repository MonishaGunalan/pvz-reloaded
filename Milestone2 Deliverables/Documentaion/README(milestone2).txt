SYSC 3110 - Project: MileStone2
===============================

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
Documentation files:
UML diagram
Sequence Diagrams	
JavaDoc files
	
	
	

