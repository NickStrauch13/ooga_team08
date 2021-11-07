Our selected game is Pac-Man. The three game variants are:
  * Anti-Pacman
    * Instead of controlling the yellow Pac-Man, the user instead controls the ghosts. This is a
    complete 180 from normal Pac-Man as the user's objectives have been flipped.
      * This would require the 'creature' controlled by the user to be different, and it would also
      require the Pacman to have its own AI.
  * Mrs.Pacman
    * Normal Pacman rules, but the game textures are changed to a new style.
      * In order to accomplish these changes with good design, every game texture cannot be 
      hardcoded in as a constant. It would be much better to have the textures dependent on the data
      file.
  * Pacman Extreme
    * Normal Pacman but there are additional power-ups scattered around the board. These may
    include perks like speed boosts, double points, fewer ghosts, and more.
      * The power-ups would have to be able to be represented in the frontend, and they will also
      have to have backend functionality. This means that we may want to have some sort of 'rules'
      class that the power-ups could temporarily modify.
