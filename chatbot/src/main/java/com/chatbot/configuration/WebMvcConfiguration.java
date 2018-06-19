package com.chatbot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.chatbot.component.RequestTimeInterceptor;

// Configuración del WebMvc  en la que se añade el time interceptor para la creación de los logs
@Configuration
public class WebMvcConfiguration {

	// Instanciación del interceptor
	@Autowired
	@Qualifier("requestTimeInterceptor")
	private RequestTimeInterceptor requestTimeInterceptor;

	// Inserción del interceptor al registro con su método apropiado
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(requestTimeInterceptor);
	}

}
