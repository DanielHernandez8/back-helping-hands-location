package com.helpinghandslocation.helpinghandslocation.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Value("${app.local-domain-front}")
    private String localDomainFront; // Cargar dominio permitido desde properties o YAML

    @Autowired
    private JwtRequestFilter jwtRequestFilter; // Filtro para autenticación JWT

    @Autowired
    private UserDetailsService userDetailsService; // Servicio de usuario personalizado

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; // Encoder para contraseñas

    // Configurar el AuthenticationManager para usar el servicio de usuarios y codificación
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    // Bean para AuthenticationManager (usado para autenticar usuarios manualmente si es necesario)
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManager.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManager.build();
    }

    // Configuración de seguridad HTTP
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Deshabilitar CSRF (recomendado solo si tienes control del front)
                .cors(withDefaults()) // Habilitar CORS con configuración personalizada
                .authorizeHttpRequests((requests) -> {
                    try {
                        // Rutas públicas sin autenticación
                        requests.requestMatchers(HttpMethod.GET, "/locations", "/tags", "/types")
                                .permitAll()
                                .requestMatchers("/auth/google", "auth/basic/register", "/swagger-ui/**", "auth/basic/login", "/v3/api-docs/**","/v2/api-docs/**","/webjars/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated(); // Cualquier otra ruta requiere autenticación
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                //.oauth2Login(withDefaults())  // Añadimos soporte para OAuth2 (login con Google)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Añadir filtro JWT antes del filtro estándar
        return http.build();
    }

    // Configuración de CORS para permitir peticiones desde el frontend
    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(localDomainFront)
                        .allowedMethods("POST", "PUT", "GET", "DELETE", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }
}
