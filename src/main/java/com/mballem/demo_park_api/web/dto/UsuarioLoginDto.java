package com.mballem.demo_park_api.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioLoginDto {

    @NotBlank(message = "{NotBlank.usuarioLoginDto.username}")
    @Email(message = "{Email.usuarioLoginDto.username}", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String username;

    @NotBlank(message = "{NotBlank.usuarioLoginDto.password}")
    @Size(min = 6, max = 6, message = "{Size.usuarioLoginDto.password}")
    private String password;
}
