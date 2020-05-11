package example.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * (Course)实体类
 *
 * @author mbw
 * @since 2020-05-11 15:31:34
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Course")
public class Course implements Serializable {
    private static final long serialVersionUID = -64442484476982081L;
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    /**
    * 课程id
    */    
    @Column(name = "courseId")
    private Integer courseId;
    /**
    * 课程名称
    */    
    @Column(name = "courseName")
    private String courseName;
    /**
    * 老师id
    */    
    @Column(name = "teacherId")
    private Integer teacherId;
    /**
    * 总人数
    */    
    @Column(name = "number")
    private Integer number;
    /**
    * 剩余课余量
    */    
    @Column(name = "restNumber")
    private Integer restNumber;
    /**
    * 开课时间
    */    
    @Column(name = "courseTime")
    private String courseTime;
    /**
    * 开课地点
    */    
    @Column(name = "classRoom")
    private String classRoom;
    /**
    * 学时
    */    
    @Column(name = "courseWeek")
    private Integer courseWeek;
    /**
    * 课程类型
    */    
    @Column(name = "courseType")
    private String courseType;
    /**
    * 专业id
    */    
    @Column(name = "collegeId")
    private Integer collegeId;
    /**
    * 学分
    */    
    @Column(name = "credit")
    private Integer credit;
    /**
    * 培养方案ID
    */    
    @Column(name = "trainProId")
    private Integer trainProId;
    /**
    * 最迟选课、退课时间
    */    
    @Column(name = "endTime")
    private Date endTime;
    /**
    * -1不通过；0待审核；1通过
    */    
    @Column(name = "permission")
    private Integer permission;

}