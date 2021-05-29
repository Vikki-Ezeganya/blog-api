package com.vikki.week_9_blog_api.service;

import com.vikki.week_9_blog_api.model.Post;
import com.vikki.week_9_blog_api.model.PostLike;
import com.vikki.week_9_blog_api.model.User;
import com.vikki.week_9_blog_api.repository.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public class PostLikeService {

        @Autowired
        PostLikeRepository postLikeRepository;

        public void addPostLike(PostLike postLike) {
            postLikeRepository.save(postLike);
        }

        public List<PostLike> getAllByPost(Post post) {
            return postLikeRepository.findAllByPost(post);
        }

        public List<PostLike> getAllByUser(User user) {
            return postLikeRepository.findAllByUser(user);
        }

        public void deletePostLike(PostLike postLike) {
            postLikeRepository.delete(postLike);
        }

        public PostLike getByPostAndUser(Post post, User user) {
            return postLikeRepository.findByPostAndUser(post, user);
        }
    }
