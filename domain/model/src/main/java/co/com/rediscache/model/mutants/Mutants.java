package co.com.rediscache.model.mutants;

import lombok.Builder;
import lombok.Data;

/**
 * Clase para el mapeo del modelo mutants
 */
@Data
@Builder(toBuilder = true)
public class Mutants {

    private String id;
    private String type;
    private String dnaChain;
}
