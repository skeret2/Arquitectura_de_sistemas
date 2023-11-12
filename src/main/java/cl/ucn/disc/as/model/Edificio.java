package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.ArrayList;
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
     * The Departamentos
     */
    @OneToMany(cascade = CascadeType.ALL)
    @Getter
    private List<Departamento> departamentos;

    /**
     * Add a departament to the ediifcio
     */
    public void addDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

}
