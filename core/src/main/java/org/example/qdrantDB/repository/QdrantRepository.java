package org.example.qdrantDB.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
@Data
public class QdrantRepository {
    @Autowired
    private WebClient webClient;

    public void insert(List<Map<String,Object>> points , String collectionName){
        //
        webClient.put()
                .uri("/collections/{collection}/points", collectionName)
                .bodyValue(Map.of("points", points))
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }



}
