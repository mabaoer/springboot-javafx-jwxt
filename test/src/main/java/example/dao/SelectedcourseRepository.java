package example.dao;

import example.entity.Selectedcourse;
import org.springframework.data.jpa.repository.JpaRepository;
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

    Selectedcourse findByStudentIdAndCourseId(Integer studentId, Integer courseId);

    Selectedcourse findBySelectedcourseId(int i);

    List<Selectedcourse> findByStudentIdAndPermission(Integer studentId, Integer i);

    @Query(value ="select * from  selectedcourse a where a.student_id =:id and a.permission=1",nativeQuery=true)
    List <Selectedcourse>findSc(@Param("id")int id);

}