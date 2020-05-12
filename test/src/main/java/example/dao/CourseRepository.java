package example.dao;

import example.entity.Course;
import javafx.collections.ObservableList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (Course)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query( value = "select o from Course o where o.teacherId = ?1")
    List<Course> findAllByTeacherID(Integer teacherId);
}