package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.Getter;
import javax.persistence.Entity;

@ToString
@AllArgsConstructor
@Builder
@Entity
public class Departamento extends BaseModel  {
    /**
     * The Apartment Number
     */
    @NotNull
    @Getter
    private Integer departamento;

    /**
     * The Piso
     */
    @NotNull
    @Getter
    private String piso;

}
