package ooga.models.game;

import org.junit.jupiter.api.Test;

public class CollisionManagerTest {
    @Test
    public void testSetCollision(){
        CollisionManager cm = new CollisionManager();
        cm.setCollision("2,7");
        assert(cm.getCurrentCollision().equals("2,7"));
    }
    @Test
    public void testGetCurrentCollision(){
        CollisionManager cm = new CollisionManager();
        String str = "GHOST";
        cm.setCollision(str);
        String str2 = cm.getCurrentCollision();
        boolean b = str.equals(str2);
        assert(b);
    }
    @Test
    public void checkIfCollisionTrue(){
        CollisionManager cm = new CollisionManager();
        cm.setCollision("2,7");
        assert(cm.checkIfCollision());
    }
    @Test
    public void checkIfCollisionFalse(){
        CollisionManager cm = new CollisionManager();
        assert(!cm.checkIfCollision());
    }
    @Test
    public void checkIsCreatureTrue(){
        CollisionManager cm = new CollisionManager();
        cm.setCollision("ABCD");
        assert(!cm.isCreature());
    }
    @Test
    public void checkIsCreatureFalse(){
        CollisionManager cm = new CollisionManager();
        cm.setCollision("CREATURE12984");
        assert(cm.isCreature());
    }


}
