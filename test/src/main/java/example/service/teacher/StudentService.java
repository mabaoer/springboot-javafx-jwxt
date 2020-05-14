package example.service.teacher;

import example.dao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    final
    private StudentRepository studentDao;
    @Autowired
    public StudentService(StudentRepository studentDao) {
        this.studentDao = studentDao;
    }
    public String findStudentNameByStudentId(Integer studentId){
        return studentDao.findStudentNameByStudentId(studentId);
    }
}
