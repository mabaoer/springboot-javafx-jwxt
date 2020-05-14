package example.controller.teacher;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@FXMLController
public class ExitController {
    @FXML private Button confirm;
    @FXML private Button cancel;

    @FXML void Confirm(ActionEvent event) {
        System.exit(0);
    }
    @FXML void Cancel(ActionEvent event) {
        TeacherController.getTabPane().getSelectionModel().select(0);
    }
}
