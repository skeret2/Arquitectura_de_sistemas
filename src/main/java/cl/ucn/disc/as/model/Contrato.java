package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@ToString
@AllArgsConstructor
@Builder
@Entity
public class Contrato extends BaseModel {

    /**
     *The Contador
     */
    @NotNull
    private Date fechaPago;

    public long diferenciaDeDias(Date fecha) {
        Instant ahora = Instant.now();
        Duration diferencia = Duration.between(ahora, fecha.toInstant());
        return diferencia.toMinutes();
    }
}
