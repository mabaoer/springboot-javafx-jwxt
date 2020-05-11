package example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * (College)实体类
 *
 * @author mbw
 * @since 2020-05-11 15:31:34
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "College")
public class College implements Serializable {
    private static final long serialVersionUID = 582733438942086910L;
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    /**
    * 专业id
    */    
    @Column(name = "collegeId")
    private Integer collegeId;
    /**
    * 专业名称
    */    
    @Column(name = "collegeName")
    private String collegeName;
    /**
    * 专业所需学分
    */    
    @Column(name = "neededCredit")
    private Integer neededCredit;

}