package formacion.cmc.springbootrest.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    
    @Bean
    public Docket api() {
    	
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(400).message("Error en la llamada.").build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("Recurso no encontrado.").build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message("Error interno.").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("formacion.cmc.springbootrest.api.v1.controller"))
                .paths(PathSelectors.any()).build();
    	
    	
    }

    private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API Rest Spring Boot")
				.description("Spring Boot Rest App").contact(new Contact("Manuel",
						"https://springfox.github.io/springfox/docs/current/", "mlopezbarranco@grupocmc.es"))
				.version("1.0").build();
    }
    
}
