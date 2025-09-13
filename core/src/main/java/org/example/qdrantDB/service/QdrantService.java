package org.example.qdrantDB.service;

import lombok.extern.slf4j.Slf4j;
import org.example.qdrantDB.DAO.QdrantRepository;
import org.example.qdrantDB.model.insert.InsertRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class QdrantService {

    @Autowired
    private QdrantRepository qdrantRepository;

    public ResponseEntity<?> insert(@RequestBody InsertRequests insertRequests, @RequestHeader("X-Client-Id") String clientId){
        try{
            if(insertRequests == null || insertRequests.getInsertRequestList() == null || insertRequests.getInsertRequestList().isEmpty()){
                throw new IllegalArgumentException("Argument is Illegal");
            }

            String collectionName = "sabarna"; // hard coded need to chnge

            List<Map<String, Object>> points = insertRequests.getInsertRequestList().stream().map(request -> {
                if (request.getId() == null) {
                    throw new IllegalArgumentException("Id is missing");
                }
                if (request.getText() == null || request.getText().isEmpty()) {
                    throw new IllegalArgumentException("Text is missing");
                }
                return Map.of("id", request.getId(),
                        "vector", "",
                        "payload", Map.of(
                                "text", request.getText(),
                                "payload", request.getMetadata()
                        ));
            }).toList();

            // insert into qdrant
            qdrantRepository.insert(points , collectionName);

            return ResponseEntity.status(200).body("Data inserted successfully ");

        } catch (Exception e) {
            log.error("exception occured" , e.getMessage());
            return ResponseEntity.status(500).body("Internal Server Error");
        }

    }
}
