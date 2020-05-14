package example.controller.admin;

import de.felixroske.jfxsupport.FXMLController;
import example.entity.Course;
import example.entity.FXLogin;
import example.entity.FXSubmitCourse;
import example.service.admin.AdminService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@FXMLController
public class AdminController {

    protected final AdminService adminService;

    public AdminController(example.service.admin.AdminService adminService){
        this.adminService =adminService;
    }

    @FXML
    public void checkSingle(){

        String account = checkAccount.getText();
        System.out.println(account);
        /**
         * 使用张宏伟登陆功能（不需要密码）
         * 直接跳入单独用户账号
         */

    }


    @FXML
    public void backCourse(){
        /**
         * 传参给学生， 进入学生退课界面《让学生获取退课权限
         */
    }


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
    public void checkAll(){
        submitTable1.setVisible(false);
        submitTable2.setVisible(false);
        submitTable1.setManaged(false);
        submitTable2.setManaged(false);
        btnPass.setVisible(false);
        btnReject.setVisible(false);

        checkAllTable.setVisible(true);
        checkAllTable.setVisible(true);

        ObservableList<FXLogin> Data = FXCollections.observableArrayList();
        Map<String, String> accout = adminService.allAccount();
        for(String key:accout.keySet()){
            Data.add(new FXLogin(key,accout.get(key)));
        }
        account.setCellValueFactory(new PropertyValueFactory<>("Name"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        checkAllTable.setItems(Data);
    }



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
    TableColumn<FXSubmitCourse, CheckBox> box1;


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
    TableColumn<FXSubmitCourse, CheckBox> box2;
    @FXML
    Button btnPass;
    @FXML
    Button btnReject;


    @FXML
    public void submitCourse(){

        checkAllTable.setManaged(false);
        checkAllTable.setVisible(false);

        submitTable1.setVisible(true);
        submitTable2.setVisible(true);
        btnPass.setVisible(true);
        btnReject.setVisible(true);
        submitTable1.setManaged(true);
        submitTable2.setManaged(true);

        List<Course> courses = adminService.submitCourse();
        ObservableList<FXSubmitCourse> data1 = FXCollections.observableArrayList();
        ObservableList<FXSubmitCourse> data2 = FXCollections.observableArrayList();
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

    public void reject(){
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


    @FXML
    public void logout(){

    }

}

