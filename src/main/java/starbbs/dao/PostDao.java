package starbbs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import starbbs.domain.Post;

@Mapper
public interface PostDao extends BaseMapper<Post> {
}
