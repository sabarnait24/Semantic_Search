package org.example.qdrantDB.service;

import org.example.qdrantDB.dto.client.ClientRequest;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    ResponseEntity<?> createClient(ClientRequest clientRequest);
    boolean isClientExists(String client);

    String getQdrantCollectionName(String client);
}
