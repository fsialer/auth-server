package com.fernando.auth_server.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestUserDTO {
    @NotBlank(message = "Field username cannot be null or blank")
    @Email(message = "Field username must be a valid email")
    private String username;
    @NotBlank(message = "Field password cannot be null or blank")
    private String password;
    @Valid
    private CreateProfileDTO profile;
}
