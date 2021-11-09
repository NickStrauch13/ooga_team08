#Use Cases

##Frontend Use Cases
* The user starts a new basic pacman variant game by loading in the correct data file.
```java
    pacmanDisplay.setupDisplay();
    pacmanDisplay.newGame();
```
* The user presses the up arrow key when there is empty space in front of the controlled creature.
```java
    location = pacman.findBoardLocation();
    pacman.forwad();
```
* The user presses the right arrow key when there is a wall directly to the right.
```java
  location = pacman.findBoardLocation();
  pacman.right();
```
* The Pacman picks up a power-up item that makes it become the predator temporarily.
```java
  effect = powerUp.getEffect();
  pacman.poweredUp();
  pacman.updateEffect(effect);
  ghosts.updateEffect(effect);
```
* The user loses their first life.
```java
    lies = controller.getLives();
    homeScreen.updateLives(lives);
    pacman.sendHome();
    ghosts.sendHome();
```
* The user loses all of their lives.
```java
    lives = controller.getLives();
    homeScreen.updateLives(lives);
    simulationManager.stopAnimation();
    simulationManager.gameOver();
    pacmanDisplay.newGame();
```
