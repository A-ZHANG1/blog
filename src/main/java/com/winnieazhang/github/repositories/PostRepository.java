package com.winnieazhang.github.repositories;

import com.winnieazhang.github.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Wayne.A.Z on 2018/7/17.
 */
public interface PostRepository extends JpaRepository<Post,Long> {

}
