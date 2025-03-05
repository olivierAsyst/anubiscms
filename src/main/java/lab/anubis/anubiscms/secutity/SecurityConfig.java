package lab.anubis.anubiscms.secutity;

import lab.anubis.anubiscms.features.role.model.Role;
import lab.anubis.anubiscms.features.role.model.AppRole;
import lab.anubis.anubiscms.features.role.repository.RoleRepository;
import lab.anubis.anubiscms.features.user.model.User;
import lab.anubis.anubiscms.features.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.time.LocalDate;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests)
                -> requests
//                        .requestMatchers("/contact").permitAll()
//                        .requestMatchers("/public/**").permitAll()
//                        .requestMatchers("/admin").denyAll()
                        .anyRequest().authenticated());
//        http.formLogin(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(withDefaults());
//        http.sessionManagement(session ->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            Role superAdmin = roleRepository.findByRoleName(AppRole.SUPER_ADMIN)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.SUPER_ADMIN)));

            Role clientAdmin = roleRepository.findByRoleName(AppRole.CLIENT_ADMIN)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.CLIENT_ADMIN)));

            Role clientEditor = roleRepository.findByRoleName(AppRole.CLIENT_EDITOR)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.CLIENT_EDITOR)));

            Role clientUser = roleRepository.findByRoleName(AppRole.CLIENT_USER)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.CLIENT_USER)));

            if (!userRepository.existsByUserName("super_admin")) {
                User admin = new User("super_admin", "admin@example.com", "{noop}adminPass");
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
                User user1 = new User("admin_user", "admin_user@example.com", "{noop}password1");
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
                User admin = new User("client_editor", "client_editor@example.com", "{noop}password2");
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
                User admin = new User("client_user", "client_user@example.com", "{noop}password3");
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
