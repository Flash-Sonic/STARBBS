package starbbs.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import starbbs.dao.UserDao;
import starbbs.domain.User;

import javax.annotation.Resource;

@SpringBootTest
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    void testGetById() {
        System.out.println(userDao.selectById(1));
    }

    @Test
    void testGetBy() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername,"baby");
        lqw.eq(User::getPassword,"angel");
        System.out.println(userDao.selectList(lqw));
    }

}


