package cl.ucn.disc.as.model;

import java.time.Instant;
import lombok.Getter;
import lombok.NonNull;

public class Pago {

    /**
     * The fechaPago
     */
    @NonNull
    @Getter
    private Instant fechaPago;

    /**
     * The monto
     */
    @NonNull
    @Getter
    private Integer monto;

    /**
     * The contrato
     */
}
