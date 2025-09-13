package org.example.qdrantDB.model.insert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertRequests {
     @NonNull
     private List<InsertRequest> insertRequestList ;
}
