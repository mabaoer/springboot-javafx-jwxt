package example.controller.student;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author mbw
 * @date 2020/5/13 16:14
 */
@FXMLController
public class MessageController implements Initializable {

    @FXML
    Label label;

    @FXML
    public void test(String message){
        label.setText(message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText("Welcom to Student System");
    }
}
