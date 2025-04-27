package com.fernando.auth_server.dto;

import com.fernando.auth_server.enums.ErrorType;
import lombok.*;

import java.util.List;

@Builder
public record ErrorResponseDTO(
         String code,
         ErrorType type,
         String message,
         List<String> details,
         String timestamp
) {

}
