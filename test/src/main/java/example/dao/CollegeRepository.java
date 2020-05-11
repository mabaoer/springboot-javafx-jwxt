package example.dao;

import example.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (College)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface CollegeRepository extends JpaRepository<College, Long> {


}