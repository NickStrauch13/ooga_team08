package ooga.view.boardBuilder;


import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.control.Button;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.models.creatures.cpuControl.CPUCreature;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import util.DukeApplicationTest;

public class BoardManagerTests extends DukeApplicationTest {
    private static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
    private static final String TITLE = "Start Screen";
    private Controller myController;
    private ChoiceBox myLangBox;
    private ChoiceBox myViewModeBox;
    private ChoiceBox myGameBox;

    @Override
    public void start(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myController = new Controller(stage);
    }

    @Test
    public void clickOnBoardBuilderButton(){
        Button buildBoardButton = lookup("#buildBoardButton").query();
        clickOn(buildBoardButton);
        sleep(400);
    }




}

