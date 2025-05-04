package com.fernando.auth_server.controller;

import com.fernando.auth_server.dto.ClientDTO;
import com.fernando.auth_server.dto.CreateClientDTO;
import com.fernando.auth_server.services.ClientCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientRestApi {

    private final ClientCreate clientCreate;

    @PostMapping("/create")
    public ResponseEntity<ClientDTO> create(@RequestBody CreateClientDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreate.create(dto));
    }

}
