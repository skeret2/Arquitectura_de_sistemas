package cl.ucn.disc.as.services;

import cl.ucn.disc.as.exceptions.SistemaException;

import io.ebean.Database;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Pago;
import cl.ucn.disc.as.model.Departamento;


import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import java.util.Date;
import java.util.List;





import javax.persistence.PersistenceException;
@Slf4j
public class SistemaImpl implements Sistema {
    public SistemaImpl(Database database){
        this.database = database;
    }
    private final Database database;

    /**
     * Agrega un nuevo edificio a la base de datos.
     */
    @Override
    public Edificio add(@NotNull Edificio edificio){
        try {
            this.database.save(edificio);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un edificio", ex);
        }
        return edificio;
    }
    /**
     * Agrega una nueva persona a la base de datos.
     */
    @Override
    public Persona add(@NotNull Persona persona){
        try {
            this.database.save(persona);
        } catch (PersistenceException ex) {
            log.error("Error", ex);
            throw new SistemaException("Error al agregar una persona", ex);
        }
        return persona;
    }

    /**
     * Agrega un nuevo departamento al edificio proporcionado.
     */
    @Override
    public Departamento addDepartamento(@NotNull Edificio edificio, @NotNull Departamento departamento) {
        try {
            this.database.save(departamento);

            // Si el edificio ya existe en la base de datos, no es necesario guardarlo de nuevo.
            // Solo agrega el departamento al edificio existente.
            edificio.addDepartamento(departamento);

        } catch (PersistenceException ex) {
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }
        return departamento;
    }


    /**
     * Realiza un contrato entre una persona y un departamento.
     */
    @Override
    public Contrato realizarContrato(Persona duenio, Departamento departamento, Date fechaPago) {
        // Verificar si la persona y el departamento existen en la base de datos
        Persona persona = this.database.find(Persona.class, duenio.getId());
        Departamento depto = this.database.find(Departamento.class, departamento.getId());
        if (persona == null || depto == null) {
            log.error("Persona o departamento no existe");
        }
        // Crear el contrato
        Contrato contrato = Contrato.builder()
                .fechaPago(fechaPago)
                .persona(persona)
                .departamento(depto)
                .build();
        // Guardar el contrato
        try {
            this.database.save(contrato);
        } catch (PersistenceException ex) {
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un contrato", ex);
        }

        return contrato;
    }

    public List<Persona> getPersonas() {
        return this.database.find(Persona.class).findList();
    }

    public List<Contrato> getContratos() {
        return this.database.find(Contrato.class).findList();
    }

    public List<Pago> getPagos(String rut) {
        Contrato contrato = this.database.find(Contrato.class, rut);
        assert contrato != null;
        return contrato.getPagos();
    }


}