package starbbs.controller;

import org.springframework.web.bind.annotation.*;
import starbbs.domain.Post;
import starbbs.domain.PostDetail;
import starbbs.domain.R;
import starbbs.domain.User;
import starbbs.service.Impl.PostDetailServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
@RequestMapping("/post/responsepublish")
public class PostResponsePublishController {
    @Resource
    private PostDetailServiceImpl postDetailService;

    @PostMapping
    public R postResponsePublish(HttpServletRequest request
            , @RequestParam("pid") String pidStr
            , @RequestParam("response_publish_content") String rcontent
    ) throws UnsupportedEncodingException {
        String message;
        HttpSession session =request.getSession();
        User user = (User)session.getAttribute("currentUser");
        if(user==null) {
            message="发布评论失败，请先登录";
            return new R(false,message);
        }
        int uid = user.getUid();
        int pid=1;
        if (pidStr != null && pidStr.length() != 0 && !"null".equals(pidStr)) {
            pid = Integer.parseInt(pidStr);
        }
        PostDetail postDetail = new PostDetail();
        postDetail.setPid(pid);
        postDetail.setUser(user);
        postDetail.setUid(uid);
        postDetail.setRcontent(rcontent);
        message="发布成功";
        return new R(postDetailService.save(postDetail),message);
    }
}
