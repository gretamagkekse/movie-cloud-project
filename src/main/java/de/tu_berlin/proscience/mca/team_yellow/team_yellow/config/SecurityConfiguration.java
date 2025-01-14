package de.tu_berlin.proscience.mca.team_yellow.team_yellow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    //TODO: need to be adjusted
    private static final String[] PATH_WITH_FREE_ACCESS = {
            "/api/movies/popular", "/api/movie/**", "/api/movies/search",
            "/api/comments/**", "/api/tmdb/apikey",
            "/","index.html", "movie-details.html**","profile.html", "login.html", "favorites.html",
            "/js/**", "/webjars/**","/styles.css",
            "/swagger-ui.html", "/swagger-ui/**", "/v3/**",
            "/favicon.ico", "/logo.png", "/bild_name.png","/Hintergrund.jpeg"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(httpRequest ->
                        httpRequest
                                .requestMatchers(HttpMethod.POST, "/api/comments/**").authenticated()
                                .requestMatchers("/me").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/comments/**").authenticated()
                                .requestMatchers("/api/favorites/**").authenticated()
                                .requestMatchers(PATH_WITH_FREE_ACCESS).permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
