package com.best.web;

import com.best.po.Blog;
import com.best.service.BlogService;
import com.best.service.TagService;
import com.best.service.TypeService;
import com.best.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;
    //get请求到根路径时，返回index页面
    //@GetMapping("/{id}/{name}")
    @GetMapping("/")
    public String index(@PageableDefault(size = 2,sort = ("updateTime"),direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        //int i=9/0;
        //拦截异常 返回500
        //String blog=null;
        //if(blog==null){
        //    throw  new NotFoundException("博客不存在");
        //}
        return "index";
    }

    @RequestMapping("/search")
    public String search(@PageableDefault(size = 2,sort = ("updateTime"),direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
		        return "blog";
    }



//    @GetMapping("/about")
//    public String about(){
//        //int i=9/0;
//        //拦截异常 返回500
//        //String blog=null;
//        //if(blog==null){
//        //    throw  new NotFoundException("博客不存在");
//        //}
//
//        return "about";
//    }

//    @GetMapping("/archives")
//    public String archives(){
//        //int i=9/0;
//        //拦截异常 返回500
//        //String blog=null;
//        //if(blog==null){
//        //    throw  new NotFoundException("博客不存在");
//        //}
//
//        return "archives";
//    }

    @GetMapping("/tags")
    public String tags(){
        //int i=9/0;
        //拦截异常 返回500
        //String blog=null;
        //if(blog==null){
        //    throw  new NotFoundException("博客不存在");
        //}

        return "tags";
    }

    @GetMapping("/types")
    public String types(){
        //int i=9/0;
        //拦截异常 返回500
        //String blog=null;
        //if(blog==null){
        //    throw  new NotFoundException("博客不存在");
        //}

        return "types";
    }
    //------------------------------------------------------
    @GetMapping("/blogs")
    public String blogs(){
        //int i=9/0;
        //拦截异常 返回500
        //String blog=null;
        //if(blog==null){
        //    throw  new NotFoundException("博客不存在");
        //}

        return "/admin/blogs";
    }

    @GetMapping("/blogs-input")
    public String blogsinput(){
        //int i=9/0;
        //拦截异常 返回500
        //String blog=null;
        //if(blog==null){
        //    throw  new NotFoundException("博客不存在");
        //}

        return "/admin/blogs-input";
    }
    @GetMapping("/footer/newblog")
    public String newblog(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(3));
        return "_fragments::newblogList";
    }

}
