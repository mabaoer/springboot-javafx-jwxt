package example;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import example.view.FirstView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(Main.class,  FirstView.class, args);
    }
}
