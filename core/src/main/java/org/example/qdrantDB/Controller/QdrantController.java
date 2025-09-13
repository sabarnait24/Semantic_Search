package org.example.qdrantDB.Controller;

import ch.qos.logback.core.joran.sanity.Pair;
import lombok.extern.slf4j.Slf4j;
import org.example.qdrantDB.model.insert.InsertRequests;
import org.example.qdrantDB.model.search.SearchRequest;
import org.example.qdrantDB.service.QdrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class QdrantController {

    @Autowired
    private  QdrantService qdrantService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody InsertRequests insertRequests, @RequestHeader("X-Client-Id") String clientId){
       return qdrantService.insert(insertRequests, clientId);
    }

//    public ResponseEntity<?> search (@ResponseBody SearchRequest searchRequest){
//        //
//    }

}
