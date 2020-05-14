package example.service.teacher;


import example.dao.CourseRepository;
import example.entity.Course;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    final
    private CourseRepository courseDao;
    @Autowired
    public CourseService(CourseRepository courseDao) {
        this.courseDao = courseDao;
    }
    public List<Course> findAllByTeacherID(Integer teacherID) {
        return courseDao.findAllByTeacherID(teacherID);
    }
    public void SaveCourse(Course course) {
        courseDao.save(course);
    }
}
