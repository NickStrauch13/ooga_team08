Part 1

How does your API encapsulate your implementation decisions?

Our API is built with a main Game class that handles all the game running decisions.
This game class does not need to be changed for any of our variations of pacman and as such has no child classes.
It encapsulates our decision to separate moving and stationary object within the game,
to separate user and CPU controlled moving objects, and to separate different types of stationary objects.
Each of the pairs mentioned are child classes of the same parent class with slightly different functionality.

What abstractions is your API built on?

Our API utilizes abstractions to define all moving pieces as child classes of the same super class, all stationary object have the same 
super class and this way they can interact in the same way each time. Each pickup has its own implementation of the abstract interact method
that is called when the correct creature intersects with it. 

What about your API's design is intended to be flexible?
What exceptions (error cases) might occur in your API and how are they addressed?

Part 2

How do you justify that your API is easy to learn?
How do you justify that API leads to readable code?
How do you justify that API is hard to misuse?
Why do you think your API design is good (also define what your measure of good is)?