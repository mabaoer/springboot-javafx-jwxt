package example.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author mbw
 * @since 2020-05-11 15:31:34
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Student")
public class Student implements Serializable {
    private static final long serialVersionUID = -75984816973777413L;
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    /**
    * 学生id
    */    
    @Column(name = "studentId")
    private Integer studentId;
    /**
    * 学生姓名
    */    
    @Column(name = "studentName")
    private String studentName;
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
    * 入学时间
    */    
    @Column(name = "grade")
    private Date grade;
    /**
    * 专业id
    */    
    @Column(name = "collegeId")
    private Integer collegeId;
    /**
    * 总学分
    */    
    @Column(name = "credit")
    private Integer credit;

}