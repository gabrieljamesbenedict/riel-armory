package com.gjbmloslos.rielarmoryspringboot.security.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Long roleId;
}
