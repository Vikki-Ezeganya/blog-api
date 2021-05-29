//package com.vikki.week_9_blog_api.model;
//
//import com.vikki.week_9_blog_api.model.User;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name = "follows")
//public class Follows implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "follower_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "followee_id")
//    private User following;
//}
