package com.best.web.admin;

import com.best.po.Blog;
import com.best.po.Tag;
import com.best.po.User;
import com.best.service.BlogService;
import com.best.service.TagService;
import com.best.service.TypeService;
import com.best.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT="admin/blogs-input";
    private static final String LIST="admin/blogs";
    private static final String REDIRECT_LIST="redirect:/admin/blogs";

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    //返回列表页面
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2,sort = ("updateTime"),direction = Sort.Direction.DESC)
                               Pageable pageable,
                       BlogQuery blog,
                       Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }

     //刷新列表片段，实现局部渲染
     @PostMapping("/blogs/search")
     public String search(@PageableDefault(size = 2,sort = ("updateTime"),direction = Sort.Direction.DESC)
                                Pageable pageable,
                        BlogQuery blog,
                        Model model){
         model.addAttribute("page",blogService.listBlog(pageable,blog));

         return "admin/blogs :: blogList";
     }
     @GetMapping("/blogs/input")
     public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog",new Blog());
        return INPUT;
     }
    // 编辑
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());

        Blog blog=blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        //这个时候blogs-input才拿到tagsId
        return INPUT;
    }

     //新增博客的提交
     @PostMapping("/blogs")
     public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
         blog.setUser((User) session.getAttribute("user"));
         blog.setType(typeService.getType(blog.getType().getId()));

        blog.setFlag(blog.getType().getName());

         String[] idarray=blog.getTagIds().split(",");
         for(int i=0;i< idarray.length;i++){
             try {
                 Long id=Long.parseLong(idarray[i]);
                 if(tagService.getTag(id)==null){
                     throw new Exception();
                 }
             }catch (Exception e){
                 Tag it=new Tag();
                 it.setName(idarray[i]);
                 idarray[i]=tagService.saveTag(it).getId().toString();

             }
         }

         String ids=new String("");
         if (idarray.length>0)
         ids+=idarray[0];
         for(int i=1;i<idarray.length;i++){
             ids+=",";
             ids+=idarray[i];
         }

         blog.setTags(tagService.listTag(ids));
         Blog b;
         if (blog.getId() == null){
             b = blogService.saveBlog(blog);
         }else{
             b = blogService.updateBlog(blog.getId(),blog);
         }
         if(b==null){
             attributes.addFlashAttribute("message","操作失败");
         }else {
             attributes.addFlashAttribute("message","操作成功");
         }
         return REDIRECT_LIST;
     }

     @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
     }
}
