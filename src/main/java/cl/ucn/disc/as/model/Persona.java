/*
 * Copyright (c) 2023. Arquitectura de Sistemas, DISC, UCN.
 */

package cl.ucn.disc.as.model;

import cl.ucn.disc.as.model.exceptions.IllegalDomainException;
import cl.ucn.disc.as.utils.ValidationUtils;
import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * The Persona class.
 *
 * @author Diego Urrutia-Astorga.
 */
@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Persona extends BaseModel {

    /**
     * The RUT.
     */
    @NotNull
    private String rut;

    /**
     * The Nombre.
     */
    @NotNull
    private String nombre;

    /**
     * The Apellidos.
     */
    @NotNull
    private String apellidos;

    /**
     * The Email.
     */
    @NotNull
    private String email;

    /**
     * The Telefono.
     */
    @NotNull
    private String telefono;

    /**
     * Custom builder to validate.
     */
    public static class PersonaBuilder {
        /**
         *
         * @return the Persona
         */
        public Persona build() {
            // validate rut
            if (!ValidationUtils.isRutValid(this.rut)) {
                throw new IllegalDomainException(
                        "Rut no válido: " + this.rut
                );
            }

            // validate the email
            if (!ValidationUtils.isEmailValid(this.email)){
                throw new IllegalDomainException(
                        "Email no válido: " + this.email
                );
            }

            //Añadir validaciones que faltan

            return new Persona(
                    this.rut,
                    this.nombre,
                    this.apellidos,
                    this.email,
                    this.telefono
            );
        }
    }

    /**
     * The Contrato
     */
    //@NotNull
    //private String contrato;

    /**
     * The Departamento
     */
    //@NotNull
    //private int departamento;
}
