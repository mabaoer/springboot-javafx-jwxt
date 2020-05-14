package example.dao;

import example.entity.Selectedcourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    Selectedcourse findByStudentIdAndCourseId(Integer studentId, Integer courseId);

    Selectedcourse findBySelectedcourseId(int i);

    List<Selectedcourse> findByStudentIdAndPermission(Integer studentId, Integer i);

    @Query(value ="select * from  selectedcourse a where a.student_id =:id and a.permission=1",nativeQuery=true)
    List <Selectedcourse>findSc(@Param("id")int id);




    @Query(value="SELECT selectedcourse.selectedcourse_id,selectedcourse.course_id, course.course_name,selectedcourse.student_id,student.student_name from selectedcourse INNER JOIN course on selectedcourse.permission=0 and selectedcourse.course_id = course.course_id INNER JOIN student on selectedcourse.student_id = student.student_id", nativeQuery=true)
    List<Object[]> backCourse();

    @Query(value = "select o.mark from Selectedcourse o where o.studentId = ?1 and o.courseId = ?2 and o.permission = ?3")
    Integer findMarkByStudentIdAndCourseIdAndPermission(Integer studentId,Integer courseId,Integer permission);

    @Query(value = "select o from Selectedcourse o where o.studentId = ?1 and o.courseId = ?2")
    Selectedcourse findSelectedcourseByStudentIdAndCourseId(Integer studentId,Integer courseId);
    @Modifying(clearAutomatically = true)
    @Query("update Selectedcourse a set a.permission = -1 where a.selectedcourseId =:id")
    public void rejectSC(@Param("id")int id);

    @Modifying(clearAutomatically = true)
    @Query("update Selectedcourse a set a.permission = 1 where a.selectedcourseId =:id")
    public void passSC(@Param("id")int id);
}