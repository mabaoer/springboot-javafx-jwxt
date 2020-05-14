package example.controller.teacher;

import de.felixroske.jfxsupport.FXMLController;
import example.entity.Course;
import example.service.teacher.CollegeService;
import example.service.teacher.CourseService;
import example.service.teacher.TraningproService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import static example.controller.login.LoginController.teacher;
import java.util.ResourceBundle;

@FXMLController
public class InformationController implements Initializable {
    @FXML private AnchorPane root;
    @FXML private Label teacherName;
    @FXML private Label title;
    @FXML private Label degree;
    @FXML private Label college;
    @FXML private Label grade;
    @FXML private TableView table;
    @FXML private TableColumn courseId;
    @FXML private TableColumn courseName;
    @FXML private TableColumn number;
    @FXML private TableColumn courseType;
    @FXML private TableColumn collegeId;
    @FXML private TableColumn trainProId;
    @FXML private TableColumn status;
    private ApplicationContext applicationContext;
    public static ObservableList<Course> courseObservableList;
    private ObservableList<VirtualCourse> virtualCourseObservableList;
    private CollegeService collegeService;
    private TraningproService traningproService;
    private CourseService courseService;

    @Autowired
    public InformationController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        courseService = applicationContext.getBean(CourseService.class);
        collegeService = applicationContext.getBean(CollegeService.class);
        traningproService = applicationContext.getBean(TraningproService.class);
    }

    public void setVirtualCourseObservableList(ObservableList<Course> courseObservableList) {
        virtualCourseObservableList = FXCollections.observableArrayList();
        for(int i=0; i < courseObservableList.size(); i++) {
            Course course = courseObservableList.get(i);
            VirtualCourse virtualCourse  = new VirtualCourse();
            virtualCourse.setCourseId(course.getCourseId());
            virtualCourse.setCourseName(course.getCourseName());
            virtualCourse.setNumber(course.getNumber());
            virtualCourse.setCourseType(course.getCourseType());
            virtualCourse.setCollegeId(collegeService.findCollegeNameByCollegeId(course.getCollegeId()));
            virtualCourse.setTrainProId(traningproService.findTypeByTraningproId(course.getTrainProId()));
            if(course.getPermission()==-1)
                virtualCourse.setPermission("Failed");
            else if(course.getPermission() == 0)
                virtualCourse.setPermission("Auditing");
            else if (course.getPermission() == 1)
                virtualCourse.setPermission("Pass");
            virtualCourseObservableList.add(virtualCourse);
        }
    }

    @Data
    @ToString
    public class VirtualCourse{
        public Integer courseId;
        public String courseName;
        public Integer number;
        public String courseType;
        public String collegeId;
        public String trainProId;
        public String permission;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseObservableList = FXCollections.observableList(courseService.findAllByTeacherID(teacher.getTeacherId()));
        setVirtualCourseObservableList(courseObservableList);
        //set teacher's information
        teacherName.setText(teacherName.getText()+teacher.getTeacherName());
        title.setText(title.getText()+teacher.getTitle());
        degree.setText(degree.getText()+teacher.getDegree());
        college.setText(college.getText()+teacher.getCollegeId());
        grade.setText(grade.getText()+teacher.getGrade());
        //set teacher's course
        courseId.setCellValueFactory(new PropertyValueFactory<VirtualCourse,String>("courseId"));
        courseName.setCellValueFactory(new PropertyValueFactory<VirtualCourse,String>("courseName"));
        number.setCellValueFactory(new PropertyValueFactory<VirtualCourse,String>("number"));
        courseType.setCellValueFactory(new PropertyValueFactory<VirtualCourse,String>("courseType"));
        collegeId.setCellValueFactory(new PropertyValueFactory<VirtualCourse,String>("collegeId"));
        trainProId.setCellValueFactory(new PropertyValueFactory<VirtualCourse,String>("trainProId"));
        status.setCellValueFactory(new PropertyValueFactory<VirtualCourse,String>("permission"));
        table.setItems(virtualCourseObservableList);
    }
}