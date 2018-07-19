package com.winnieazhang.github.service;


import com.winnieazhang.github.entity.Post;
import com.winnieazhang.github.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2018/7/17.
 */

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void insert(Post post){
        postRepository.save(post);
    }
}
