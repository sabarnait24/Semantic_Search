package org.example.qdrantDB.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertRequests {
     private List<InsertRequest> insertRequestList ;
}
