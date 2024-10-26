package fr.lequipedechoc.hackathon_api.cross_cutting.security;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import fr.lequipedechoc.hackathon_api.cross_cutting.constants.Endpoint;
import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * Main security configuration of application
 *
 * @author T.NGUYEN
 * @date 28/03/2024
 */

/*
 * The @Configuration annotation in Spring is used to denote a class that
 * declares one or more @Bean methods and may be processed by the Spring IoC
 * container to generate bean definitions and service requests for those beans
 * at runtime.
 * Nota : The @Bean annotation on a method in Spring indicates that the method
 * is a factory method for a Spring bean. When the application context loads, it
 * will call the method and register the returned object as a bean in the
 * context.
 */
@Configuration /*
                * The class declares one or more @Bean methods and may be processed by the
                * Spring IoC container to generate bean definitions
                */
@EnableMethodSecurity /*
                       * Indicates that part of the configuration is contained in methods elsewhere in
                       * the code
                       */
@EnableWebSecurity /* Activation of Security */
public class SecurityConfig {

    /* DEPENDENCIES INJECTION */
    /* ============================================================ */

    /*
     * BCryptPasswordEncoder is a class provided by Spring Security that implements
     * the PasswordEncoder
     * interface. It uses the BCrypt strong hashing function to hash passwords,
     * making them secure
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /* Make validation on token(jwt) in the HTTP header request */
    private final JwtFilter jwtFilter;

    /*
     * UserDetailsService is a core interface in Spring Security used to retrieve
     * user authentication
     * and authorization information. It has one method named loadUserByUsername(),
     * which can be
     * overridden to customize the process of finding the user.
     * When we replace the default implementation of UserDetailsService, we must
     * also specify a
     * PasswordEncoder.
     */
    private final UserDetailsService userDetailsService;

    /* For Swagger */
    private final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "swagger-ui.html"
    };

    public SecurityConfig(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            JwtFilter jwtFilter,
            UserDetailsService userDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    // Interface à implémenter par les classes (généralement les gestionnaires de
    // requêtes HTTP) qui fournit une instance de CorsConfiguration en fonction de
    // la requête fournie.
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Nous appliquons ici cette configuration à toutes les routes de l'application
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    

    /* SECURITY FILTERS CHAIN */
    /* ============================================================ */

    /**
     * Create security filter chain of application
     *
     * @param httpSecurity item allows configuring web based security for specific
     *                     http requests. By default it will be applied to all
     *                     requests, but can be
     *                     restricted using requestMatcher or other similar methods.
     *                     <p>
     * @return the security filter chain
     *         <p>
     * @author T.NGUYEN
     * @date 28/03/2024
     */
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                 .cors(c -> c.configurationSource(corsConfigurationSource()))
				.exceptionHandling(customizer -> customizer
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll())
             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /* AUTHENTIFICATION MANAGER */
    /* ============================================================ */

    /**
     * Create authentification manager
     * The Spring AuthenticationManager is a core component of
     * Spring Security responsible for validating user credentials.
     * It is typically implemented by ProviderManager, which delegates to a chain of
     * AuthenticationProvider instances.
     *
     * @param authenticationConfiguration who define the authentification process
     *                                    for you application
     * @return the authentification manager of application
     * @throws Exception when anomalie in identification occurs
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /* AUTHENTIFICAION PROVIDER */
    /* ============================================================ */

    /**
     * Create Spring authentification provider, who will be used by
     * AuthenticationManager,
     * using UserDetailsService and passwordEncoder
     * <p>
     * AuthenticationProvider is a key component of Spring Security’s
     * authentication and authorization. It is responsible for
     * authenticating a user’s credentials and returning an Authentication object
     * that represents the authenticated user.
     *
     * @return the authentification provider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        /*
         * The DaoAuthenticationProvider is a AuthenticationProvider implementation in
         * Spring Security that uses a UserDetailsService and PasswordEncoder to
         * authenticate a username and password. It is responsible for loading user
         * details from the UserDetailsService and comparing the provided password with
         * the encoded password stored in the UserDetails object.
         */
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

}
