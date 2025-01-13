package org.koreait.member.controllers;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class RequestLogin {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
