package com.vikki.week_9_blog_api.repository;

import com.vikki.week_9_blog_api.model.Favourites;
import com.vikki.week_9_blog_api.model.Post;
import com.vikki.week_9_blog_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Long> {

    List<Favourites> findAllByUser(User user);
    Favourites findByPost(Post post);
    Favourites findByPostAndUser(Post post, User user);
}
