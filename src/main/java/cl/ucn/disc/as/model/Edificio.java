package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import javax.persistence.Entity;

/**
 * Class Edificio
 *
 * @autor Arquitectura de sistemas
 */

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Edificio extends BaseModel {

    /**
     * The Name
     */
    @Getter
    @NotNull
    private String nombre;

    /**
     * The direccion
     */
    @Getter
    @NotNull
    private String direccion;

    /**
     * The departaments
     */
    @Getter
    @NotNull
    private List<Departamento> departamentos;

    /**
     * Add a departament to the ediifcio
     */
    public void addDepartamento(Departamento departamento) {
        this.departamentos.add(departamento);
    }

}
