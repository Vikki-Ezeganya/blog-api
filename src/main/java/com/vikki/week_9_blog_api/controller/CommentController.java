package com.vikki.week_9_blog_api.controller;

import com.vikki.week_9_blog_api.model.Comment;
import com.vikki.week_9_blog_api.model.Post;
import com.vikki.week_9_blog_api.model.User;
import com.vikki.week_9_blog_api.repository.CommentRepository;
import com.vikki.week_9_blog_api.repository.PostRepository;
import com.vikki.week_9_blog_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    //get all comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);
    }

    //Adding comment
    @PostMapping("/addNew/{email}/{postId}")
    public ResponseEntity<?> addComment(@RequestBody Comment comment, @PathVariable("email") String email, @PathVariable("postId") Long id){
        User existingUser = this.userRepository.findUserByEmail(email);
        if (existingUser != null) {
            Date current_time = new Date();
            Optional<Post> post = postRepository.findById(id);
            if (post.isPresent()) {
                comment.setDateCreated(current_time);
                comment.setUser(userRepository.findUserByEmail(email));
                comment.setPost(post.get());
                this.commentRepository.save(comment);
                return new ResponseEntity<>("comment created", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("user or post does not exist", HttpStatus.BAD_REQUEST);
    }

    //get a comment
    @GetMapping("/{id}")
    public ResponseEntity<?> getByCommentId(@PathVariable (value="id") Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return new ResponseEntity<>(comment.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("comment not available", HttpStatus.BAD_REQUEST);
    }

    //update comment by user
    @PutMapping("/{userEmail}/{commentId}")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment, @PathVariable(name="commentId") Long id, @PathVariable(name="userEmail") String email) {
        Date currentTime = new Date();
        Optional<Comment> comment1 = this.commentRepository.findById(id);
       if (comment1.isPresent()){
           Comment comment2 = comment1.get();
           comment2.setCommentBody(comment.getCommentBody());
           comment2.setDateCreated(currentTime);
           this.commentRepository.save(comment2);
           return new ResponseEntity<>("comment updated!", HttpStatus.OK);
       }
        return new ResponseEntity<>("comment not available", HttpStatus.BAD_REQUEST);
    }

    //delete comment on a post by user
    @DeleteMapping("/{commentId}/{userEmail}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long id, @PathVariable("userEmail") String email) {
        User existingUser = this.userRepository.findUserByEmail(email);
        if (existingUser != null) {
            Optional<Comment> commentToDelete = commentRepository.findById(id);
            if (commentToDelete.isPresent()) {
                Comment comment = commentToDelete.get();
                if (comment.getUser().getEmail().equals(email)) {
                    commentRepository.delete(comment);
                    return new ResponseEntity<>("comment deleted", HttpStatus.OK);
                }
            }
        }
        return  new ResponseEntity<>("User or comment does not exist or this is not your comment",HttpStatus.BAD_REQUEST);
    }

}
