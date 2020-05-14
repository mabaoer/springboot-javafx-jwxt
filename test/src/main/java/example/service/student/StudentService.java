package example.service.student;

import example.dao.CourseRepository;
import example.dao.SelectedcourseRepository;
import example.entity.Course;
import example.entity.Selectedcourse;
import example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mbw
 * @date 2020/5/13 9:47
 */
@Service
public class StudentService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SelectedcourseRepository selectedcourseRepository;

    public List<Course> findAllCanBeSelectedCoursesByStudent(Student student) {
        List<Course> courses = new ArrayList<>();
        for (Course course : courseRepository.findAllByPermissionAndEndTimeAfter(1, new Date())) {
            if (course.getCollegeId() == 0 || course.getCollegeId() == student.getCollegeId())
                courses.add(course);
        }
        return courses;
    }
    public List<Course> findAllSelectedcourseByStudent(Student student) {
        List<Selectedcourse> selectedcourses = selectedcourseRepository.findByStudentIdAndPermission(student.getStudentId(),1);
        List<Course> courses=new ArrayList<>();
        for (Selectedcourse s:selectedcourses) {
            courses.add(courseRepository.findByCourseId(s.getCourseId()));
        }
        return courses;
    }

    public String selectCourseByCourseIdAndStudentId(Integer courseid, Integer studentid) {
        Selectedcourse selectedcourse = Selectedcourse.builder().mark(-1).courseId(courseid).permission(1)
                .studentId(studentid).build();
        Selectedcourse selectedcourse1=selectedcourseRepository.findByStudentIdAndCourseId(studentid, courseid);
        if ( selectedcourse1== null) {
            selectedcourseRepository.save(selectedcourse);
            return "选课成功";
        } else{
            if(selectedcourse1.getPermission()==1){
                return "已经选中，请勿重复选择";
            }else {
                selectedcourse1.setPermission(1);
                selectedcourseRepository.save(selectedcourse1);
                return "选课成功";
            }
        }
    }

    public String deleteSelectedourseBySelectedCourseid(Integer selectedcourseid){
        Selectedcourse selectedcourse = selectedcourseRepository.findBySelectedcourseId(selectedcourseid);
        if (selectedcourse != null) {
            Course course = courseRepository.findByCourseId(selectedcourse.getCourseId());
            if (course.getEndTime().compareTo(new Date()) > 0){
                selectedcourse.setPermission(-1);
                selectedcourseRepository.save(selectedcourse);
                return "退课成功";
            }else {
                if(selectedcourse.getMark()==-1){
                    return "已有成绩，不能退课";
                }else {
                    selectedcourse.setPermission(0);
                    selectedcourseRepository.save(selectedcourse);
                    return "超出退课时间，需要等待管理员审核";
                }
            }
        }else {
           return "已经退课，请勿重复退课";
        }
    }

    public Integer findSelectedcourseByStudentidAndcourseid(Integer studentId, Integer courseid) {
        return selectedcourseRepository.findByStudentIdAndCourseId(studentId,courseid).getSelectedcourseId();
    }
}
