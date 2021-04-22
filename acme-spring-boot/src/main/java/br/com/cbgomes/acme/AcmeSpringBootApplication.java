package br.com.cbgomes.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages= {"br.com.cbgomes.acme.client.repository"})
@EntityScan(value = {"br.com.cbgomes.acme.client.domain"})
@ComponentScan(value = {"br.com.cbgomes.acme.client.resource", "br.com.cbgomes.acme.client.service", "br.com.cbgomes.acme.client.resource.exception", "br.com.cbgomes.acme.config", "br.com.cbgomes.acme"})
public class AcmeSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmeSpringBootApplication.class, args);
	}

}
