package starbbs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import starbbs.domain.Post;
import starbbs.domain.Resp;
import starbbs.domain.User;
import starbbs.service.Impl.RespServiceImpl;
import starbbs.service.Impl.PostServiceImpl;
import starbbs.service.Impl.UserServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post/detail")
public class RespController {

    @Resource
    private RespServiceImpl respService;
    @Resource
    private PostServiceImpl postService;
    @Resource
    private UserServiceImpl userService;

    @GetMapping("/lz")
    public Post getlzPost(@RequestParam("pid") String pidStr){
        int pid=1;
        if(pidStr != null && pidStr.length() !=0 && !"null".equals(pidStr)) {
            pid=Integer.parseInt(pidStr);
        }
        Post post = postService.getById(pid);
        User user = userService.getById(post.getUid());
        post.setUser(user);
        return post;
    }

    @GetMapping
    public IPage<Resp> getResponsePage(HttpServletRequest request
            , @RequestParam("currentPage") String currentPageStr
            , @RequestParam("pid") String pidStr) {

        int pid = 1;
        //当前页码
        int currentPage = 0;

        if (pidStr != null && pidStr.length() != 0 && !"null".equals(pidStr)) {
            pid = Integer.parseInt(pidStr);
        }
        if (currentPageStr != null && currentPageStr.length() != 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        IPage<Resp> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(currentPage, 10);
        LambdaQueryWrapper<Resp> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Resp::getPid, pid);
        respService.page(page, lqw);
        List<Resp> records = page.getRecords();
        for (Resp record : records) {
            User user = userService.getById(record.getUid());
            record.setUser(user);
            Post post = postService.getById(record.getPid());
            User puser = userService.getById(post.getUid());
            post.setUser(puser);
            record.setPost(post);
        }
        return page;
    }

}
