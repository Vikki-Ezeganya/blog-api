package com.vikki.week_9_blog_api.repository;

import com.vikki.week_9_blog_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
