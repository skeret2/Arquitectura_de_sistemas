package cl.ucn.disc.as;


import cl.ucn.disc.as.dao.PersonaFinder;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.model.Departamento;

import cl.ucn.disc.as.services.Sistema;
import cl.ucn.disc.as.services.SistemaImpl;
import io.ebean.DB;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;

import java.sql.DatabaseMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 *The Main
 *@author Profesor Diego Urrutia-Astorga
 */
@Slf4j
public class Main {
    /**
     *The Main
     *
     * @param args to use.
     */
    public static void main(String[] args){

        log.debug("Starting Main..");

        log.debug("Starting Main..");

        //get the database
        Database db = DB.getDefault();

        Sistema sistema = new SistemaImpl(db);

        Edificio edificio = Edificio.builder()
                .nombre("Y1")
                .direccion("Angamos #0610")
                .build();
        log.debug("Edificio before db: {}", edificio);

        edificio = sistema.add(edificio);
        log.debug("Edificio afer db: {}", edificio);


        // personas
        Persona persona = Persona.builder()
                .rut("20545000-9")
                .nombre("Scarlett")
                .apellidos("Zapata Cortes")
                .email("scarlettannais.zapata@gmail.com")
                .telefono("123456789")
                .build();

        log.debug("The Persona before db: ${}", persona);

        //save into the database
        db.save(persona);

        //Añadir persona
        Persona persona2 = Persona.builder()
                .rut("22432926-1")
                .nombre("Prueba1")
                .apellidos("prueba")
                .email("prueba@gmail.com")
                .telefono("123456789")
                .build();

        persona2 = sistema.add(persona2);

        //finder de personas
        PersonaFinder pf = new PersonaFinder();
        Optional<Persona> oPersona = pf.byRut("20545000-9");
        oPersona.ifPresentOrElse(p -> log.debug("Persona from db: {}", p),
                () -> log.debug("Persona no encontrada")
        );

        log.debug("Done. :)");


        Departamento departamento1 = Departamento.builder()
                .departamento(1)
                .piso(1)
                .build();

        sistema.addDepartamento(edificio, departamento1);

        Departamento departamento2= Departamento.builder()
                .departamento(2)
                .piso(1)
                .build();
        sistema.addDepartamento(edificio, departamento2);
    }
}
