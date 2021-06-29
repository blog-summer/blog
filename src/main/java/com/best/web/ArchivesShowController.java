package com.best.web;


import com.best.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ArchivesShowController {
    @Autowired//注入
    private BlogService blogService;
    @GetMapping("/archives")
    public  String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());//调用blogService,把拿到的blog，放到blodService.....
        model.addAttribute("blogCount",blogService.countBlog());//web拿到数据就到页面上渲染一下。
        return "archives";
    }
}
