package com.winnieazhang.github.controllers;

import com.winnieazhang.github.config.CustomUserDetails;
import com.winnieazhang.github.entity.Post;
import com.winnieazhang.github.service.PostService;
import com.winnieazhang.github.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by Wayne.A.Z on 2018/7/19.
 */
@RestController
public class HomeController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping(value="/")
    public String index(){
        return "index";
    }

    @GetMapping(value="/posts")
    public List<Post> posts(){
        return postService.getAllPosts();
    }

    @PostMapping(value="/post")
    public String publishPost(@RequestBody Post post){
        //该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；

        CustomUserDetails userDetails=(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(post.getDateCreated()==null){
            post.setDateCreated(new Date());
            post.setCreator(userService.getUser(userDetails.getUsername()));

            postService.insert(post);
        }
        return "Post was Published";
    }
}
