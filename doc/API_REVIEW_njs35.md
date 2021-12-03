1. On the highest level, our API encapsulates the model from the view. Then within these two components,
the API divides the implementation into more specific blocks.
2. The view API (what I worked on) is mainly built upon the abstraction of the different game pieces
that the view needs to create/show. 
3. Our API is flexible because the methods are general. The API user will not need to know
specifically how the API is functioning. 
4. One of the main exceptions that our API needs to handle is exceptions that relate to the input
int the game data files. These strings in the data files are so crucial because they typically are 
used to make reflection calls.


1. Our API is easy to learn because the method names are very readable and are well documented. The
API is also well divided into manageable components.
2. Our API leads to readable code because our method names are intuitive and allow later programmers
to follow along with the logic.
3. Our API has robust error checking, and the methods are very open-ended, giving the user freedom
in regard to what he/she wishes to create.
4. Our API design is well divided by the three engine, viewer, and data categories. This enables the 
API user to easily learn about our specific API structural components. 