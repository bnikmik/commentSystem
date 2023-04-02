package com.example.commentsystem.repository;

import com.example.commentsystem.model.Comment;
import com.example.commentsystem.projection.CommentView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = "SELECT id AS id, body as body, post_id AS postId FROM comment WHERE post_id IN (?1) ", nativeQuery = true)
    List<CommentView> findByPostIdIn(List<Integer> postIds);
}
