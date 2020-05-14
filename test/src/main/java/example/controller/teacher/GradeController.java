package example.controller.teacher;

import de.felixroske.jfxsupport.FXMLController;
import example.entity.Selectedcourse;
import example.service.teacher.SelectedcourseService;
import example.service.teacher.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@FXMLController
public class GradeController implements Initializable {
    public Button cancel;
    public Button commit;
    public TableColumn usualGrades;
    public TableColumn midtermGrades;
    public TableColumn finalGrades;
    public TableColumn studentId;
    public TableColumn studentName;
    public TableColumn overallGrades;
    public TableView table;
    @FXML private ComboBox combo_box;
    private ApplicationContext applicationContext;
    private SelectedcourseService selectedcourseService;
    private StudentService studentService;
    private ObservableList<String> observableList;
    private ObservableList<Integer> courseIdObservableList;
    private ObservableList<Grade> gradeObservableList;
    private ObservableList<Grade> changeObservableList;
    @Autowired
    public GradeController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        selectedcourseService = applicationContext.getBean(SelectedcourseService.class);
        studentService = applicationContext.getBean(StudentService.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        observableList = FXCollections.observableArrayList();
        courseIdObservableList = FXCollections.observableArrayList();
        gradeObservableList = FXCollections.observableArrayList();
        changeObservableList = FXCollections.observableArrayList();
        for(int i=0; i < InformationController.courseObservableList.size();i++) {
            if(InformationController.courseObservableList.get(i).getPermission() < 1)
                continue;
            String courseName = InformationController.courseObservableList.get(i).getCourseName();
            Integer courseId = InformationController.courseObservableList.get(i).getCourseId();
            courseIdObservableList.add(courseId);
            observableList.add(courseName);
        }
        combo_box.setItems(observableList);

        studentId.setCellValueFactory(new PropertyValueFactory<Grade,String>("studentId"));
        studentName.setCellValueFactory(new PropertyValueFactory<Grade,String>("studentName"));
        overallGrades.setCellValueFactory(new PropertyValueFactory<Grade,String>("overallGrades"));
        usualGrades.setCellValueFactory(new PropertyValueFactory<Grade,String>("usualGrades"));
        midtermGrades.setCellValueFactory(new PropertyValueFactory<Grade,String>("midtermGrades"));
        finalGrades.setCellValueFactory(new PropertyValueFactory<Grade,String>("finalGrades"));
        usualGrades.setEditable(true);
        midtermGrades.setEditable(true);
        finalGrades.setEditable(true);
        usualGrades.setCellFactory(TextFieldTableCell.forTableColumn());
        midtermGrades.setCellFactory(TextFieldTableCell.forTableColumn());
        finalGrades.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void Cancel(ActionEvent event) {
        TeacherController.getTabPane().getSelectionModel().select(0);
    }

    public void Commit(ActionEvent event) {
        Alert alert  = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Successful");
        alert.showAndWait();
        int index = combo_box.getSelectionModel().getSelectedIndex();
        for(int i=0;i < changeObservableList.size();i++) {
            Selectedcourse selectedcourse = selectedcourseService.findSelectedcourseByStudentIdAndCourseId(changeObservableList.get(i).studentId,courseIdObservableList.get(index));
            selectedcourse.setMark(changeObservableList.get(i).getOverallGrades());
            selectedcourseService.save(selectedcourse);
        }
    }

    public void EditCommit(TableColumn.CellEditEvent cellEditEvent) {
        Grade grade = (Grade) cellEditEvent.getRowValue();
        if(cellEditEvent.getTableColumn().getId().equals("usualGrades"))
            grade.usualGrades = (String) cellEditEvent.getNewValue();
        if(cellEditEvent.getTableColumn().getId().equals("midtermGrades"))
            grade.midtermGrades = (String) cellEditEvent.getNewValue();
        if(cellEditEvent.getTableColumn().getId().equals("finalGrades"))
            grade.finalGrades = (String) cellEditEvent.getNewValue();
        Double mark = Integer.parseInt(grade.usualGrades)*0.2 +
                Integer.parseInt(grade.midtermGrades)*0.2 +
                Integer.parseInt(grade.finalGrades)*0.6;
        grade.overallGrades = mark.intValue();
        table.refresh();
        changeObservableList.add(grade);
    }

    public void Changed(ActionEvent event) {
        gradeObservableList.clear();
        int index = combo_box.getSelectionModel().getSelectedIndex();
        List<Integer> list = selectedcourseService.findStudentIdsByCourseId(courseIdObservableList.get(index));
        for(int i=0; i<list.size();i++) {
            Integer courseId = courseIdObservableList.get(index);
            Integer studentId = list.get(i);
            Integer mark = selectedcourseService.findMarkByStudentIdAndCourseIdAndPermission(studentId,courseId,1);
            if(mark == null)
                continue;
            if( mark != -1 )
                continue;
            String studentName = studentService.findStudentNameByStudentId(list.get(i));
            gradeObservableList.add(new Grade(list.get(i),studentName,0,"0","0","0"));
        }
        table.setItems(gradeObservableList);
    }


    @Data
    @ToString
    public class Grade {
        public Integer studentId;
        public String studentName;
        public Integer overallGrades;
        public String usualGrades;
        public String midtermGrades;
        public String finalGrades;
        public Grade(Integer studentId, String studentName, Integer overallGrades, String usualGrades, String midtermGrades, String finalGrades) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.overallGrades = overallGrades;
            this.usualGrades = usualGrades;
            this.midtermGrades = midtermGrades;
            this.finalGrades = finalGrades;
        }
    }
}
