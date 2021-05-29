package com.vikki.week_9_blog_api.repository;

import com.vikki.week_9_blog_api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

}
