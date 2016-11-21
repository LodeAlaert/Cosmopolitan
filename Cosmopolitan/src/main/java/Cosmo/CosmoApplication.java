package Cosmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import test.CosmoApplicationTest;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"Controllers","Repositories"})
public class CosmoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CosmoApplication.class, args);
	}
}
