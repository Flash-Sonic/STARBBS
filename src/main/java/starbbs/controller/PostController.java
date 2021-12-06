package starbbs.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import starbbs.domain.Post;
import starbbs.domain.R;
import starbbs.domain.User;
import starbbs.service.Impl.PostServiceImpl;
import starbbs.service.Impl.UserServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Resource
    private PostServiceImpl postService;
    @Resource
    private UserServiceImpl userService;

    @GetMapping("/list")
    public IPage<Post> getPage(@RequestParam("currentPage") String currentPageStr
            , @RequestParam("cid") String cidStr
            , @RequestParam("pname") String pname) throws UnsupportedEncodingException {
        int cid = 1;
        //当前页码
        int currentPage = 0;
        if (cidStr != null && cidStr.length() != 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        if (currentPageStr != null && currentPageStr.length() != 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        IPage<Post> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(currentPage, 10);
        LambdaQueryWrapper<Post> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Post::getCid, cid);
        lqw.like(Post::getPname, pname);
        postService.page(page, lqw);
        if (currentPage > page.getPages()) {
            page.setCurrent(page.getPages());
            postService.page(page, lqw);
        }
        List<Post> records = page.getRecords();
        for (Post record : records) {
            User user = userService.getById(record.getUid());
            record.setUser(user);
        }
        return page;
    }

    @GetMapping("/curuser")
    public R getCurUser(HttpServletRequest request) {
        String message;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            message = "请先登录";
            return new R(false, message);
        }
        message = "欢迎登陆" + user.getUsername();
        return new R(true, message, user);
    }

    @GetMapping("/exit")
    public R exitCurUser(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute("currentUser");
        String message = "退出成功";
        return new R(true, message);
    }
}
