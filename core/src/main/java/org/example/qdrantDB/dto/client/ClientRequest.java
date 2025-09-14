package org.example.qdrantDB.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private String client;
    private String collectionName;
}
