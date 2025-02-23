package com.example.livechat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}