package com.fernando.auth_server.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String clientId;
    private String clientName;
}
