package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Getter;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@ToString
@AllArgsConstructor
@Builder
@Entity
public class Contrato extends BaseModel {

    /**
     *The Contador
     */
    @NotNull
    @Getter
    private Date fechaPago;

    public long diferenciaDeDias(Date fecha) {
        Instant ahora = Instant.now();
        Duration diferencia = Duration.between(ahora, fecha.toInstant());
        return diferencia.toMinutes();
    }

    /**
     * The persona
     */
    @Getter
    @NotNull
    private Persona persona;

    /**
     * The departamento
     */
    @Getter
    @NotNull
    private Departamento departamento;

    /**
     * The pago list
     */
    @Getter
    @NotNull
    private List<Pago> pagos;


}
