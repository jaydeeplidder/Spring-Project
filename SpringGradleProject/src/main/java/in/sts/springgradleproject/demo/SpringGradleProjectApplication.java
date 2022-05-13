package in.sts.springgradleproject.demo;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "in.sts.springgradleproject")
@EnableAutoConfiguration
public class SpringGradleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGradleProjectApplication.class, args);
	}

}
