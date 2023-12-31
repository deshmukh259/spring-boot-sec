package com.security.springbootsec;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootSecApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecApplication.class, args);
	}


	@Bean
	public ServletWebServerFactory servletWebServerFactory(){
		TomcatServletWebServerFactory tomact =new TomcatServletWebServerFactory(){

			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint constraint= new SecurityConstraint();
				constraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection =new SecurityCollection();
				collection.addPattern("/*");
				constraint.addCollection(collection);
				context.addConstraint(constraint);
			}
		};
		tomact.addAdditionalTomcatConnectors(redirectConnector());
		return tomact;
	}

	private Connector redirectConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setRedirectPort(8443);
		return connector;
	}
}
