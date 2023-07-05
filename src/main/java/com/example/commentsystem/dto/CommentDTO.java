package com.example.commentsystem.dto;

import com.example.commentsystem.model.Comment;
import com.example.commentsystem.projection.CommentView;
import lombok.Data;

@Data
public class CommentDTO {
    private Integer id;
    private String body;

    public static CommentDTO fromModelToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;
    }

    public static CommentDTO fromProjToDTO(CommentView commentView) {
        CommentDTO dto = new CommentDTO();
        dto.setId(commentView.getId());
        dto.setBody(commentView.getBody());
        return dto;
    }


}
