package example.dao;

import example.entity.Userlogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * (Userlogin)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface UserloginRepository extends JpaRepository<Userlogin, Long> {
    public Userlogin findByUserloginId(Integer userloginid);

    public List<Userlogin> findByUserloginIdAndPassword(String userloginid,String password);
}