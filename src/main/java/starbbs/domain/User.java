package starbbs.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private String username;
    private String password;
    private String email;
}
