package cl.ucn.disc.as;


import cl.ucn.disc.as.dao.PersonaFinder;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;

import cl.ucn.disc.as.services.Sistema;
import cl.ucn.disc.as.services.SistemaImpl;
import io.ebean.DB;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;
import io.javalin.Javalin;

import java.sql.DatabaseMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.time.Instant;
import cl.ucn.disc.as.ui.ApiRestServer;
import cl.ucn.disc.as.ui.RoutesConfigurator;
import cl.ucn.disc.as.ui.WebController;


/**
 *The Main

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
        Javalin app = ApiRestServer.start(7070, new WebController());

        log.debug("Done.");

    }
}