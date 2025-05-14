package com.fernando.auth_server.dto;

import com.fernando.auth_server.dto.validation.EnumValidator;
import com.fernando.auth_server.enums.TypeSex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

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
    @NotNull(message = "Field birth cannot be null")
    private LocalDate birth;
    @NotBlank(message = "Field sex cannot be null or blank")
    @EnumValidator(enumClass = TypeSex.class, message = "Type sex is not valid")
    private String sex;
}
