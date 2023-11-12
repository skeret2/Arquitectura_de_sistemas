package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.Getter;

import java.time.Instant;

import javax.persistence.Entity;
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Pago extends BaseModel {
    /**
     *  fecha de pago
     */
    @NotNull
    private Instant fechaPago;

    /**
     *  monto de pago
     */
    @NotNull
    private Integer montoPago;
}