package com.vikki.week_9_blog_api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_table")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private  String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    private Date dateCreated;

    @OneToMany(targetEntity = Post.class, cascade=CascadeType.ALL)
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL,orphanRemoval = true)
     private List<Comment> comments;

    @OneToOne(targetEntity = PostLike.class, cascade = CascadeType.ALL,orphanRemoval = true)
    private PostLike postLike;


//
//    @OneToMany(targetEntity = Favourites.class, cascade=CascadeType.ALL)
//     private List<Favourites> favourite;
//
//    @OneToMany(targetEntity = Connections.class, cascade=CascadeType.ALL)
//    private List<Connections> following;

}
