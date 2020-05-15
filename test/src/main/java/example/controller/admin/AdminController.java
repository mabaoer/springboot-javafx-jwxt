package example.controller.admin;

import de.felixroske.jfxsupport.FXMLController;
import example.Main;
import example.controller.login.LoginController;
import example.dao.StudentRepository;
import example.dao.TeacherRepository;
import example.entity.*;
import example.service.admin.AdminService;
import example.view.student.MessageView;
import example.view.student.StudentView;
import example.view.teacher.TeacherView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@FXMLController
public class AdminController {

    protected final AdminService adminService;

    @FXML
    TableView checkAllTable;

    @FXML
    TableColumn account;

    @FXML
    TableColumn role;

    @FXML
    TableView userTable;

    @FXML
    Button login;

    @FXML
    TextField checkAccount;

    @FXML
    TableView submitTable1;

    @FXML
    TableColumn cid1;
    @FXML
    TableColumn cN1;
    @FXML
    TableColumn tid1;
    @FXML
    TableColumn cT1;
    @FXML
    TableColumn cR1;
    @FXML
    TableColumn p1;
    @FXML
    TableColumn <FXSubmitCourse,CheckBox>box1;


    @FXML
    TableColumn cid2;
    @FXML
    TableColumn cN2;
    @FXML
    TableColumn tid2;
    @FXML
    TableColumn cT2;
    @FXML
    TableColumn cR2;
    @FXML
    TableColumn p2;
    @FXML
    TableView submitTable2;
    @FXML
    TableColumn <FXSubmitCourse,CheckBox>box2;
    @FXML
    Button btnPass;
    @FXML
    Button btnReject;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    StudentRepository studentdao;
    @Autowired
    TeacherRepository teacherdao;

    @FXML
    public void checkSingle(){

        String account = checkAccount.getText();
        if(account.length()==4)
        {
            LoginController.teacher =teacherdao.findByTeacherId(Integer.parseInt(account));
            Main.showView(TeacherView.class, Modality.NONE);
        }
        else if(account.length()==5)
        {
            LoginController.student = studentdao.findByStudentId(Integer.parseInt(account));
            Main.showView(StudentView.class, Modality.NONE);
        }

    }


    @FXML
    TableView backCourseTable;

    @FXML
    TableColumn SID;
    @FXML
    TableColumn CID;
    @FXML
    TableColumn CN;
    @FXML
    TableColumn StuID;
    @FXML
    TableColumn SN;
    @FXML
    TableColumn box;


    @FXML
    public void backCourse(){
        submitTable1.setVisible(false);
        submitTable2.setVisible(false);
        submitTable1.setManaged(false);
        submitTable2.setManaged(false);
        checkAllTable.setVisible(false);
        checkAllTable.setManaged(false);


        btnPass.setVisible(true);
        btnReject.setVisible(true);
        backCourseTable.setManaged(true);
        backCourseTable.setVisible(true);

        List<BackCourseTable>bCourses = adminService.backCourse();

        ObservableList<FXBackCourse>  data1 = FXCollections.observableArrayList();
        int sid,cid,tid;
        String cN,sN;
        for(int i=0;i<bCourses.size();i++)
        {
            cid=bCourses.get(i).getCourseID();
            sid=bCourses.get(i).getStudentID();
            tid=bCourses.get(i).getSelectedCourseID();
            cN=bCourses.get(i).getCourseName();
            sN=bCourses.get(i).getStudentName();
            data1.add(new FXBackCourse(cid,cN,sid,sN,tid));

        }


        SID.setCellValueFactory(new PropertyValueFactory<>("selectedCourseID"));
        CID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        CN.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        StuID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        SN.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        box.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        backCourseTable.setItems(data1);

    }






    @FXML
    public void checkAll(){
        backCourseTable.setManaged(false);
        backCourseTable.setVisible(false);
        submitTable1.setVisible(false);
        submitTable2.setVisible(false);
        submitTable1.setManaged(false);
        submitTable2.setManaged(false);
        btnPass.setVisible(false);
        btnReject.setVisible(false);

        checkAllTable.setVisible(true);
        checkAllTable.setManaged(true);

        ObservableList<FXLogin>  Data = FXCollections.observableArrayList();
        Map<String,String> accout = adminService.allAccount();
        for(String key:accout.keySet()){
            Data.add(new FXLogin(key,accout.get(key)));
        }
        account.setCellValueFactory(new PropertyValueFactory<>("Name"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        checkAllTable.setItems(Data);
    }









    @FXML
    public void submitCourse(){

        backCourseTable.setManaged(false);
        backCourseTable.setVisible(false);
        checkAllTable.setManaged(false);
        checkAllTable.setVisible(false);

        submitTable1.setVisible(true);
        submitTable2.setVisible(true);
        btnPass.setVisible(true);
        btnReject.setVisible(true);
        submitTable1.setManaged(true);
        submitTable2.setManaged(true);

        List<Course>courses = adminService.submitCourse();
        ObservableList<FXSubmitCourse>  data1 = FXCollections.observableArrayList();
        ObservableList<FXSubmitCourse>  data2 = FXCollections.observableArrayList();
        int cid,tid;
        String cR,cT,cn,p;
        for(int i=0;i<courses.size();i++)
        {
            cid=courses.get(i).getCourseId();
            tid=courses.get(i).getTeacherId();
            int per =courses.get(i).getPermission();
            cR=courses.get(i).getClassRoom();
            cT=courses.get(i).getCourseTime();
            cn=courses.get(i).getCourseName();
            if(per==0)
            {
                p="待审核";
                data1.add(new FXSubmitCourse(cid,cn,tid,cT,cR,p));
            }

            else if(per==1)
            {
                p="审核通过";
                data2.add(new FXSubmitCourse(cid,cn,tid,cT,cR,p));
            }
        }

        cid1.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        cN1.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tid1.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        cR1.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
        cT1.setCellValueFactory(new PropertyValueFactory<>("courseTime"));
        p1.setCellValueFactory(new PropertyValueFactory<>("permission"));
        box1.setCellValueFactory(new PropertyValueFactory<>("checkBox"));



        cid2.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        cN2.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tid2.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        cR2.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
        cT2.setCellValueFactory(new PropertyValueFactory<>("courseTime"));
        p2.setCellValueFactory(new PropertyValueFactory<>("permission"));


        submitTable1.setItems(data1);
        submitTable2.setItems(data2);

    }


    @FXML
    public void pass()
    {

        if(submitTable1.isVisible()== true)
        {
            ObservableList<FXSubmitCourse> list=submitTable1.getItems();
            ObservableList<FXSubmitCourse> list2=submitTable2.getItems();

            List<Integer> data = new LinkedList<>();

            for(int i=0;i<list.size();i++)
            {
                if(list.get(i).getCheckBox().isSelected())
                {
                    data.add(i);
                }
            }

            for (int i = data.size()-1; i>=0; i--) {
                list2.add(list.get(data.get(i)));
                int id = list.get(data.get(i)).getCourseID();
                adminService.passCourse(id);
                list.remove(list.get(data.get(i)));
            }

            submitTable1.setItems(list);
            submitTable2.setItems(list2);
        }
        else if(backCourseTable.isVisible() == true){

            ObservableList<FXBackCourse> list=backCourseTable.getItems();
            for(int i=list.size()-1 ;i>=0 ;i--)
            {
                if(list.get(i).getCheckBox().isSelected())
                {
                    int id = list.get(i).getSelectedCourseID();
                    adminService.passSelectedCourse(id);
                    list.remove(list.get(i));
                }
            }
            backCourseTable.setItems(list);
        }

    }

    public void reject(){
        if(submitTable1.isVisible() == true)
        {
            ObservableList<FXSubmitCourse> list=submitTable1.getItems();
            for(int i=list.size()-1;i>=0;i--)
            {
                if(list.get(i).getCheckBox().isSelected())
                {
                    int id = list.get(i).getCourseID();
                    adminService.rejectCourse(id);
                    list.remove(i);
                }
            }
            submitTable1.setItems(list);
        }

        else if(backCourseTable.isVisible() == true){

            ObservableList<FXBackCourse> list=backCourseTable.getItems();
            for(int i=list.size()-1 ;i>=0 ;i--)
            {
                if(list.get(i).getCheckBox().isSelected())
                {
                    int id = list.get(i).getSelectedCourseID();
                    adminService.rejectSelectedCourse(id);
                    list.remove(list.get(i));
                }
            }
            backCourseTable.setItems(list);
        }

    }


    @FXML
    public void logout(){

    }

}

