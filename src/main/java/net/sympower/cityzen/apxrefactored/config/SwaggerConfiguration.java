package net.sympower.cityzen.apxrefactored.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {"net.sympower.cityzen.apxrefactored"})
public class SwaggerConfiguration {

    @Bean
    public OpenAPI directDebitOfflineApplicationAPI(@Value("${application.version}") String version) {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("ivapx file parser")
                                .description(
                                        "Hourly Parsing Service")
                                .contact(
                                        new Contact()
                                                .name("Developers")
                                                .email("dummy@dummy.com"))
                                .version(version))
                .tags(tags());
    }

    private List<Tag> tags() {
        List<Tag> tags = new ArrayList<>();
        Tag tag =
                new Tag()
                        .name("IVAPX File Parser Application")
                        .description("Hourly File Parsing Service");
        tags.add(tag);
        return tags;
    }
}

