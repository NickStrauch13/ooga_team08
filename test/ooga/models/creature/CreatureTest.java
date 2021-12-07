package ooga.models.creature;

import ooga.models.creatures.Creature;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreatureTest {
    UserCreature user;
    CPUCreature cpu;
    @BeforeEach
    public void start(){
        user= new UserCreature(0,0);
        cpu = new CPUCreature(0,0);
        user.setId("hello");
        user.setSize(24);
        cpu.setSize(24);
        user.setInvincible(true);
    }
    @Test
    public void testGetSize(){

        assert(user.getSize()==cpu.getSize());
    }
    @Test
    public void testGetId(){
        assert(user.getId().equals("hello"));
    }
    @Test
    public void testSetId(){
        user.setId("ugr");
        assert(!user.getId().equals("hello"));
    }
    @Test
    public void testGetSpeed(){

        assert(user.getSpeed()==1);
    }
    @Test
    public void testGetXpos(){

        assert(user.getXpos()==0);
    }
    @Test
    public void testGetHomeYpos(){

        assert(user.getHomeY()==0);
    }
    @Test
    public void testGetHomeXpos(){

        assert(user.getHomeX()==0);
    }
    @Test
    public void testGetYpos(){

        assert(user.getYpos()==0);
    }
    @Test
    public void testUserMoveTo(){
        user.moveTo(1,2);
        assert(1==user.getXpos());
    }
    @Test
    public void testUserMoveTo2(){
        user.moveTo(1,2);
        assert(2==user.getYpos());
    }
    @Test
    public void testCPUMoveTo(){
        cpu.moveTo(1,2);
        assert(1==cpu.getXpos());
    }
    @Test
    public void testCPUMoveTo2(){
        cpu.moveTo(1,2);
        assert(2==cpu.getYpos());
    }
    @Test
    public void testGetCenterX(){

        assert(12==user.getCenterX());
    }
    @Test
    public void testGetCenterY(){
        user.moveTo(1,2);
        assert(14==user.getCenterY());
    }
    @Test
    public void testSetX(){
        user.setXpos(1);
        assert(1==user.getXpos());
    }
    @Test
    public void testSetY(){
        user.setYpos(2);
        assert(2==user.getYpos());
    }
    @Test
    public void testSetSize(){
        user.setSize(100);
        assert(100==user.getSize());
    }
    @Test
    public void testGetStandardSpeed(){
        assert(1==user.getStandardSpeed());
    }
    @Test
    public void testCPUGetHomeYpos(){

        assert(cpu.getHomeY()==0);
    }
    @Test
    public void testCPUGetHomeXpos(){

        assert(cpu.getHomeX()==0);
    }
    @Test
    public void testIsInvincible(){
        assert(user.isInvincible());
    }
    @Test
    public void testSetInvincible(){
        user.setInvincible(false);
        assert(!user.isInvincible());
    }


}
