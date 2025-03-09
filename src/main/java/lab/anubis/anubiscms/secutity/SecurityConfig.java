package lab.anubis.anubiscms.secutity;

import lab.anubis.anubiscms.features.role.model.Role;
import lab.anubis.anubiscms.features.role.model.AppRole;
import lab.anubis.anubiscms.features.role.repository.RoleRepository;
import lab.anubis.anubiscms.features.user.model.User;
import lab.anubis.anubiscms.features.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.time.LocalDate;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {

    private final AuthEntryPointJwt unauthorizedHandler;

    public SecurityConfig(AuthEntryPointJwt unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf ->csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers("/auth/public/**")
        );
        http.authorizeHttpRequests((requests)
                -> requests
                        .requestMatchers("/api/admin/**").hasRole("SUPER_ADMIN")
                        .requestMatchers("/api/csrf-token").permitAll()
                        .requestMatchers("/auth/public/**").permitAll()
                        .anyRequest().authenticated());
        http.exceptionHandling(exception ->
                exception.authenticationEntryPoint(unauthorizedHandler));
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(withDefaults());
        http.formLogin(withDefaults());
//        http.sessionManagement(session ->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            Role superAdmin = roleRepository.findByRoleName(AppRole.ROLE_SUPER_ADMIN)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_SUPER_ADMIN)));

            Role clientAdmin = roleRepository.findByRoleName(AppRole.ROLE_CLIENT_ADMIN)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_CLIENT_ADMIN)));

            Role clientEditor = roleRepository.findByRoleName(AppRole.ROLE_CLIENT_EDITOR)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_CLIENT_EDITOR)));

            Role clientUser = roleRepository.findByRoleName(AppRole.ROLE_CLIENT_USER)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_CLIENT_USER)));

            if (!userRepository.existsByUserName("super_admin")) {
                User admin = new User("super_admin", "admin@example.com", passwordEncoder().encode("adminPass"));
                admin.setAccountNonLocked(true);
                admin.setAccountNonExpired(true);
                admin.setCredentialsNonExpired(true);
                admin.setEnabled(true);
                admin.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                admin.setAccountExpiryDate(LocalDate.now().plusYears(1));
                admin.setTwoFactorEnabled(false);
                admin.setSignUpMethod("email");
                admin.setRole(superAdmin);
                userRepository.save(admin);
            }
            if (!userRepository.existsByUserName("admin_user")) {
                User user1 = new User("admin_user", "admin_user@example.com", passwordEncoder().encode("password1"));
                user1.setAccountNonLocked(false);
                user1.setAccountNonExpired(true);
                user1.setCredentialsNonExpired(true);
                user1.setEnabled(true);
                user1.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                user1.setAccountExpiryDate(LocalDate.now().plusYears(1));
                user1.setTwoFactorEnabled(false);
                user1.setSignUpMethod("email");
                user1.setRole(clientAdmin);
                userRepository.save(user1);
            }
            if (!userRepository.existsByUserName("client_editor")) {
                User admin = new User("client_editor", "client_editor@example.com", passwordEncoder().encode("password2"));
                admin.setAccountNonLocked(true);
                admin.setAccountNonExpired(true);
                admin.setCredentialsNonExpired(true);
                admin.setEnabled(true);
                admin.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                admin.setAccountExpiryDate(LocalDate.now().plusYears(1));
                admin.setTwoFactorEnabled(false);
                admin.setSignUpMethod("email");
                admin.setRole(clientEditor);
                userRepository.save(admin);
            }
            if (!userRepository.existsByUserName("client_user")) {
                User admin = new User("client_user", "client_user@example.com", passwordEncoder().encode("password3"));
                admin.setAccountNonLocked(true);
                admin.setAccountNonExpired(true);
                admin.setCredentialsNonExpired(true);
                admin.setEnabled(true);
                admin.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                admin.setAccountExpiryDate(LocalDate.now().plusYears(1));
                admin.setTwoFactorEnabled(false);
                admin.setSignUpMethod("email");
                admin.setRole(clientUser);
                userRepository.save(admin);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        if (!manager.userExists("user1")){
            manager.createUser(
                    User.withUsername("user1")
                            .password("{noop}password1")
                            .roles("USER")
                            .build()
            );
        }
        if (!manager.userExists("admin")){
            manager.createUser(
                    User.withUsername("admin")
                            .password("{noop}adminpass")
                            .roles("ADMIN")
                            .build()
            );
        }
        return manager;
    }*/


}
