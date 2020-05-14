import example.Main;
import example.controller.student.StudentController;
import example.dao.*;
import example.entity.College;
import example.entity.Course;
import example.entity.Selectedcourse;
import example.entity.Student;
import example.service.student.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author mbw
 * @date 2020/5/12 16:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class test {
    @Autowired
    StudentService studentService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SelectedcourseRepository selectedcourseRepository;

    static Student student;
    static Course course;

    @Test
    public void test() {
        student = studentRepository.findByStudentId(1);
        List<Course> courses = studentService.findAllCanBeSelectedCoursesByStudent(student);
        for (Course c : courses) {
            System.out.println(c);
        }
    }

    @Test
    public void insert() {
        student = studentRepository.findByStudentId(1);
        course = courseRepository.findByCourseId(1);
        if (selectedcourseRepository.findByStudentIdAndCourseId(student.getStudentId(), course.getCourseId()) == null)
            studentService.selectCourseByCourseIdAndStudentId(course.getCourseId(), student.getStudentId());
        else
            System.out.println("已经选中,请勿重复选取");
    }

    @Test
    public void delete() {
        Selectedcourse selectedcourse = selectedcourseRepository.findBySelectedcourseId(1);
        if (selectedcourse != null) {
            course = courseRepository.findByCourseId(selectedcourse.getCourseId());
            if (course.getEndTime().compareTo(new Date()) > 0){
                selectedcourse.setPermission(-1);
                selectedcourseRepository.save(selectedcourse);
            }else {
                selectedcourse.setPermission(0);
                selectedcourseRepository.save(selectedcourse);
                System.out.println("等待管理员审核");
            }
        }else {
            System.out.println("已经退课，请勿重复退课");
        }
    }
    @Test
    public void test1(){
        studentRepository.save(Student.builder().studentName("哈哈哈").sex("1").grade(new Date())
                .credit(1).collegeId(1).birthYear(new Date()).build());
    }
}
