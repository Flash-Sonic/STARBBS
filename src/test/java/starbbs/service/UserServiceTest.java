package starbbs.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import starbbs.domain.User;
import starbbs.service.Impl.UserServiceImpl;

import javax.annotation.Resource;

@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserServiceImpl userService;

    @Test
    void getUser(){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername,"baby");
        lqw.eq(User::getPassword,"angel");
        System.out.println(userService.getBaseMapper().selectList(lqw));
    }
}
