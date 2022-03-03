package com.innowise.cargo_transportation.web.controller;

import com.innowise.cargo_transportation.core.dto.request.ClientParamsRequest;
import com.innowise.cargo_transportation.core.dto.request.ClientRequest;
import com.innowise.cargo_transportation.core.dto.request.UserRequest;
import com.innowise.cargo_transportation.core.dto.response.ClientListResponse;
import com.innowise.cargo_transportation.core.dto.response.ClientResponse;
import com.innowise.cargo_transportation.core.service.ClientService;
import com.innowise.cargo_transportation.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientRestController {
    private final BCryptPasswordEncoder encoder;
    private final UserService userService;
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<URI> createClient(@RequestBody @Valid ClientRequest clientRequest) {
        UserRequest userRequest = clientRequest.getAdminInfo();
        String password = userRequest.getPassword();
        userRequest.setPassword(encoder.encode(password));
        Long clientId = clientService.createClient(clientRequest);
        userRequest.setClientId(clientId);
        userService.createUser(userRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientId).toUri();

        return ResponseEntity.created(location)
                .body(location);
    }

    @GetMapping("/{id}")
    public ClientResponse findById(@PathVariable("id") long id) {
        return clientService.findClientById(id);
    }

    @PutMapping("/{id}")
    public void updateClientById(@PathVariable("id") long id, @RequestBody ClientRequest clientRequest) {
        clientService.updateClient(id, clientRequest);
    }

    @GetMapping
    public ClientListResponse findByFilter(ClientParamsRequest clientParamsRequest) {
        return clientService.findList(clientParamsRequest);
    }

    @DeleteMapping
    public void deleteClient(@RequestBody List<Long> idList) {
        clientService.deleteClientsByIdList(idList);
    }

    @PutMapping("/activate/{id}")
    public void activateClient(@PathVariable("id") long id) {
        clientService.activateClientById(id);
    }
}
