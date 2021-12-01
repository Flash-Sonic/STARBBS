package starbbs.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Post {
    @TableId(type = IdType.AUTO)
    private Integer pid;
    private String pname;
    private String pcontent;
    private Date ptime;
    private Integer uid;
    private Integer cid;
    @TableField(exist = false)
    private User user;
}
