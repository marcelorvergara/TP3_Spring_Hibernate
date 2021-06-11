package br.edu.infnet;

import br.edu.infnet.model.Role;
import br.edu.infnet.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(BasicApplication.class);

//    @Bean
//    CommandLineRunner initDatabase(RoleRepository roleRepository) {
//        return args -> {
//            log.info("Role Admin " + roleRepository.save(new Role("Admin")));
//            log.info("Role User " + roleRepository.save(new Role("User")));
//        };
//    }
}
