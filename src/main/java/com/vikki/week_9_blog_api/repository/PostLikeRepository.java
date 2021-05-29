package com.vikki.week_9_blog_api.repository;

import com.vikki.week_9_blog_api.model.Post;
import com.vikki.week_9_blog_api.model.PostLike;
import com.vikki.week_9_blog_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeRepository extends JpaRepository <PostLike, Long> {
    List<PostLike> findAllByPost(Post post);

    List<PostLike> findAllByUser(User user);

    PostLike findByPostAndUser(Post post, User user);
}
