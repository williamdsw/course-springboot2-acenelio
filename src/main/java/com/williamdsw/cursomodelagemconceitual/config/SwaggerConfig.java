package com.williamdsw.cursomodelagemconceitual.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig 
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
	
	private final ResponseMessage m201 = customResponseHeader ();
	private final ResponseMessage m204put = simpleMessage (204, "Atualização OK");
	private final ResponseMessage m204del = simpleMessage (204, "Deleção OK");
	private final ResponseMessage m403 = simpleMessage (403, "Não autorizado");
	private final ResponseMessage m404 = simpleMessage (404, "Não encontrado");
	private final ResponseMessage m422 = simpleMessage (422, "Erro de validação");
	private final ResponseMessage m500 = simpleMessage (500, "Erro inesperado");
	
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
	
	// Configuracao das informacoes da API
	@Bean
	public Docket api ()
	{
		String resourcesPackage = "com.williamdsw.cursomodelagemconceitual.resources";
		return new Docket (DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages (false)
				.globalResponseMessage (RequestMethod.GET, Arrays.asList (m403, m404, m500))
				.globalResponseMessage (RequestMethod.POST, Arrays.asList (m201, m403, m422, m500))
				.globalResponseMessage (RequestMethod.PUT, Arrays.asList (m204put, m403, m404, m422, m500))
				.globalResponseMessage (RequestMethod.DELETE, Arrays.asList (m204del, m403, m404, m422, m500))
				.select ()
				.apis (RequestHandlerSelectors.basePackage(resourcesPackage))
				.paths (PathSelectors.any())
				.build ()
				.apiInfo (apiInfo ());
	}
	
	// Personalizando informacoes da API
	private ApiInfo apiInfo ()
	{
		String title = "API do curso Spring Boot";
		String description = "Esta API é utilizada no curso de Spring Boot do prof. Nelio Alves";
		String version = "Versão 1.0";
		String termsOfServiceUrl = "https://www.udemy.com/terms";
		Contact contact = new Contact("William Santos", "https://www.udemy.com/user/william-reznor/", "williamdsw@outlook.com");
		String license = "Permitido uso para estudantes";
		String licenseUrl = "https://www.udemy.com/terms";
		return new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl, Collections.emptyList ());
	}
	
	// Retorna novo ResponseMessage com base no codigo + messagem
	private ResponseMessage simpleMessage (int code, String message)
	{
		return new ResponseMessageBuilder ().code (code).message (message).build ();
	}
	
	// Customiza cabecalhos da resposta
	private ResponseMessage customResponseHeader ()
	{
		Map<String, Header> map = new HashMap<> ();
		map.put ("location", new Header ("location", "URI do novo recurso", new ModelRef ("string")));
		return new ResponseMessageBuilder ().code (201).message ("Recurso criado").headersWithDescription (map).build ();		
	}
}