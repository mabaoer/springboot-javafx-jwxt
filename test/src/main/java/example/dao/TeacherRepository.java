package example.dao;
import java.util.Date;

import example.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (Teacher)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public Teacher findByTeacherId(Integer teacherid);
}