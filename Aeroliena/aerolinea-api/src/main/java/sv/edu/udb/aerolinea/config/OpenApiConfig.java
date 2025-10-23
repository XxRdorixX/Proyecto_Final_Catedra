package sv.edu.udb.aerolinea.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 *  Configuración central para Swagger (OpenAPI)
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI aerolineaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Sistema de Boletos Aéreos")
                        .description("Documentación de la API REST para la gestión de aerolíneas, vuelos, pasajeros, reservaciones, pagos y reclamos.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Rodrigo Escobar")
                                .email("soporte@aerolinea.com")
                                .url("http://localhost:8080/swagger-ui.html")))
                .servers(List.of(new Server().url("http://localhost:8080").description("Servidor local de desarrollo")));
    }
}
