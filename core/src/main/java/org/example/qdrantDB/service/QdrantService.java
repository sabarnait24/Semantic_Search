package org.example.qdrantDB.service;

import org.example.qdrantDB.dto.insert.InsertRequests;
import org.springframework.http.ResponseEntity;

public interface QdrantService {
    ResponseEntity<?> insert(InsertRequests insertRequests, String clientId);
}
