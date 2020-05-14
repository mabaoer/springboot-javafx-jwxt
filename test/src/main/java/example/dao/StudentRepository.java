package example.dao;
import java.util.Date;

import example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * (Student)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select o.studentName from Student o where o.studentId = ?1")
    String findStudentNameByStudentId(Integer studentId);

    public Student findByStudentId(Integer studentid);

    public Student findByStudentId(Integer studentId);
}