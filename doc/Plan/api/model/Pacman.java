public interface Pacman extends Creature{

    void move();

    void die();

    void eat(pickup p);

    void getState();

    void powerUp();

}