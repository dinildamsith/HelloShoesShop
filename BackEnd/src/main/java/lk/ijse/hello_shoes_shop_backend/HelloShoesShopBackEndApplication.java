package lk.ijse.hello_shoes_shop_backend;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories
public class HelloShoesShopBackEndApplication {


    @Bean
    ModelMapper mapper(){
        return new ModelMapper();
    }


    public static void main(String[] args) {
        SpringApplication.run(HelloShoesShopBackEndApplication.class, args);
    }

}
