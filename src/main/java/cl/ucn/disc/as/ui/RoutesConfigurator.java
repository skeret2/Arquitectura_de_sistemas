package cl.ucn.disc.as.ui;

import io.javalin.Javalin;

public interface RoutesConfigurator {

    /**
     * Configure the routes.
     *
     * @param javalin to configure.
     */
    void configure(Javalin javalin);
}