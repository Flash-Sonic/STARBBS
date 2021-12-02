package starbbs.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import starbbs.dao.PostDetailDao;
import starbbs.domain.PostDetail;
import starbbs.service.PostDetailService;

@Service
public class PostDetailServiceImpl extends ServiceImpl<PostDetailDao, PostDetail> implements PostDetailService {
}
