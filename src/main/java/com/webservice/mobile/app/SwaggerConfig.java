package com.webservice.mobile.app;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    Contact contact = new Contact(
            "Raj Singha",
            "https://rajsingha.codes",
            "r.singha4520@gmail.com"
    );

    List<VendorExtension> vendorExtensions = new ArrayList<>();

    ApiInfo apiInfo = new ApiInfo(
            "Mobile App WebService - RESTful Web Service Documentation ",
            "This pages documents Mobile App WebService RESTful Web Service Endpoints",
            "1.0",null,
            contact,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            vendorExtensions);

    @Bean
    public Docket apiDocket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .protocols(new HashSet<>(Arrays.asList("HTTP","HTTPs")))
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.webservice.mobile.app"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
