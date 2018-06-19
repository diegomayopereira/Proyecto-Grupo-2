package com.chatbot.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chatbot.repository.LogRepository;

//Creación del componente requestTimeInterceptor, el cuál calcula tiempos y los publica a través de Logs
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	// Llamada al repositorio de logs
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;

	// Creación del Log
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);

	// Se asigna el timestamp del principio de la request a la variable StartTime,
	// esto se realiza en el método preHandle del HandlerInterceptorAdapter
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("startTime", System.currentTimeMillis());

		return true;
	}

	// En este método se registra y persiste la fecha de acceso a una request, los
	// detalles de esta, el username que la utilizó y la url accedida
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// Llama a la variable que registra el tiempo de inicio
		long startTime = (long) request.getAttribute("startTime");

		// Almacenamiento en variable de la URL accedida
		String url = request.getRequestURL().toString();

		// Obtención del token de autenticación, para la posterior obtención de detalles
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// Inicialización de la variable username
		String username = "";

		// Si la sesión no es nula y hay datos de acceso, se asigna a la variable
		// username el nombre de dicha autenticación
		if (auth != null && auth.isAuthenticated()) {
			username = auth.getName();
		}

		// Persistencia de todos los datos creados en la base de datos
		logRepository.save(new com.chatbot.entity.Log(new Date(), auth.getDetails().toString(), username, url));

		// Log para saber en cuánto tiempo se accede a una url
		LOG.info("Url to: '" + url + "' in: '" + (System.currentTimeMillis() - startTime) + " ms'");
	}

}
