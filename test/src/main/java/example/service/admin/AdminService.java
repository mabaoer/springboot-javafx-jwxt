package example.service.admin;

import example.dao.*;
import example.entity.Course;
import example.entity.Selectedcourse;
import example.entity.Traningpro;
import example.entity.Userlogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service

public class AdminService {


    @Autowired
    public UserloginRepository userloginRepository;
    @Autowired
    public StudentRepository studentRepository;
    @Autowired
    public TeacherRepository teacherRepository;
    @Autowired
    public CourseRepository courseRepository;
    @Autowired
    public TraningproRepository traningproRepository;
    @Autowired
    public SelectedcourseRepository selectedcourseRepository;



    public List<Course> recommendCourse() {
        /**
         * 学生界面设计前端
         * 所有数据均以测试成功
         * 返回推荐课程
         */

        List<Course> returnCourse = new LinkedList<>();
        // test.getCollegeId();
        // test.getStudentId();

        int collegeId = 1;        //学生所在专业测试用例
        int studentId = 10002;

        List<Course> courses = courseRepository.findAll();
        Map<Integer, Course> map = new HashMap<>();   //哈希查询，减少循环
        for (int i = 0; i < courses.size(); i++) {
            if(collegeId ==courses.get(i).getCollegeId()||courses.get(i).getCollegeId()==0)
            {
                Course credit = courses.get(i);    //只要学分和课程号即可，为方便数据使用，将所有数据放入
                map.put(courses.get(i).getCourseId(), credit);
            }

        }

        List<Traningpro> traningpros = traningproRepository.findTp(collegeId);
        List<Selectedcourse> selectedcourses = selectedcourseRepository.findSc(studentId);

        int tpCount; //遍历整张培养方案
        for (int i = 0; i < traningpros.size(); i++) {
            int flag = 0;
            int needScore = traningpros.get(i).getNeedScore();
            int curScore = 0;
            int tId = traningpros.get(i).getTraningproId();
            String s = traningpros.get(i).getType();

            for (int j = 0; j < selectedcourses.size(); j++) {

                int cId = selectedcourses.
                        get(j).getCourseId();
                Course temp = map.get(cId);
                if (temp.getTrainProId() == tId) {
                    curScore += temp.getCredit();
                    map.remove(cId);
                }
                if (curScore >= needScore) {

                    System.out.println("你已经修完了" + s);
                    flag = 1;
                }
            }
            if (flag == 0) {
                System.out.println("你没有修完" + s + "推荐");
                for (Integer key : map.keySet()) {
                    Course temp2 = map.get(key);
                    if (temp2.getTrainProId() == tId)
                    {
                        returnCourse.add(temp2);
                        System.out.println(temp2.getCourseName());
                    }
                }
            }
        }
        return  returnCourse;
    }




    public void passCourse(int id){
            courseRepository.pass(id);
    }



    public void rejectCourse(int id){
        courseRepository.reject(id);
    }

    public List<Course> submitCourse(){

        List<Course> courses = courseRepository.findAll();
        return courses;
    }


    public Map<String, String> allAccount(){
        List<Userlogin> userlogins =userloginRepository.findAll();
        Map<String, String> temp =new HashMap<>();
        for(int i=0;i<userlogins.size();i++)
        {
            String role = "学生";
            if(userlogins.get(i).getRole()==0)
            {
                role="管理员";
            }
            else if(userlogins.get(i).getRole()==1)
                role="老师";

            temp.put(userlogins.get(i).getUserName(),role);
        }
        return temp;
    }



}
