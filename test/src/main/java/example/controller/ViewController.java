package example.controller;

import de.felixroske.jfxsupport.FXMLController;
import example.Main;
import example.view.ToolView;
import javafx.event.Event;
import javafx.stage.Modality;

import java.io.IOException;

@FXMLController
public class ViewController {

    public void showToolWindow(Event event) throws IOException {
        Main.showView(ToolView.class, Modality.NONE);
    }

}
