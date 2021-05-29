package com.vikki.week_9_blog_api.controller;


import com.vikki.week_9_blog_api.model.Favourites;
import com.vikki.week_9_blog_api.model.Post;
import com.vikki.week_9_blog_api.model.User;
import com.vikki.week_9_blog_api.repository.FavouritesRepository;
import com.vikki.week_9_blog_api.repository.PostRepository;
import com.vikki.week_9_blog_api.repository.UserRepository;
import com.vikki.week_9_blog_api.service.FavouritesService;
import com.vikki.week_9_blog_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stars")
public class FavouritesController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    FavouritesService favouritesService;

    @Autowired
    FavouritesRepository favouritesRepository;

    @Autowired
    UserRepository userRepository;

//    UserService userService;

    //Star a post or add to favourite list
    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<?> starPost(@PathVariable(name = "postId") Long postId, @PathVariable(name = "userId") Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Post> post1 = postRepository.findById(postId);
            if (post1.isPresent()) {
                Favourites favourites = favouritesService.getByPostAndUser(post1.get(), user.get());
                if (favourites == null) {
                    Favourites fav = new Favourites();
                    fav.setUser(user.get());
                    fav.setPost(post1.get());
                    favouritesService.addComment(fav);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //get all starred post by user
    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getStarPosts(@PathVariable(name = "userId") Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Favourites> favourites = favouritesService.findAllByUser(user.get());
            List<Post> posts = new ArrayList<>();
            for (Favourites favourite : favourites)
                posts.add(favourite.getPost());
            return new ResponseEntity<>(posts, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // delete post from favourite list
    @DeleteMapping("/{postId}/{userId}")
    public ResponseEntity<?> deleteStarredPost (@PathVariable(name = "userId") Long userId, @PathVariable(name = "postId") Long postId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Post> post = postRepository.findById(postId);
            if (post.isPresent()) {
                Favourites favourites = favouritesService.getByPostAndUser(post.get(), user.get());
                if (favourites != null) {
                    favouritesRepository.delete(favourites);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}