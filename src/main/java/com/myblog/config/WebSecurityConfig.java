//package com.myblog.config;
//
//import com.myblog.service.UserDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//
//    private final UserDetailService userDetailService;
//
//    //1번. 정적 리소스(HTML, 이미지)에서 스프링시큐리티 기능(인증, 인가) 비활성화
//    @Bean
//    public WebSecurityCustomizer configure(){
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers(new AntPathRequestMatcher("/static/**"));
//        /*  requestMatchers() : 특정 요청과 일치하는 url에 대한 액세스 설정*/
//    }
//
//
//
//    // 2번. 특정 Http 요청에 대한 웹 기반 보안(인증&인가, 로그인&로그아웃) 구성
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        return http
//           //3번. 인증, 인가 설정 (특정 경로에 대한)
//                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers(
//                                        new AntPathRequestMatcher("/login"),
//                                        new AntPathRequestMatcher("/signup"),
//                                        new AntPathRequestMatcher("/user")
//                                ).permitAll()
//                                .anyRequest().authenticated())
//           //4번. 폼 기반 로그인 설정
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/articles"))
//           //5번. 로그아웃 설정
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login")
//                        .invalidateHttpSession(true))
//                 // 6번. csrf 비활성화 (실습 편하게 하려고!)
//                .csrf(AbstractHttpConfigurer::disable)
//                .build();
//    }
//
//
//    /* 7번. 인증 관리자 관련 설정
//         사용자 정보 가져올 서비스(UserDetailService) 재정의, 인증방법(JDBC 기반) 등을 설정  */
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder
//            bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception{
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        //8번. 사용자 정보 서비스 설정
//        authProvider.setUserDetailsService(userDetailService);
//        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
//        return new ProviderManager(authProvider);
//    }
//
//
//    //9번. 패스워드 인코더로 사용할 빈 등록
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//}
