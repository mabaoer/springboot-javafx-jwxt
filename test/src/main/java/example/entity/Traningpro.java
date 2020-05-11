package example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * (Traningpro)实体类
 *
 * @author mbw
 * @since 2020-05-11 15:31:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Traningpro")
public class Traningpro implements Serializable {
    private static final long serialVersionUID = 852032431882270952L;
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    /**
    * 属性id
    */    
    @Column(name = "traningproId")
    private Integer traningproId;
    /**
    * 专业id
    */    
    @Column(name = "collegeId")
    private Integer collegeId;
    /**
    * 课程属性
    */    
    @Column(name = "type")
    private String type;
    /**
    * 所需学分
    */    
    @Column(name = "needScore")
    private Integer needScore;

}