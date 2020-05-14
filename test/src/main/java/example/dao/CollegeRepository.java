package example.dao;

import example.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * (College)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface CollegeRepository extends JpaRepository<College, Long> {
@Query(value = "select collegeId,collegeName,neededCredit from  College where collegeId=1")
    public College findcollege();

}