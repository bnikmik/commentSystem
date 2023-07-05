package com.example.commentsystem.service;

import com.example.commentsystem.dto.CommentDTO;
import com.example.commentsystem.dto.PostDTO;
import com.example.commentsystem.dto.UserPostDTO;
import com.example.commentsystem.projection.CommentView;
import com.example.commentsystem.projection.PostView;
import com.example.commentsystem.projection.UserView;
import com.example.commentsystem.repository.CommentRepository;
import com.example.commentsystem.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    public List<UserPostDTO> getTopByCountComments() {
        List<UserView> users = postRepository.getTopByCountComments();
        return users.stream().map(UserPostDTO::fromUserView)
                .collect(Collectors.toList());
    }

    public List<PostDTO> getAllPostWithComments() {
        return postRepository.findAllBy().stream().map(PostDTO::fromModelToDTO).collect(Collectors.toList());
    }

    public List<PostDTO> getAllWithPageable(Integer pageNumber, Integer pageSize) {
        List<PostView> viewList = postRepository.findAllBy(PageRequest.of(pageNumber, pageSize));
        List<CommentView> commentList = commentRepository.findByPostIdIn(viewList.stream().map(PostView::getId).collect(Collectors.toList()));
        return viewList.stream().map(PostDTO::fromProjToDTO)
                .peek(postDTO -> postDTO.setComments(commentList.stream().filter(comment -> comment.getId().equals(postDTO.getId())).map(CommentDTO::fromProjToDTO).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<PostDTO> getPostsWithSpecification(String body) {
        return postRepository.findAll((root, query, criteriaBuilder) -> {
                    root.fetch("comments", JoinType.LEFT).fetch("user", JoinType.LEFT);
                    query.distinct(true);
                    return criteriaBuilder.like(root.join("comments").get("body"), "%" + body + "%");
                })
                .stream()
                .map(PostDTO::fromModelToDTO)
                .collect(Collectors.toList());
    }
}
