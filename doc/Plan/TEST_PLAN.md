###Test Plan

##Testing Strategy One

We decided that one of the best ways to make our methods and classes more testable was to add more frequent return values to our methods. In cases where we could be implementing a void method, we think it will be just as easy to return a value (most often a boolean) that will help us write tests that can accurately assess how our methods our functioning. Of course, if a method already has a return value then it won't need adjusting. 

##Testing Strategy Two

The second strategy we decided to adopt regularly is the idea of throwing more exceptions to make our code more testable. We decided to pursue this as our second strategy because returning something from the function makes it so much easier to test. Although throwing an error is not the same as returning a value it is still considerably easier to test than a void method.

##Test scenario one

A valuable feature to test is the movement of the ghosts. If the ghosts cannot move properly according ot the instructions that we give it then there is no way it will be able to properly be able to pursue Pacman. The first test case to test the movement of the ghost would be to test a move(50.0). The move method of Ghost currently takes a parameter that is a double that indicates how much far it should move in the current direction. The method then returns a double[] that is the coordinates of the next position of the ghost. The expected outcome of this test would the new location after the movement is performed by the ghost. The API supports this test by returning the new coordinates of the ghost after the movment. 

The second test for the movement feature of the ghost would implement a rotation and a movement. The test case would be: rotate(90.0) and move(50.0). The test would see if the new coordinates of the ghost match what is expected after the ghost moves 50.0 in the new direction it faces after rotating. The API supports this test because it still utilizes the fact that move() returns the new coordinates of the ghost. The rotate() also returns the new angle that the ghost faces so this could be used in another test case to test the rotate method only.

The third test case is one that is bad/error throwing. This test would examine the new location of the ghost after it tries to move(50.0) except when that movement would put it inside a wall, so instead it should move up to the wall and return the coordinates of the ghost except at the location (wall - radius of ghost). The move() method should accomdate this condition before it performs a move by calling the Creature's inBounds() method. This method would be easily testable because it returns a boolean (true if in bounds, false otherwise).

##Test scenario two

Another useful feature to test would be to test the updateCellState() of the Board.java class. This method is useful because it helps determine if the board is being updated properly according to its status. One test case would be to make sure updateCellState() properly acknowledges that the Pacman is in the cell. To test the updateCellState() method the program would mode the pacman into a certain cell. Update that cell and make sure getCellState() in the Board.java class returns the integer that corresponds to the Pacman being inside the cell.

Another test case for updateCellState() would be to test if a cell is empty. A useful test case for this would be to have the Pacman in a cell and then move it out and with an updateCellState() call. Next calling getCellState() should show that the cell is empty because it will return 0. This ensures that after a Pacman has entered and left a cell it doesn't still think it is in that cell.

Another test case for updateCellState() would be to test if the program acknowledges that a power up has been added to a certain cell. First a powerup would be added then getCellState() would be called. It should return an int corresponding to a power up being in this cell (maybe 3).

##Test scenario three

Another useful feature would be to test would be collisions between Pacman and the ghost. On test case would test whether a collision is acknowledged properly by decreasing a life. One test case would be to test whether colliding a user controller Pacman results in Pacman losing a life. Lose life enables testing for this by returning the new amount of lives that the Pacman has.

A second test case would involve testing whether a collision involving a user controlled ghost and a Pacman results in the Pacman losing a life.

Lastly a test would be to make sure that a collision involving a Pacman and a ghost when the Pacman has 0 lives remaining results in triggering GameOver. 

##Test scenario four

A useful test would be to test if the game properly processes a Pacman collecting an energy dot. This could be tested by having the Pacman collect an energy dot and ensuring that it removes the dot from the screen. To test this removeDot() would be called and the back-end grid would be checked to ensure that it is no longer there. The expected value would be the number of dots remaining prior to the collection minus 1. 

A second test case would test whether collecting a dot triggers an increase in the score. This would be tested by calling removeDot() then checking if the score after the collection equals the score prior plus the value of the dot. The API helps facilitate these methods by returning the new value of the score or highscore after the dot was collected.

A third test would be to test whether collecting the final dot results in winning the level. To test this, exactly one dot would be added to the grid then removeDot() would be called and the test would check if wonGame() returns true. The API enables this test because wonGame() should return true if it properly recognized a game that is won.


