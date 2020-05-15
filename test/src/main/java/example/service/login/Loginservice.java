package example.service.login;

import example.dao.StudentRepository;
import example.entity.Student;
import example.entity.Teacher;
import example.entity.Userlogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import example.dao.UserloginRepository;
import example.dao.*;

import java.util.List;

/**
 * @author mbw
 * @date 2020/5/9 21:45
 */
@Service
public class LoginService {

    final
    UserloginRepository userDao;
    final
    StudentRepository studentDao;
    final
    TeacherRepository teacherDao;

    @Autowired
    public LoginService(UserloginRepository userDao, StudentRepository studentDao, TeacherRepository teacherDao) {
        this.userDao = userDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
    }
    public List<Userlogin> findAllUsers() {
        return userDao.findAll();
    }

    public Student findByStudentId(Integer studentId) {
        return studentDao.findByStudentId(studentId);
    }

    public Teacher findByTeacherId(Integer teacherId) {
        return teacherDao.findByTeacherId(teacherId);
    }
}
