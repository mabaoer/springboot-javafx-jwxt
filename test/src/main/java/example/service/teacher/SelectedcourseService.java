package example.service.teacher;

import example.dao.SelectedcourseRepository;
import example.entity.Selectedcourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectedcourseService {
    final private SelectedcourseRepository selectedcourseDao;
    @Autowired
    public SelectedcourseService(SelectedcourseRepository selectedcourseDao) {
        this.selectedcourseDao = selectedcourseDao;
    }
    public List<Integer> findStudentIdsByCourseId(Integer courseId ) {
        return selectedcourseDao.findStudentIdsByCourseId(courseId);
    }
    public Integer findMarkByStudentIdAndCourseIdAndPermission(Integer studentId,Integer courseId,Integer permission) {
        return selectedcourseDao.findMarkByStudentIdAndCourseIdAndPermission(studentId,courseId,permission);
    }
    public Selectedcourse findSelectedcourseByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        return selectedcourseDao.findSelectedcourseByStudentIdAndCourseId(studentId,courseId);
    }
    public void save(Selectedcourse selectedcourse) {
        selectedcourseDao.save(selectedcourse);
    }
}
