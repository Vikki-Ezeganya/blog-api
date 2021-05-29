package com.vikki.week_9_blog_api.controller;

import com.vikki.week_9_blog_api.model.Post;
import com.vikki.week_9_blog_api.model.PostLike;
import com.vikki.week_9_blog_api.model.User;
import com.vikki.week_9_blog_api.repository.PostRepository;
import com.vikki.week_9_blog_api.repository.UserRepository;
import com.vikki.week_9_blog_api.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostLikeService postLikeService;

    //Add New Post
    @PostMapping("/addNew/{email}")
    public  ResponseEntity<?> addpost(@RequestBody Post post, @PathVariable("email") String email){
        User existingUser = this.userRepository.findUserByEmail(email);
        if(existingUser != null) {
            Date current_time = new Date();
            post.setDateCreated(current_time);
            post.setUser(userRepository.findUserByEmail(email));
            this.postRepository.save(post);
            return new ResponseEntity<>("post created", HttpStatus.OK);
        }
        return new ResponseEntity<>("User does not exist",HttpStatus.OK);
    }


//Get All post
    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK) ;
    }


    //Get posts by id
   @GetMapping("/{id}")
    public ResponseEntity<?> getByPostId(@PathVariable (value="id") Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return new ResponseEntity<>(post.get(), HttpStatus.OK) ;
        }

        return new ResponseEntity<>("Post not available", HttpStatus.BAD_REQUEST);
   }

   //update post by user
   @PutMapping("/{email}/{id}")
    public Post updatePost(@RequestBody Post post, @PathVariable("id") Long id, @PathVariable("email") String email) {
        Post existingPost = this.postRepository.findById(id).get();
        existingPost.setPostTitle(post.getPostTitle());
        existingPost.setPostBody(post.getPostBody());
        Date current_time = new Date();
        existingPost.setDateUpdated(current_time);
        existingPost.setUser(userRepository.findUserByEmail(email));
        return this.postRepository.save(existingPost);
   }

   //delete post by user
    @DeleteMapping("/{id}/{email}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") Long id, @PathVariable("email") String email) {
        Post existingPost = this.postRepository.findById(id).get();
        existingPost.setUser(userRepository.findUserByEmail(email));
        this.postRepository.delete(existingPost);
        return ResponseEntity.ok().build();
    }

    //Like a post
    @PostMapping(path = "/{postId}/like/{userId}")
    public ResponseEntity<?> likePost(@PathVariable(name = "postId") Long postId, @PathVariable(name = "userId") Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);
        if (user.isPresent()) {
            if (post.isPresent()) {
                PostLike like = postLikeService.getByPostAndUser(post.get(), user.get());
                if (like == null) {
                    PostLike postLike = new PostLike();
                    postLike.setPost(post.get());
                    postLike.setUser(user.get());
                    postLikeService.addPostLike(postLike);
                    return new ResponseEntity<>("post liked!",HttpStatus.OK);
                } else {
                    postLikeService.deletePostLike(like);
                    return new ResponseEntity<>("post unliked!",HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Get number of likes for a post
    @GetMapping( "/{postId}/likes")
    public ResponseEntity<?> getPostLikes(@PathVariable(name = "postId") Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            int size = postLikeService.getAllByPost(postRepository.findById(id).get()).size();
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}


