package ooga.controller;

import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONBuilderTests extends DukeApplicationTest  {

    private JSONBuilder jsonBuilder;
    private Controller myController;

    @Override
    public void start(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myController = new Controller(stage);

    }

    @BeforeEach
    void initialize() {
        jsonBuilder = new JSONBuilder(myController);
    }

    @Test
    void createJSONFile() {
        ArrayList<String> userAdded = new ArrayList<>();
        userAdded.add("3,4,WALL");
        userAdded.add("2,5,WALL");
        jsonBuilder.compileBoard(userAdded);
        String[][] myFile = jsonBuilder.getFileBoard();
        assertEquals(false, myFile[0][0] != null);
    }

    @Test
    void getPositionTest() {
        int myposition = jsonBuilder.getPosition("3,4,WALL", 0);
        assertEquals(3,myposition);
    }

    @Test
    void getPositionTest2() {
        int myposition = jsonBuilder.getPosition("5,4,WALL", 1);
        assertEquals(4,myposition);
    }

    @Test
    void getIDClass() {
        String className = jsonBuilder.getIDClass("5,7,WALL");
        assertEquals("WALL", className);
    }

    @Test
    void mySplitTest() {
        String[] mySplit = jsonBuilder.splitId("5,7,WALL");
        assertEquals("5",mySplit[0]);
    }

    @Test
    void inObjectMapTest() {
        ArrayList<String> userAdded = new ArrayList<>();
        userAdded.add("1,7,WALL");
        userAdded.add("2,2,WALL");
        jsonBuilder.compileBoard(userAdded);
        assertEquals("./data/boardBuilderBoards/output.json", jsonBuilder.getBoardPath());
    }

    @Test
    void getFileBoard() {
        ArrayList<String> userAdded = new ArrayList<>();
        userAdded.add("2,6,WALL");
        userAdded.add("1,3,WALL");
        jsonBuilder.compileBoard(userAdded);
        assertEquals(true,jsonBuilder.getFileBoard().length == 10);
    }

}
