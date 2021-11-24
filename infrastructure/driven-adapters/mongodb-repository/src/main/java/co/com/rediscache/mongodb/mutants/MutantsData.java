package co.com.rediscache.mongodb.mutants;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "DNAAnalysis")
@NoArgsConstructor
class MutantsData {

    @Id
    private String id;
    private String type;
    private String dnaChain;
}
