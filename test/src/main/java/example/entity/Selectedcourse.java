package example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * (Selectedcourse)实体类
 *
 * @author mbw
 * @since 2020-05-11 15:31:34
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Selectedcourse")
public class Selectedcourse implements Serializable {
    private static final long serialVersionUID = 662301215996910559L;
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    /**
    * ID
    */    
    @Column(name = "selectedcourseId")
    private Integer selectedcourseId;
    /**
    * 课程id
    */    
    @Column(name = "courseId")
    private Integer courseId;
    /**
    * 学生id
    */    
    @Column(name = "studentId")
    private Integer studentId;
    /**
    * 成绩（-1为还没有成绩
    */    
    @Column(name = "mark")
    private Integer mark;
    /**
    * -1已经退课，0等待管理员审核，1选中
    */    
    @Column(name = "permission")
    private Integer permission;

}