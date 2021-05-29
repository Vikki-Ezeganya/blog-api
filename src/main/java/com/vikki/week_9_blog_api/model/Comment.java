package com.vikki.week_9_blog_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date dateCreated;

    @Column(nullable = false)
    private String commentBody;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "post_id")
    private Post post;

//    @OneToMany
//    private List<CommentLike> commentLike;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
