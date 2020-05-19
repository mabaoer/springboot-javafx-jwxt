package example.dao;

import example.entity.Traningpro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * (Traningpro)dao
 *
 * @author mbw
 * @since 2020-05-11 15:48:06
 */
public interface TraningproRepository extends JpaRepository<Traningpro, Long> {
    @Query(value ="select * from  traningpro a where a.college_id =:id or a.college_id = 0",nativeQuery=true)
    List<Traningpro> findTp(@Param("id")int id);

    @Query(value = "select o.type from Traningpro o where o.traningproId = ?1")
    String findTypeByTraningproId(Integer traningproId);

}