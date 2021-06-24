package com.lrm.web;

import com.lrm.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    //get请求到根路径时，返回index页面
    //@GetMapping("/{id}/{name}")
    @GetMapping("/")
    public String index(){
        //int i=9/0;
        //拦截异常 返回500
        //String blog=null;
        //if(blog==null){
        //    throw  new NotFoundException("博客不存在");
        //}

        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        //int i=9/0;
        //拦截异常 返回500
        //String blog=null;
        //if(blog==null){
        //    throw  new NotFoundException("博客不存在");
        //}

        return "blog";
    }


    @GetMapping("/about")
    public String about(){
        //int i=9/0;
        //拦截异常 返回500
        //String blog=null;
        //if(blog==null){
        //    throw  new NotFoundException("博客不存在");
        //}

        return "about";
    }

    @GetMapping("/archives")
    public String archives(){
        //int i=9/0;
        //拦截异常 返回500
        //String blog=null;
        //if(blog==null){
        //    throw  new NotFoundException("博客不存在");
        //}

        return "archives";
    }

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
}
