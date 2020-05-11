package example.dao;
import java.util.Date;

import example.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * (Teacher)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "select o from Teacher o where o.teacherId = ?1")
    Teacher findByTeacherId(Integer teacherId);

}