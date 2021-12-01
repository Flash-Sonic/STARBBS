package starbbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import starbbs.domain.Category;
import starbbs.service.CategoryService;
import starbbs.service.Impl.CategoryServiceImpl;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/post/category")
public class PostCategoryController {

    @Resource
    private CategoryServiceImpl categoryService;

    @GetMapping
    public List<Category> getCategory() {
        List<Category> list = categoryService.list();
        return list;
    }

}
