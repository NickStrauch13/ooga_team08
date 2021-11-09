# OOGA Design Final
### Names

## Team Roles and Responsibilities

 * Team Member #1
    Abhinav Ratnagiri
 * Team Member #2
    Neil Mosca
 * Team Member #3
    Nick Strauch
 * Team Member #4
    Nick Conterno
 * Team Member #5
    David Wu

## Design goals

#### What Features are Easy to Add

Creating moving Pacman and ghost objects, stationary pickups and wall objects. Tracking Collisions between moving objects and stationary ones.
Drawing Initial board based on input csv files. Adding additional power-ups with new rules should be an easy addition to our code. 

## High-level Design

#### Core Classes

BACKEND

CREATURE- Abstract to implemented by child classes. This consists of pacman objects and ghosts. \
PACMAN- Child class of creature, will contain pacman functions such as pickup, die etc.\
CPUPACMAN- child class of pacman, movement controlled by cpu algorithm, implements cpu move interface.\
USERPACMAN- child class of pacman, movement controlled by user, implements user move interface.\
GHOST- Child class of creature, encapsulates ghost and all properties therein.\
CPUGHOST- child class of ghost, movement controlled by cpu algorithm,implements cpu move interface.\
USERGHOST- child class of ghost, movement controlled by user, implements user move interface.\
BOARD- grid of cell objects where each will have a state corresponding to wall, pickup or empty. Passed once to view upon initialization.\
CELL- can be a wall, contain power up or be empty, essentially one space on game board.\
GAME- class dictates the running/starting/endgame of the game, has step function to update moving creatures\

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

#### Features Affected by Assumptions


## Significant differences from Original Plan
  * The backend will now be checking for not only the moving objects' cellular location, but also the 
moving objects' pixel location.

## New Features HowTo


#### Easy to Add Features
Changing the textures should be easy if we design the textured objects well. Keeping track of score
in the frontend should also be easy if we doa good job of connecting the frontend to the backend
through the use of the controller.


