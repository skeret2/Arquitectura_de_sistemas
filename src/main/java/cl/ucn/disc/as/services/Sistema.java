package cl.ucn.disc.as.services;

import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.model.Contrato;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


/**
 * System Operations
 *
 * @autor Arquitectura de sistema
 */
public interface Sistema {
    /**
     * Agregar un edificio al sistema
     *
     * @param edificio a agregar
     */
    Edificio add(Edificio edificio);

    Persona add(Persona persona);

    /**
     * Agregar un departamento al sistema
     *
     * @param edificio a agregar
     * @param departamento a agregar
     */
    Departamento addDepartamento(Edificio edificio,Departamento departamento);

    /**
     * Agregar un departamento al sistema
     *
     * @param edificio a agregar
     * @param departamento a agregar
     */
    Departamento addDepartamento(Long edificio,Departamento departamento);

    /**
     * Realizar un contrato
     *
     * @param duenio a agregar
     * @param departamento a agregar
     * @param fechaPago a agregar
     */
    Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago);

    /**
     * Pobla la base de datos.
     */
    public void populate();


    /**
     * Obtener listado de personas del sistema.
     */
    List<Persona> getPersonas();

    /**
     * Obtener listado de contratos del sistema.
     */
    List<Contrato> getContratos();

    /**
     * Obtener persona por rut.
     */
    Optional<Persona> getPersona(String rut);

}

