package starbbs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import starbbs.domain.User;
import starbbs.service.Impl.UserServiceImpl;

import javax.annotation.Resource;

@SpringBootTest
public class Page{

    @Resource
    private UserServiceImpl userService;

    @Test
    void getUser(){
        IPage<User> page=new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(1,3);
        userService.page(page);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }
}
