package example.controller;

import de.felixroske.jfxsupport.FXMLController;

import example.Main;
import example.entity.Teacher;
import example.view.TeacherView;
import javafx.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@FXMLController
public class ToolController {

@Autowired
protected example.service.test test;

    public void doSomething(final Event e) {
        System.out.println("张宏伟牛逼");
    }
}
