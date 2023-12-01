package cl.ucn.disc.as;


import lombok.extern.slf4j.Slf4j;
import io.javalin.Javalin;
import cl.ucn.disc.as.ui.ApiRestServer;
import cl.ucn.disc.as.ui.WebController;
import cl.ucn.disc.as.grpc.PersonaGrpcServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

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
    public static void main(String[] args) throws IOException, InterruptedException {

        log.debug("Starting main library path: {}.", System.getProperty("java.library.path"));

        // Start the API Rest
        log.debug("Starting the API Rest server ..");
        Javalin app = ApiRestServer.start(7070, new WebController());

        log.debug("Done.");

        // Start the gRPC server
        log.debug("Starting the gRPC server ..");
        Server server = ServerBuilder
                .forPort(50123)
                .addService(new PersonaGrpcServiceImpl())
                .build();
        server.start();

        // Shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
        // Wait for the shutdown
        server.awaitTermination();

        log.debug("Done.");
    }
}