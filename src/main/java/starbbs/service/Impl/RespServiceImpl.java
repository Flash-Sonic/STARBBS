package starbbs.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import starbbs.dao.RespDao;
import starbbs.domain.Resp;
import starbbs.service.RespService;

@Service
public class RespServiceImpl extends ServiceImpl<RespDao, Resp> implements RespService {
}
