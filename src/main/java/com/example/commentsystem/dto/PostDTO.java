package com.example.commentsystem.dto;

import com.example.commentsystem.model.Post;
import com.example.commentsystem.projection.PostView;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostDTO {
    private Integer id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDTO user;


    public static PostDTO fromModelToDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        List<CommentDTO> comments = post.getComments().stream()
                .map(CommentDTO::fromModelToDTO)
                .collect(Collectors.toList());
        dto.setComments(comments);
        dto.setUser(UserDTO.toDTO(post.getUser()));
        return dto;
    }

    public static PostDTO fromProjToDTO(PostView postView) {
        PostDTO dto = new PostDTO();
        dto.setId(postView.getId());
        dto.setTitle(postView.getTitle());
        dto.setBody(postView.getBody());
        return dto;
    }
}
