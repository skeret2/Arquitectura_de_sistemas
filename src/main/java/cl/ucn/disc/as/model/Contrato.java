package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Getter;

import java.util.ArrayList;

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


    public static class ContratoBuilder {
        /**
         *
         * @return the Contrato
         */
        public Contrato build() {
            //inicializa si pagos es nulo
            if (this.pagos == null) {
                this.pagos = new ArrayList<>();
            }
            return new Contrato(fechaPago, persona, departamento, pagos);

        }
    }



}
