package example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * (Userlogin)实体类
 *
 * @author mbw
 * @since 2020-05-11 15:31:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Userlogin")
public class Userlogin implements Serializable {
    private static final long serialVersionUID = 320905282570310722L;
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    /**
    * 用户id
    */    
    @Column(name = "userloginId")
    private Integer userloginId;
    /**
    * 昵称
    */    
    @Column(name = "userName")
    private String userName;
    /**
    * 密码
    */    
    @Column(name = "password")
    private String password;
    /**
    * 角色权限
    */    
    @Column(name = "role")
    private Integer role;

}