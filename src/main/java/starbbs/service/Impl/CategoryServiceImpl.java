package starbbs.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import starbbs.dao.CategoryDao;
import starbbs.domain.Category;
import starbbs.service.CategoryService;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
}
