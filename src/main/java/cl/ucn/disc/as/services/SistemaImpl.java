package cl.ucn.disc.as.services;
import cl.ucn.disc.as.exceptions.SistemaException;
import cl.ucn.disc.as.model.Edificio;
import io.ebean.Database;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.PersistenceException;
@Slf4j
public class SistemaImpl implements Sistema {
    public SistemaImpl(Database database){
        this.database = database;
    }
    private final Database database;

    /**
     *{@inheritDoc}
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
}
