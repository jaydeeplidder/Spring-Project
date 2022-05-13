package in.sts.springgradleproject;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		auth.inMemoryAuthentication()
		.withUser("user")
		.password(encoder.encode("user123@"))
		.roles("USER")
		.and()
		.withUser("admin1")
		.password(encoder.encode("admin1234@"))
		.roles("USER","ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
		
		//Disable CSRF
		http = http.csrf().disable();
		
		//Disable default session base cookie basic authentication
		http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/users").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/users/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")
//		.antMatchers(HttpMethod.GET,"/users").permitAll()
		.antMatchers(HttpMethod.GET,"/users/**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}

}
