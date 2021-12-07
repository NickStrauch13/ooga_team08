ooga
====

This project implements a player for multiple related games.

Names: Nick Strauch, Nicholas Conterno, Abhinav Ratnagiri, Neil Mosca, David Wu


### Timeline

Start Date: 11/1/21

Finish Date: 12/6/21

Hours Spent: 486

### Primary Roles
Front-end: Nick S and Neil
Back-end: Nick C and Abhinav
Controller & Data Parsing: David

### Resources Used
https://www.javatpoint.com/how-to-read-csv-file-in-java
https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
https://www.geeksforgeeks.org/
https://stackoverflow.com/questions/tagged/java

### Running the Program

Main class: src/Main.java

Data files needed: All files in view/resources (properties, css, and png files for display), All files in models/resources folder and models/creature/resources.
Also need to load in one game file from the initial UI in order to start actual gameplay, all potential game files in data/game folder.

Features implemented: Upon running Main, the program will bring up a UI with three main buttons, a field for the Users name, 
the User's high score and has a couple drop down menus that allow the user to change different settings.
The three buttons are High Scores, New Game, and Build Board. The High Score button allows the user to see the ten highest scores
that have been achieved and what user achieved that score. The New Game button will pull up a file selector allowing
the user to load in a json file representing a game to play. After selecting a file, a game will appear and will be
playable. The Build Board button brings the user to a new page that allows them to create a board using an
interactive UI. The board created by the user is converted to a json file and is loaded to allow the user to play the game
on the game board that they created themselves. The Username text field allows the user to input their name to be used in their
player profile. This name will appear below the text field and display the user's top score (pulled from a csv file). After starting
a game, the user will still be able to see their player profile, and it will show their previous best score. After finishing a game,
the user's score will be added to the high score csv file (and will display when High Score button is pressed if the score is high enough).
The game that the user can play has an "unlimited" number of variations. The game has user controlled pieces and computer controlled
pieces. These pieces can be specified in the data file. The game also supports various pickups that do things like add score,
add lives, slow computer controlled creatures, teleport user controlled pieces, etc. By combining these game elements in various ways,
you can create many types of games. The game variants that we have made include Pacman, mrsPacman, Extreme Pacman (custom powerups), 
Maze Game, and even a puzzle game. Other variants could easily be made such as an "escape" game where the user has to get pickups to get teleported into
various rooms before the user can escape. Another possible variant would be a survival game where the user has to run away from
the computer controlled creatures for as long as possible while the timer keeps track of the user's time. From the game screen,
the user can also reset the game using the Reset button, the user can return to the home page, and the user can play and pause
the current game.




### Notes/Assumptions

Assumptions or Simplifications: 
* The ghosts do not spawn in a box like how they do in actual pacman. Instead, the user can place the ghosts wherever they want in the data file.
* 

Interesting data files:

Known Bugs:
* It is extremely rare, but sometimes the ghosts "hesitate" before eating you when you are not moving. This has to do with the BFS
path finding algorithm making the ghost think it is already in the same location as the pacman when it really isn't.

Challenge Features:


### Impressions

