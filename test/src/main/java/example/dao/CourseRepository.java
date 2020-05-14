package example.dao;

import example.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * (Course)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByPermissionAndEndTimeAfter(Integer i2, Date date);

    Course findByCourseId(int i);

    @Modifying(clearAutomatically = true)
    @Query("update Course a set a.permission = 1 where a.courseId =:id")
    public void pass(@Param("id")int id);


    @Modifying(clearAutomatically = true)
    @Query("update Course a set a.permission = -1 where a.courseId =:id")
    public void reject(@Param("id")int id);
}