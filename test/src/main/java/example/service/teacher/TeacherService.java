package example.service.teacher;

import example.dao.TeacherRepository;
import example.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TeacherService {
    final
    private TeacherRepository teacherDao;
    @Autowired
    public TeacherService(TeacherRepository teacherDao) {
        this.teacherDao = teacherDao;
    }
    public Teacher findByTeacherId(String teacherId) {
        return teacherDao.findByTeacherId(Integer.parseInt(teacherId));
    }
}
