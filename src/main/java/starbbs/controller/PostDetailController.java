package starbbs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import starbbs.domain.Post;
import starbbs.domain.PostDetail;
import starbbs.domain.User;
import starbbs.service.Impl.PostDetailServiceImpl;
import starbbs.service.Impl.PostServiceImpl;
import starbbs.service.Impl.UserServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post/detail")
public class PostDetailController {

    @Resource
    private PostDetailServiceImpl postDetailService;
    @Resource
    private PostServiceImpl postService;
    @Resource
    private UserServiceImpl userService;

    @GetMapping
    public IPage<PostDetail> getResponsePage(HttpServletRequest request
            , @RequestParam("currentPage") String currentPageStr
            , @RequestParam("pid") String pidStr) {

        int pid=1;
        //当前页码
        int currentPage=0;

        if (pidStr != null && pidStr.length() != 0 && !"null".equals(pidStr)) {
            pid = Integer.parseInt(pidStr);
        }
        if (currentPageStr != null && currentPageStr.length() != 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        IPage<PostDetail> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(currentPage, 10);
        LambdaQueryWrapper<PostDetail> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PostDetail::getPid, pid);
        postDetailService.page(page, lqw);
        List<PostDetail> records = page.getRecords();
        for (PostDetail record : records) {
            User user=userService.getById(record.getUid());
            record.setUser(user);
            Post post = postService.getById(record.getPid());
            record.setPost(post);
        }
        return page;
    }

}
