public interface SimulationManager {

    void step();

    void run();

    void startSimulation();

    void pause();

    void restart();

    void quit();

    void stopAnimation();

    void nextLevel();

    void gameWon();

    void gameOver();

}