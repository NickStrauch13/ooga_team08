BACK-END DESIGN

In the backend (model) we will have a game class which will handle all four game types. This class will handle 
basic backend functionality, such as setting up the game, setting up the baord, running steps of the game, and updating basic instance
variables. When the board is set up, it will create instances of our pacman creatures. These creatures will all extend an 
abstract creature class and implement their own move and die methods. At the start of the program, the entire board will be sent to the view
so that it can be drawn once to begin our animation, but from this point on we will only send selective updates. Thus, at each step, we will send updates to 
the position (in pixels) for each of our moving objects (Pacmen & ghosts). Stationary objects will only be adjusted if they disappear from the screen. 



|Abstract Creature| |
|---|---|
|void move()        ||
|void die()       ||

|CPUPacman| |
|---|---|
|void move()         ||
|void die()        ||
|void eat(PowerUp p)        ||
|void powerUp()        ||
|void getState()        ||// 0 dead, 1 normal, 2 powered up
|private void getMove()||

|UserPacman| |
|---|---|
|void move()         ||
|void die()        ||
|void eat(PowerUp p)        || 
|void powerUp()        ||
|void getState()        ||//0 dead, 1  normal, 2 powered up

|UserGhost| |
|---|---|
|void move()         ||
|void die()        ||

|CPUGhost| |
|---|---|
|void move()         ||
|void die()        ||
|void chase() ||
|void runAway()||

|Abstract PowerUp||
|---|---|
|void eaten()||

|StandardPowerUp| |
|---|---|
|void eaten()||

|Game| |
|---|---|
| void run()||
| void createCreature(String creatureType)||
| void setUpBoard() ||
| void step()||
| void createPowerUp(String powerUpType)||
| void endGame()||
| void nextLevel()||
| void loseLife() ||
| void resetCreatures()||
| void addScore()||

|Board| |
|---|---|
|updateCellState()||