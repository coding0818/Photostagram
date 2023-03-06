package kr.co.photostagram.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private SecurityUserService service;

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 인가(접근권한) 설정
		http.authorizeHttpRequests().antMatchers("/", "/index").permitAll(); // 모든 자원에 대해서 모든 사용자 접근 허용

		// 사이트 위변조 요청 방지
		http.csrf().disable();


		// 로그인 설정
		http.formLogin()
		.loginPage("/member/login")
		.defaultSuccessUrl("/index")
		.failureUrl("/member/login?success=111")
		.usernameParameter("username")
		.passwordParameter("password");

		// 로그아웃 설정
		http.logout()
		.invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")).deleteCookies("JSESSIONID", "autoUid")
		.logoutSuccessUrl("/member/login?success=200");
		
		// 사용자 인증 처리 컴포넌트 서비스 등록
		http.userDetailsService(service);

		/*
		// 로그인 유지
		http.rememberMe()
		.rememberMeParameter("rememberMe")		// html checkbox name에 해당하는 값 (default: remember-me)
		.tokenValiditySeconds(86400*30)			// 한 달 (default: 14일)
		.alwaysRemember(false)					// 체크 박스 항상 실행 -> (default: false)
		.userDetailsService(service)			// 사용자 계정 조회 처리 설정 api
		.tokenRepository(tokenRepository());
		*/

		return http.build();
	}
	
	@Bean
    public PasswordEncoder encoder () {
        return new BCryptPasswordEncoder();
    }


}
