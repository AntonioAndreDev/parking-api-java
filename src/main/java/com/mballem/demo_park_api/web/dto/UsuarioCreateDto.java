package com.mballem.demo_park_api.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioCreateDto {

    @NotBlank(message = "{NotBlank.usuarioCreateDto.username}")
    @Email(message = "{Email.usuarioCreateDto.username}", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String username;

    @NotBlank(message = "{NotBlank.usuarioCreateDto.password}")
    @Size(min = 6, max = 6, message = "{Size.usuarioCreateDto.password}")
    private String password;
}
