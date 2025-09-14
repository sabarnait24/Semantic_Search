package org.example.qdrantDB.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.qdrantDB.dto.insert.InsertRequests;
import org.example.qdrantDB.service.QdrantService;
import org.example.qdrantDB.service.QdrantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class QdrantController {

    @Autowired
    private QdrantService qdrantService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody InsertRequests insertRequests, @RequestHeader("X-Client-Id") String clientId){
       return qdrantService.insert(insertRequests, clientId);
    }

//    public ResponseEntity<?> search (@ResponseBody SearchRequest searchRequest){
//        //
//    }

}
