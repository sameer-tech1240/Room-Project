package com.room.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerApiConfiguration {
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Er.ASAD");
        myContact.setEmail("ahmadk3asad@gmail.com");
        myContact.setUrl("www.google.com");


        Info information = new Info()
                .title("Room API")
                .version("1.0")
                .summary("Room Api for legends only")
                .description("This Application is Learning purpose where we are learning with cheers!!!")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
