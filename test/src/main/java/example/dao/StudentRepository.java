package example.dao;
import java.util.Date;

import example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (Student)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface StudentRepository extends JpaRepository<Student, Long> {


}