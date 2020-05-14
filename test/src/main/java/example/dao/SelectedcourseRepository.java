package example.dao;

import example.entity.Selectedcourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (Selectedcourse)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface SelectedcourseRepository extends JpaRepository<Selectedcourse, Long> {

    @Query(value = "select o.studentId from Selectedcourse o where o.courseId = ?1")
    List<Integer> findStudentIdsByCourseId(Integer courseId);

    @Query(value = "select o.mark from Selectedcourse o where o.studentId = ?1 and o.courseId = ?2 and o.permission = ?3")
    Integer findMarkByStudentIdAndCourseIdAndPermission(Integer studentId,Integer courseId,Integer permission);

    @Query(value = "select o from Selectedcourse o where o.studentId = ?1 and o.courseId = ?2")
    Selectedcourse findSelectedcourseByStudentIdAndCourseId(Integer studentId,Integer courseId);
}