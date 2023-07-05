package com.example.commentsystem.dto;

import com.example.commentsystem.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String username;

        public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }
}
