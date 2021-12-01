package starbbs.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import starbbs.domain.User;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
