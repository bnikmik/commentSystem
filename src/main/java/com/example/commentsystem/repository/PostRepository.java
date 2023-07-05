package com.example.commentsystem.repository;

import com.example.commentsystem.model.Post;
import com.example.commentsystem.projection.PostView;
import com.example.commentsystem.projection.UserView;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>, JpaSpecificationExecutor<Post> {
    @Query(value = "SELECT u.id                 AS userId,\n" +
            "       u.username           AS username,\n" +
            "       COUNT(DISTINCT p.id) AS countPosts,\n" +
            "       COUNT(c.id)          AS countComments,\n" +
            "       (SELECT post.body\n" +
            "        FROM post\n" +
            "        WHERE post.user_id = u.id\n" +
            "        ORDER BY (SELECT COUNT(*)\n" +
            "                  FROM comment\n" +
            "                  WHERE comment.post_id = post.id) DESC, post.id DESC\n" +
            "        LIMIT 1)            AS lastPost\n" +
            "FROM users u\n" +
            "         LEFT JOIN post p ON p.user_id = u.id\n" +
            "         LEFT JOIN comment c ON c.post_id = p.id\n" +
            "GROUP BY u.id, u.username\n" +
            "ORDER BY countComments DESC\n" +
            "LIMIT 10",nativeQuery = true)
    List<UserView> getTopByCountComments();

    @EntityGraph(attributePaths = {"comments", "user"})
    List<Post> findAllBy();

    List<PostView> findAllBy(Pageable pageable);

}
