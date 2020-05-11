package example.controller;

import de.felixroske.jfxsupport.FXMLController;

import javafx.event.Event;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class ToolController {

@Autowired
protected example.service.test test;

    public void doSomething(final Event e) {
        System.out.println(test.test());
    }
}
