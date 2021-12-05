1. On the highest level, our API encapsulates how exactly the controller interacts with both the views and the models. Also, my API
encapsulates the parsing process of the .json file so that all other modules can get are just processed data they request.
2. The controller API currently does not rely on any abstractions because it serves as the bridge between the frontend and the backend.
3. My API is flexible because it includes and tries to handle any many possible input types as possible including so that the users
do not have to follow a large set of rules too strictly to generate a game map.
4. One major exception that might occur is incorrect data types in the input file. The json reader class tries to capture as many as
possible incorrect data types and throw corresponding error alert windows to handle the exceptions.


1. My API is easy to learn because the names of parameters and methods are readable with detailed JavaDoc explanations. With clean code
and single responsibility principle, the API should function exactly like what it claims.
2. My API leads to readable code because the code was encapsulated properly and satisfied single responsibility principle. The modularity
and cleanness of the code would enable other programmers to easily read, understand, or even modify the current API design.
3. My API tries to cover as many edge cases as possible, meaning that the API should give users much freedom to input what they without
having an error. If an error occurs, my exception handling cases will try to cover as many cases as possible so that the user will quickly understand
what goes wrong to avoid any misuses.
4. My API design consists of an information container, a json file reader, and the communication controller 
between the front end and the back end. The design is good because each component is very straightforward to understand, and
the simple, clean design will allow other users to use the code with ease.