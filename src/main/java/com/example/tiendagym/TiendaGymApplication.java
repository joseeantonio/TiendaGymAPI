package com.example.tiendagym;

import com.example.tiendagym.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class TiendaGymApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaGymApplication.class, args);
    }

}
