Object Oriented Programming - Pacman Project
====

This project implements a player for multiple related games.

Names: Nick Strauch, Nicholas Conterno, Abhinav Ratnagiri, Neil Mosca, David Wu


### Timeline

Start Date: 11/1/21

Finish Date: 12/6/21

Hours Spent: 500+

### Primary Roles

- Front-end: Nick S, and Neil

- Back-end: Nick S, Nick C, and Abhinav

- Controller & Data Parsing: David

### Resources Used

- https://www.javatpoint.com/how-to-read-csv-file-in-java
- https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
- https://www.geeksforgeeks.org/
- https://stackoverflow.com/questions/tagged/java


### Running the Program

#### `Main class:` src/Main.java

`Data files needed:` All files in view/resources (properties, css, and png files for display), All files in models/resources folder and models/creature/resources.
Also need to load in one game file from the initial UI in order to start actual gameplay, all potential game files in data/game folder.

### Features Implemented
Upon running Main, the program will bring up a UI with three main buttons, a field for the Users name, 
the User's high score, and a drop down menu that allow the user to change different settings.

The three buttons are High Scores, New Game, and Build Board. 
- The `High Score button` allows the user to see the ten highest scores that have been achieved and what user achieved that score. 
- The `New Game button` will pull up a file selector allowing the user to load in a json file representing a game to play. After selecting a file, a game will appear and will be playable. The user has to press the Green play button to begin the game, and then the player can move the pacman creature using the arrow keys. 
- The `Build Board button` brings the user to a new page that allows them to create a board using an
interactive UI. The board created by the user is converted to a json file and is loaded to allow the user to play the game on the game board that they created themselves. 

We also implemented a `user system`, allowing the user to create a profile and save their high scores.
The Username text field allows the user to input their name to be used in their player profile. This name will appear below the text field and display the user's top score (pulled from a csv file). After starting
a game, the user will still be able to see their player profile, and it will show their previous best score. 
After finishing a game,
the user's score will be added to the high score csv file (and will display when High Score button is pressed if the score is high enough).

The game has an "unlimited" number of variations. The game has user controlled pieces and computer controlled
pieces. These pieces can be specified in the data file. The game also supports various pickups that do things like add score, add lives, slow computer controlled creatures, teleport user controlled pieces, etc. 

By combining these game elements in various ways, you can create many types of games. The `game variants` that we have made include Pacman, mrsPacman, Extreme Pacman (custom powerups), Maze Game, and even a puzzle game. Other variants could easily be made such as an "escape" game where the user has to get pickups to get teleported into various rooms before the user can escape. Another possible variant would be a survival game where the user has to run away from the computer controlled creatures for as long as possible while the timer keeps track of the user's time. 

From the game screen, the user can reset the game using the Reset button, return to the home page, and can play and pause the current game. 

We also included 12 cheat keys for the user to use. The cheat keys are Q, W, E, R, T, Y,
U, I, O, P, A, S. The keys have the following functions:
* Q : Freezes all ghosts in their current position permanently.
* W: Resets the ghosts' positions.
* E: Ends the game.
* R: Resets the user position.
* T: Advances to the next Level.
* Y: Lose one life.
* U: Makes ghosts blue (edible).
* I: Adds 500 points to the user's current score.
* O: Adds 100 points to the user's current score.
* P: Adds 1,000,000 points to the user's current score.
* A: Adds one life.
* S: Removes 1,000,000 points from user's current score.


### Notes/Assumptions

Assumptions or Simplifications: 
* The ghosts do not spawn in a box like how they do in actual pacman. Instead, the user can place the ghosts wherever they want in the data file.
* We assume that the file only has one user controlled creature. You can add multiple user controlled creatures without crashing the program, but only one will be playable.

Interesting data files:
* Pacman.json is a vanilla pacman game with multiple CPUs. This file shows basic pacman functionality.
* PacmanExtreme.json is a pacman game with lots of custom power-ups added in. This file shows the power-ups' functionality.
* mrsPacman.json is a pacman game with different textures and creature image files. This file shows how the entire game board view is dependent on the data file.
* MazeGame.json is a large maze game. It should be noted that sometimes the user has to maximize their window to play if their monitor is not large enough. This file shows an entirely new game variant and shows a different lose condiction.
Instead of losing the game when you run out of lives, you lose in the maze game by not reaching the finish before the timer runs out.
* EasyMaze.json shows a simple maze that utilizes the portal power-up mechanic. This game is great for showing how the game gets harder as you get to a higher level.
* basics.json is an extremely simple pacman game with only one ghost. This game file is great for experimenting with the cheat keys.
* Puzzle.json is a unique puzzle game where you have to figure out how to navigate around the map using the empty space "loop around" mechanic from traditional pacman.
* There are additional game files as well so feel free to try them out!

Known Bugs:
* It is extremely rare, but sometimes the ghosts "hesitate" before eating you when you are not moving. This has to do with the BFS path finding algorithm making the ghost think it is already in the same location as the pacman when it really isn't.

Challenge Features:
* We added a Board Builder that allows the user to create a pacman game file through an intuitive GUI. After building the board, the user can then play pacman on their very own board.
The board builder works by converting the UI data into a json file that can be processed by our game engine. This feature is a great way to easily make new games to play around with.
* We also added a high score system that links a player's profile to the game's data. A user can input their name into the username text field to set up their profile. The game will then keep track of the user's score
and add it to the list of other high scores when the user completes a game. Only the top ten high scores are displayed when you press the High Score button, but all of the player data is saved and will appear when the user enters their name into the username text field.

### Impressions
This project taught us all so much. Perhaps the most valuable lesson was the need for extensible code. Throughout this project, we were given many new requirements that we had to implement. If any part of our code was not designed well, implementing these new features would have been extremely difficult.

We all agreed that we felt very prepared to complete this project with good design, as we were able to 
apply what we learned throughout the semester. We worked well as a team, had over 75 in person meetings, and we all got to know each other very well.

We are happy with how our end project turned out; however, we know there is always room for improvement. We worked as hard as we could, yet there are still
a few things that we would have liked to refactor if we had additional time. Overall, we felt this project was an excellent way to complete the semester.

