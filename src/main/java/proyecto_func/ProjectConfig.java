package proyecto_func;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/login").setViewName("admin/login");
        registry.addViewController("/admin/admin").setViewName("admin/admin");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/errores/**", "/js/**", "/webjars/**", "/informacion/quienesSomos", "/img/**", "css/**",
                         "/donacion/vistaUsuario", "/donacion/guardarDonacion","/evento/vistaEvento", "/mensaje/contactanos", "/adopcion/vistaAdopcion", "/adopcion/vistaAdopcion_1/{idMascota}","/adopcion/guardar2","/mensaje/guardar")
                .permitAll()
                        
                .requestMatchers("/admin/admin", "/adopcion/listado", "/adopcion/modificar/{idAdopcion}", "/adopcion/ver/{idAdopcion}", "/adopcion/guardar","/adopcion/consulta","/adopcion/eliminar/{idAdopcion}",
                        "/donacion/listado", "/donacion/ver/{idDonacion}", "/donacion/consulta","/evento/listado", "/evento/modificar/{idEvento}", "/evento/ver/{idEvento}", "/evento/consulta", "/evento/guardar","/evento/eliminar/{idEvento}",
                        "/mascota/listado", "/mascota/modificar/{idMascota}", "/mascota/ver/{idMascota}","/mascota/eliminar/{idMascota}","/mascota/consulta", "/mascota/guardar","/mensaje/listado", "/mensaje/ver/{idMensaje}","/mensaje/consulta","/mensaje/eliminar/{idMensaje}",
                        "/patrocinador/listado", "/patrocinador/modificar/{idPatrocinador}", "/patrocinador/ver/{idPatrocinador}","/patrocinador/eliminar/{idPatrocinador}", "/patrocinador/consulta","/usuario/listado", "/patrocinador/guardar","/usuario/modificar/{idUsuario}",
                        "/usuario/ver/{idUsuario}", "/usuario/eliminar/{idUsuario}", "usuario/consulta", "/usuario/guardar","/usuario/restablecerPass/{idUsuario}", "/usuario/nuevo", "/usuario/cambiar"
                ).hasAnyRole("ADMIN", "USER")
                )
                
                
                
                .formLogin((form) -> form
                .loginPage("/admin/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();

    }
        @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {

        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }
}
