package congvanservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("congvanservice.controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo())
                .tags(new Tag("Hệ thống quản lý công văn","API Công văn"),
                        new Tag("Hệ thống quản lý loại công văn", "API Loại công văn"),
                        new Tag("Hệ thống quản lý tài khoản", "API Tài khoản"),
                        new Tag("Hệ thống quản lý lĩnh vực", "API Lĩnh vực"),
                        new Tag("Hệ thống quản lý tài nguyên", "API tài nguyên"));
    }

    private ApiInfo apiEndPointsInfo(){
        return new ApiInfoBuilder().title("Spring Boot - REST API")
                .description("Hệ thống quản lý công văn")
                .contact(new Contact("Hieu Nghiem","www.nghiemxuanhieu97.com","nghiemxuanhieu97@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
