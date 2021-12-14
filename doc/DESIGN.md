# OOGA Design Final
### Names

## Team Roles and Responsibilities

 * Team Member #1
    Abhinav Ratnagiri-backend
 * Team Member #2
    Neil Mosca-frontend
 * Team Member #3
    Nick Strauch-frontend
 * Team Member #4
    Nick Conterno-backend
 * Team Member #5
    David Wu-controller & data parsing

## Design goals

#### What Features are Easy to Add

* Creating moving Pacman and ghost objects, stationary pickups and wall objects. 
* Tracking Collisions between moving objects and stationary ones. 
* Drawing Initial board based on input csv files. 
* Adding additional power-ups with new rules should be an easy addition to our code.
* Creating new types of creatures
* The user should be able to easily create their own board
* The user should be able to change the language
* The user should be able to change the UI view color 

## High-level Design

Our design, on a high-level, is demonstrative of the classic MVC package tree. We have a models package that handles all computational logic, a controller to handle
the beginnings and game flow, and a view to handle all input and output to the user themselves. The design of our code starts with the controller, which initializes
the game board and game, all based on an input JSON file. The Controller initializes
a JSONReader object that tries to catch all incorrect data formats from the input file 
and pop up alert windows to remind users to check potential erros in the file. When having an exception, the controller will stop other executions until the user reupload 
a working JSON file. This JSON file contains the initial position of all the objects on the board, with a map of ints and their 
corresponding objects, each of which are child classes of the abstract gameObject class. The same type of hierarchy exists with the creatures, all of the moving objects 
within the game. Creating the board allows for the instantiation of a Game, complete with the game setting from the input data file. Additionally, the SimulationManager
and gameDisplay classes create the initial display UI that prompts the user to input a new game file to start the process of initialization. The SimulationManager also handles
all inputs to the game by handling keyPressActions, all which come as child classes of the abstract keyPressAction class. These are checked at each step and handle the action based
on the class's predefined doAction method. Once the game has been started, the game flow is controlled from the Game class in the backend, updating the positions of the moving creatures, checking the win/loss conditions and looking for the state of game
powerups. Similarly, the SimulationManager does the analogous thing for the front end, removing nodes, handling inputs and displaying new windows if at all necessary. 
Additionally, at each step the front end checks its nodes for intersections between the user creature and any other node and sends the ID of the collided node to the controller.
Once the ID has been sent, an instance of a collisionManager with the collided Node ID is created and passed to the game class which actually handles the collision itself by calling on the
interact method of the object at the position defined by that instance of collisionManager. Once the collision is handled, a boolean is passed to our controller
which then indicates to the view that the pickup must be removed from the screen. This process continues on as the game flows, with data being passed from back to front end and vice versa
through the controller. 

Additionally, we also have a "Board Builder" feature which 
allows users to design their customized games by simply clicking on the GUI so that 
a BuilderDisplay class will read all the mouse input actions from users and use a JSONBuilder object
to transfer and store the information into a JSON file. Users can play this customized game and upload the file to play the game later. 

This is how our game design controls game flow and connects the core classes within the models, views and controller packages.

#### Core Classes

BACKEND

CREATURE- Abstract to implemented by child classes. This consists of pacman objects and ghosts. \
PACMAN- Child class of creature, will contain pacman functions such as pickup, die etc.\
USERPACMAN- child class of pacman, movement controlled by user, implements user movement methods.\
GHOST- Child class of creature, encapsulates ghost and all properties therein.\
CPUGHOST- child class of ghost, movement controlled by cpu algorithm,implements cpu movement methods.\
BOARD- grid of cell objects where each will have a state corresponding to wall, pickup or empty. Passed once to view upon initialization.\
GAME- class dictates the running/starting/endgame of the game, has step function to update moving creatures\
GAMEOBJECT- Abstract to be implemented by child classes containing all stationary object within the game.\
PICKUP- Child class of GAMEOBJECT, children are all pickups (non-wall) stationary objects in the game.\
WALL- Child class of GAMEOBJECT, children are different types of walls in the game.

CONTROLLER

CONTROLLER: class that connects the frontend and backend and launch the entire game.\
GAMECONTROLLER: class that serves as the engine and specifically processes information from backend `Game`.\
GAMESETTINGS: class that stores all the information extracted from the configuration file specifically regarding the game 
settings including the general settings and settings for particular objects like ghosts.\
JSONBUILDER: class that builds a playable JSON file based on users' inputs.\
JSONCONTAINER: class that stores the general information extracted from the configuration file regarding the game layout and various game settings.\
JSONREADER: class that parses the JSON configuration file and extracts all the information into `JSONCONTAINER`.

FRONTEND

PACMANDISPLAY- sets up display of application\
BOARDVIEW- controls updates to game state (scores, grid positions, lives, pickups)\
SIMULATIONMANAGER- runs step, pause, start animation, controls game flow\
CREATUREVIEW- updates to creature display position, display attributes (powered up, image)
PREDATORVIEW- extends CREATUREVIEW for predators
PREYVIEW- extends CREATUREVIEW for prey


## Assumptions that Affect the Design
  * Initial board layout is passed in by through a data file.
  * Assume that the board passed in from the file is winnable (i.e. no dots surrounded by walls)
  * Assuming the Pacman moves 'smoothly' across the board (not cell by cell)
  * Assume that no more than 2 portals are introduced
  * Assume that no more than 1 UserCreature is placed in the initial board
  * Assume that all numbers in the array are mapped to preexisting gameObjects using the gameObject Map
  * Assume when users construct a game, basic game elements (walls, ghosts, and pacmans) still exist 

## Significant differences from Original Plan
  * The original plan had the backend checking for not only the moving objects' cellular location, but also the moving objects' pixel location. We later changed from this because we realized that the backend does not need to know where 
node collisions occur. Instead, the backend only needs to know what game object was collided with.
  * The original plan dictated that our board would be an array of cells with different functionality,
   this was changed to use an inheritance hierarchy of gameObjects. Instead our board is an array of these 
   objects, each with different functionality. 

## New Features HowTo
   The key extension features that could be implemented come in the form of new creatures, new pickups, or new types of walls. Each of these is easily accomplished due to the design of our code. In order to add a new creature, a developer would simply have to create a new child class of UserCreature or CPUCreature, depending on how they want the movement of this creature to be controlled. They could change the functionality of this new creature by adding to the moveTo() and die() methods within their creature class,
   even electing to change the way that a creature is PoweredUp or set to Invincible if this was the intended goal. In order to create a new pickup or wall, the respective super class would need a new child class, with the interact method overwritten. For each of the three new features, the developer would need to create a name-matched gamePiece child class that dictated how they wanted their piece to be displayed. This could be in the shape of a rectangle, circle or even image; wheatever they desire. Thus it is very easy to add new features to our code, features that could lead to infinite new game variants, only limited by imagination. 

 



