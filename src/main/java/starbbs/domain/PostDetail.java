package starbbs.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDetail {
    @TableId(type = IdType.AUTO)
    private int rid;
    private int pid;
    private int uid;
    private String rcontent;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Post post;
}
