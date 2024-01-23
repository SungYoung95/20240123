package com.springproj.emotionshare.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	AuthenticationFailureHandler authenticationFailureHandler;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(); // 패스워드 암호화
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((auth) ->auth //람다식으로 작성
					.requestMatchers("/login","loginProc").permitAll()
					.requestMatchers("/admin").hasRole("ADMIN")
					.requestMatchers("/mainpage","/mypage","checkpwd").hasAnyRole("ADMIN","USER")
					.anyRequest().permitAll()
					)
			.formLogin(login ->login
		        .loginPage("/login")
		        .loginProcessingUrl("/j_spring_security_check")
		        .failureHandler(authenticationFailureHandler)
		    	.usernameParameter("username")
				.passwordParameter("password")
		        .defaultSuccessUrl("/mainpage")
		        .permitAll()
		        )
		        .logout(logout ->logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login")
						.permitAll()
						
				)
				.rememberMe(Customizer.withDefaults());

		
		http //중복로그인 설정
			.sessionManagement((auth)-> auth
					.maximumSessions(1)
					.maxSessionsPreventsLogin(true)); //1중복 초과시 새로운 로그인 차단
		http
				.sessionManagement((auth)-> auth
					.sessionFixation().changeSessionId());//보안 세션 보호
		
		
		
		
		
		return http.build();
	}

}
