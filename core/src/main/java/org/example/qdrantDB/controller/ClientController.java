package org.example.qdrantDB.controller;

import org.example.qdrantDB.dto.client.ClientRequest;
import org.example.qdrantDB.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/admin/clients")
    public ResponseEntity<?> createClient(@RequestBody ClientRequest clientRequest){
        return  clientService.createClient(clientRequest);
    }
}
