package com.vikki.week_9_blog_api.service;

import com.vikki.week_9_blog_api.model.Favourites;
import com.vikki.week_9_blog_api.model.Post;
import com.vikki.week_9_blog_api.model.User;
import com.vikki.week_9_blog_api.repository.FavouritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouritesService {
    @Autowired
    FavouritesRepository favouritesRepository;

    public void addComment(Favourites favourites) {
        favouritesRepository.save(favourites);
    }

    public List<Favourites> findAllByUser(User user) {
        return favouritesRepository.findAllByUser(user);
    }

    public Favourites findByPost(Post post) {
        return favouritesRepository.findByPost(post);
    }

    public void deleteFavourite(Favourites favourites) {
        favouritesRepository.delete(favourites);
    }

    public Favourites getByPostAndUser(Post post, User user) {
        return favouritesRepository.findByPostAndUser(post, user);
    }
}

