package example.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * (Teacher)实体类
 *
 * @author mbw
 * @since 2020-05-11 15:31:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Teacher")
public class Teacher implements Serializable {
    private static final long serialVersionUID = -91952221692041070L;
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    /**
    * 老师id
    */    
    @Column(name = "teacherId")
    private Integer teacherId;
    /**
    * 老师姓名
    */    
    @Column(name = "teacherName")
    private String teacherName;
    /**
    * 性别
    */    
    @Column(name = "sex")
    private String sex;
    /**
    * 出生日期
    */    
    @Column(name = "birthYear")
    private Date birthYear;
    /**
    * 学历
    */    
    @Column(name = "degree")
    private String degree;
    /**
    * 职称
    */    
    @Column(name = "title")
    private String title;
    /**
    * 入职时间
    */    
    @Column(name = "grade")
    private Date grade;
    /**
    * 专业id
    */    
    @Column(name = "collegeId")
    private Integer collegeId;

}