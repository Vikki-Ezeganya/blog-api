//package com.vikki.week_9_blog_api.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//import javax.persistence.*;
//
//@Data
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="comment_like")
//public class CommentLike {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "comment_id")
//    private Comment comment;
//}
