package endproject.autorize;


import endproject.repository.UserRepository;
import endproject.service.MyUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final MyUserService myUserService;

    public SecurityConfig(PasswordEncoder passwordEncoder, MyUserService myUserService) {
        this.passwordEncoder = passwordEncoder;
        this.myUserService = myUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable(); //wylaczamy zabezpieczenia przed np DDOS
        http.csrf().disable(); //wylaczamy zabezpieczenia przed np DDOS

        http
                .authorizeRequests() //wlaczenie filtrowania requestow z sieci
                .antMatchers("/login**", "/register**").permitAll() //wylaczenie filtru na te konkretne urle
                .and()
                .formLogin()
                .loginPage("/login") //ustawiamy endpoint do naszego kontrolera widoku gdzie mamy strone logowania
                .loginProcessingUrl("/signin") //miejsce gdzie przychodzi za[ytanie z formularza html
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((res, req, auth) -> { // rozszerzona wersja tego co ponizej
                    req.sendRedirect("/pricelist");
                })
              //  .successForwardUrl("/") //przekierowanie po udanym zalogowaniiu
                .failureForwardUrl("/login?error=wrong login or password!")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll(); //wylacznie filtrowania dla logout


    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().disable(); //wylaczamy zabezpieczenia przed np DDOS
//        http.csrf().disable(); //wylaczamy zabezpieczenia przed np DDOS
//
//        http
//                .authorizeRequests() //wlaczenie filtrowania requestow z sieci
//                .antMatchers("/").permitAll() //wylaczenie filtru na te konkretne urle
//                .and()
//                .formLogin()
//                .loginPage("/login") //ustawiamy endpoint do naszego kontrolera widoku gdzie mamy strone logowania
//                .loginProcessingUrl("/signin") //miejsce gdzie przychodzi za[ytanie z formularza html
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler((res, req, auth) -> { // rozszerzona wersja tego co ponizej
//                    req.sendRedirect("/pricelist");
//                })
//                //  .successForwardUrl("/") //przekierowanie po udanym zalogowaniiu
//                .failureForwardUrl("/login?error=wrong login or password!")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .permitAll(); //wylacznie filtrowania dla logout
//
//
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserService)
                .passwordEncoder(passwordEncoder);
    }
}