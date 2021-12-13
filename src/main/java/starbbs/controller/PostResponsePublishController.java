package starbbs.controller;

import org.springframework.web.bind.annotation.*;
import starbbs.domain.Resp;
import starbbs.domain.R;
import starbbs.domain.User;
import starbbs.service.Impl.RespServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/post/responsepublish")
public class PostResponsePublishController {
    @Resource
    private RespServiceImpl respService;

    @PostMapping("/{pid}")
    public R postResponsePublish(HttpServletRequest request
            , @PathVariable("pid") String pidStr
            , @RequestParam("response_publish_content") String rcontent
    ) throws UnsupportedEncodingException {
        String message;
        HttpSession session =request.getSession();
        User user = (User)session.getAttribute("currentUser");
        if(user==null) {
            message="发布评论失败，请先登录";
            return new R(false,message);
        }
        if(rcontent.length() == 0) {
            return new R(false,"内容不能为空");
        }
        int uid = user.getUid();
        int pid=1;
        if (pidStr != null && pidStr.length() != 0 && !"null".equals(pidStr)) {
            pid = Integer.parseInt(pidStr);
        }
        Resp postDetail = new Resp();
        postDetail.setPid(pid);
        postDetail.setUser(user);
        postDetail.setUid(uid);
        postDetail.setRcontent(rcontent);
        message="发布成功";
        return new R(respService.save(postDetail),message);
    }
}
