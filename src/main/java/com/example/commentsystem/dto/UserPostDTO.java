package com.example.commentsystem.dto;

import com.example.commentsystem.projection.UserView;
import lombok.Data;

@Data
public class UserPostDTO {
    private Integer id;
    private String username;
    private int countPosts;
    private int countComments;
    private String lastPost;

    public static UserPostDTO fromUserView(UserView userView) {
        UserPostDTO dto = new UserPostDTO();
        dto.setId(userView.getUserId());
        dto.setUsername(userView.getUsername());
        dto.setCountPosts(userView.getCountPosts());
        dto.setCountComments(userView.getCountComments());
        dto.setLastPost(userView.getLastPost());
        return dto;
    }
}

