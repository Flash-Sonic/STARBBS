package starbbs.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import starbbs.dao.UserDao;
import starbbs.domain.User;
import starbbs.service.IUserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
