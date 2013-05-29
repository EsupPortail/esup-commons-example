package org.esupportail.example.web.config;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.primefaces.push.PushServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.util.Log4jConfigListener;

public class WebAppInitializer implements WebApplicationInitializer {

	private final String PACKAGE_TO_SCAN = "org.esupportail.example.web.config";
	private final String LOG4J_PATH = "classpath:/log4j.properties";

	@Override
	public void onStartup(ServletContext context) throws ServletException {

		context.setInitParameter("log4jConfigLocation", LOG4J_PATH);
		context.setInitParameter("com.sun.faces.allowTextChildren", "true");
		context.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
		context.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
		context.setInitParameter("javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE", "true");
		context.setInitParameter("org.apache.myfaces.ERROR_HANDLING", "true");
		context.setInitParameter("org.apache.myfaces.SERIALIZE_STATE_IN_SESSION", "false"); // for viewscope
		context.setInitParameter("primefaces.THEME", "bootstrap");

		AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
		root.scan(PACKAGE_TO_SCAN);
		
		context.addListener(new ContextLoaderListener(root));
		context.addListener(new RequestContextListener());
		context.addListener(new Log4jConfigListener());

		Dynamic facesServlet = context.addServlet("Faces Servlet",
				FacesServlet.class);
		facesServlet.setLoadOnStartup(1);
		facesServlet.addMapping("*.xhtml");	

		Dynamic pushServlet = context.addServlet("Push Servlet",
				PushServlet.class);
		pushServlet.setLoadOnStartup(2);
		pushServlet.addMapping("/primepush/*");

	}

}
