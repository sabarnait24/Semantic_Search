package org.example.qdrantDB.service;

import lombok.extern.slf4j.Slf4j;
import org.example.qdrantDB.dto.client.ClientRequest;
import org.example.qdrantDB.exception.ClientAlreadyExistsException;
import org.example.qdrantDB.model.ClientEntity;
import org.example.qdrantDB.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<?> createClient(@RequestBody ClientRequest clientRequest) {
        try {
            clientRepository.findByClient(clientRequest.getClient()).ifPresent(clientEntity -> {
                throw new ClientAlreadyExistsException("Client Already Exists : {}" + clientRequest.getClient());
            });

            ClientEntity clientEntity = new ClientEntity();

            clientEntity.setId(UUID.randomUUID().toString());
            clientEntity.setClient(clientRequest.getClient());
            clientEntity.setCollectionName(clientRequest.getCollectionName());

            clientRepository.save(clientEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "New client created successfully for the client " + clientRequest.getClient()));
        } catch (Exception e) {
            e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                   Map.of("error" , "Internal Server Error"));
        }
    }
}
