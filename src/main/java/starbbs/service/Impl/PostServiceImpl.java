package starbbs.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import starbbs.dao.PostDao;
import starbbs.domain.Post;
import starbbs.service.IPostService;

@Service
public class PostServiceImpl extends ServiceImpl<PostDao, Post> implements IPostService {

}
