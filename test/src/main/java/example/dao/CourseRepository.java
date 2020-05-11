package example.dao;

import example.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (Course)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface CourseRepository extends JpaRepository<Course, Long> {


}