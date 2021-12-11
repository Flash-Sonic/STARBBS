package starbbs.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.code.kaptcha.Constants;
import org.springframework.web.bind.annotation.*;
import starbbs.domain.User;
import starbbs.service.Impl.UserServiceImpl;
import starbbs.domain.R;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user/login")
public class UserLoginController {

    @Resource
    private UserServiceImpl userService;

    @GetMapping
    public R getOne(@RequestParam("userName") String username, @RequestParam("passWord") String password, HttpServletRequest request, @RequestParam("verityCode") String code) {


        /*验证码*/
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

        System.out.println(request.getSession().getId());
        System.out.println("=======================================>" + sessionCode);

        //保证验证码只能用一次
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (sessionCode != null && sessionCode.equalsIgnoreCase(code)) {
            if(username == null || password == null) {
                return new R(false,"用户名或密码不能为空");
            }
            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
            lqw.eq(username != null, User::getUsername, username);
            lqw.eq(password != null, User::getPassword, password);
            User user = userService.getBaseMapper().selectOne(lqw);
            String message = "用户名或密码错误";
            if (user == null) {
                return new R(false, message);
            } else {
                session.setAttribute("currentUser",user);
                return new R(true, user);
            }
        } else {
            String message = "验证码错误,验证失败";
            return new R(false, message);
        }

    }

}

