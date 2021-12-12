package starbbs.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.code.kaptcha.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import starbbs.domain.User;
import starbbs.service.Impl.UserServiceImpl;
import starbbs.domain.R;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user/register")
public class UserRegisterController {

    @Resource
    private UserServiceImpl userService;

    @GetMapping
    public R setOne(@RequestParam("userName") String username, @RequestParam("passWord") String password, @RequestParam("Email") String email, HttpServletRequest request, @RequestParam("verityCode") String code) {

        /*验证码*/
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //保证验证码只能用一次
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (sessionCode != null && sessionCode.equalsIgnoreCase(code)) {
            if(username == null || password == null || username.length() == 0 || password.length() == 0) {
                return new R(false,"用户名或密码不能为空");
            }
            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
            lqw.eq(username != null, User::getUsername, username);
            User user = userService.getBaseMapper().selectOne(lqw);
            if (user != null) {
                String message = "用户名存在";
                return new R(false, message);
            } else {
                User u = new User();
                u.setUsername(username);
                u.setPassword(password);
                u.setEmail(email);
                userService.saveOrUpdate(u);
                String message = "注册成功，即将跳转到登录界面";
                return new R(true, message);
            }
        } else {
            String message = "验证码不同,验证失败";
            return new R(false, message);
        }
    }
}
