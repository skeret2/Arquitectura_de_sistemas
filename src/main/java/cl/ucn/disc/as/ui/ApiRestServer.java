package cl.ucn.disc.as.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;

@Slf4j
public final class ApiRestServer {

    /**
     * The constructor.
     */
    private ApiRestServer() {
        // Nothing here.
    }

    private static Gson createAndConfigureGson() {
        TypeAdapter<Instant> instantTypeAdapter = new TypeAdapter<Instant>() {

            /**
             * Writes one JSON value (an array, object, string, number, boolean or null)
             * for {@code instant}.
             *
             * @param out
             * @param instant the Java object to write. May be null.
             * @throws IOException
             */
            @Override
            public void write(JsonWriter out, Instant instant) throws IOException {
                if (instant == null) {
                    out.nullValue();
                    return;
                }
                else {
                    out.value(instant.toEpochMilli());
                }
            }

            /**
             * Reads one JSON value (an array, object, string, number, boolean or null)
             * and converts it to a Java object. Returns the converted object.
             * @param in
             * @return the converted Java object. May be null.
             * @throws IOException
             */
            @Override
            public Instant read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                return Instant.ofEpochMilli(in.nextLong());
            }
        };

        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Instant.class, instantTypeAdapter)
                .create();
    }

    private static Javalin createAndConfigureJavalin() {
        JsonMapper jsonMapper = new JsonMapper() {
            private static final Gson GSON = createAndConfigureGson();
            @NotNull
            @Override
            public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
                return GSON.fromJson(json, targetType);
            }

            @NotNull
            @Override
            public String toJsonString(@NotNull Object obj, @NotNull Type type) {
                return GSON.toJson(obj, type);
            }
        };

        return Javalin.create(config -> {
            config.jsonMapper(jsonMapper);
            config.compression.gzipOnly(9);
            config.requestLogger.http((ctx, ms) -> {
                log.info("{} {} in {} ms.", ctx.method(), ctx.fullUrl(), ms);
            });
            config.plugins.enableDevLogging();
        });
    }

    /**
     * Start the server.
     * @param port to use
     * @param routesConfigurator
     */
    public static Javalin start(final Integer port, final RoutesConfigurator routesConfigurator){
        if (port < 1024 || port > 65535) {
            log.error("Bad port: {}", port);
            throw new IllegalArgumentException("Port must be between 1024 and 65535");
        }

        log.debug("Starting the server in port {} ..", port);

        Javalin app = createAndConfigureJavalin();

        routesConfigurator.configure(app);

        Runtime.getRuntime().addShutdownHook(new Thread(app::stop));

        app.events(event -> {
            event.serverStarting(() -> {
                log.debug("Starting the server ..");
            });
            event.serverStarted(() -> {
                log.debug("Server started!");
            });
            event.serverStopping(() -> {
                log.debug("Stopping the server ..");
            });
            event.serverStopped(() -> {
                log.debug("Server stopped!");
            });
        });

        return app.start(port);
    }
}