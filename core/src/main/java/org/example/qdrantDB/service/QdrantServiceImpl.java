package org.example.qdrantDB.service;

import lombok.extern.slf4j.Slf4j;
import org.example.qdrantDB.repository.QdrantRepository;
import org.example.qdrantDB.dto.insert.InsertRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class QdrantServiceImpl implements QdrantService {

    @Autowired
    private QdrantRepository qdrantRepository;

    @Autowired
    private ClientService clientService;

    public ResponseEntity<?> insert(@RequestBody InsertRequests insertRequests, @RequestHeader("X-Client-Name") String client){
        try{
            if(insertRequests == null || insertRequests.getInsertRequestList() == null || insertRequests.getInsertRequestList().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request !!");
            }

            String collectionName = clientService.getQdrantCollectionName(client);

            List<Map<String, Object>> points = getQdrantPoints(insertRequests);

            qdrantRepository.insert(points , collectionName);

            return ResponseEntity.status(HttpStatus.OK).body("Data inserted successfully !!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error !!");
        }
    }

    private  List<Map<String, Object>>  getQdrantPoints(InsertRequests insertRequests){
        return insertRequests.getInsertRequestList().stream().map(request -> {
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
    }
}
