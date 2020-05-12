package example.dao;

import example.entity.Userlogin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (Userlogin)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface UserloginRepository extends JpaRepository<Userlogin, Long> {

}