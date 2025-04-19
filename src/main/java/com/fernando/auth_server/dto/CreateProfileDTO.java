package com.fernando.auth_server.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProfileDTO {
    @NotBlank(message = "Field names cannot be null or blank")
    private String names;
    @NotBlank(message = "Field lastNames cannot be null or blank")
    private String lastNames;
}
