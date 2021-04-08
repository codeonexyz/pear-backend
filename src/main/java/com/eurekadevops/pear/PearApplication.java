package com.eurekadevops.pear;

import com.eurekadevops.pear.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class PearApplication {

    public static void main(String[] args) {
        SpringApplication.run(PearApplication.class, args);
    }

}
