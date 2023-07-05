package com.example.commentsystem.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String body;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post")
    @Fetch(FetchMode.SUBSELECT)
    private List<Comment> comments;
}
