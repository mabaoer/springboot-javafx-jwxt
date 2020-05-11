package example.dao;

import example.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mbw
 * @date 2020/5/11 13:51
 */
public interface CollegeReposity extends JpaRepository<College, Long> {
}
