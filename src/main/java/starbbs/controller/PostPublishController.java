package starbbs.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import starbbs.domain.Post;
import starbbs.domain.R;
import starbbs.domain.User;
import starbbs.service.Impl.PostServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
@RequestMapping("/post/publish")
public class PostPublishController {

    @Resource
    private PostServiceImpl postService;

    @PostMapping
    public R postPublish(HttpServletRequest request
            , @RequestParam("cid") String cidStr
            , @RequestParam("publish_title") String pname
            , @RequestParam("publish_content") String pcontent
    ) throws UnsupportedEncodingException {
        String message;
        HttpSession session =request.getSession();
        User user = (User)session.getAttribute("currentUser");
        if(user==null) {
            message="发布失败，请先登录";
            return new R(false,message);
        }
        if(pname.length() == 0 || pcontent.length() == 0) {
            return new R(false,"标题或内容不能为空");
        }
        int uid = user.getUid();
        int cid=0;
        if (cidStr != null && cidStr.length() != 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        Post post = new Post();
        post.setCid(cid);
        post.setUser(user);
        post.setUid(uid);
        post.setPtime(new Date());
        post.setPname(pname);
        post.setPcontent(pcontent);
        message="发布成功";
        return new R(postService.save(post),message);
    }
}
