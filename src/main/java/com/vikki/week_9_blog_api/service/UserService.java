package com.vikki.week_9_blog_api.service;


import com.vikki.week_9_blog_api.model.User;
import com.vikki.week_9_blog_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
}
