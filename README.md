BLACK HOLE GAME
===============

A Java-based desktop implementation of the classic Black Hole board game. 
Players take turns moving their spaceships across a dynamically sized 
grid, attempting to be the first to pull half of their fleet into the 
center black hole.

The project demonstrates advanced Object-Oriented Programming and GUI 
development using Java Swing, featuring custom event listeners, 
collision detection, and grid-based logic.


OVERVIEW
--------

The game is played on an n x n board (selectable sizes: 5x5, 7x7, 9x9) 
with a black hole at the center. Each player starts with n-1 spaceships 
placed on opposite diagonals. 

Because of the black hole's gravity, spaceships cannot move just one 
step—they slide until they hit the edge of the board, another ship, or 
the black hole itself. A player wins by moving half of their ships into 
the hole. After a win, a message is displayed and a new game starts 
automatically.


FEATURES
--------

  * Interactive GUI: Built with Java Swing, featuring a clean grid of 
    buttons that respond to clicks.
  * Dynamic Movement: Implements "sliding" logic where ships move as far 
    as possible until they encounter an obstacle.
  * Board Size Selection: Players can change the board size (5x5, 7x7, 
    9x9) via the top menu at any time.
  * Visual Feedback: Selected ships are highlighted, and the status 
    label updates to show whose turn it is.
  * Collision Detection: Ships stop when they encounter other ships or 
    get sucked into the black hole.


PROJECT STRUCTURE
-----------------

  Source Code (src/blackhole/):
    - BlackHole.java         : The main entry point.
    - BlackHoleGUI.java      : The main JFrame and menu bar setup.
    - BoardGUI.java          : The visual grid of buttons and status label.
    - Board.java             : The core game logic, movement, and scoring.
    - Field.java             : Represents a single cell on the board.

  NetBeans Project Files:
    - build.xml              : NetBeans build script.
    - manifest.mf            : JAR manifest.
    - nbproject/             : NetBeans project configuration folder.


HOW TO COMPILE AND RUN
----------------------

Ensure you have Java installed (JDK 8 or newer is recommended).

Using NetBeans:
1. Open NetBeans.
2. Go to File -> Open Project and select the BlackHole project folder.
3. Press F6 (or click the Run Project button).

Using Windows PowerShell (Manual Compilation):
1. Open PowerShell in the root project folder.
2. Compile the source files into a `bin` directory:
   javac -d bin src\blackhole\*.java
3. Run the application:
   java -cp bin blackhole.BlackHole


LICENSE
-------

This program is provided as-is for educational and demonstration
purposes. Use and modify it freely.
