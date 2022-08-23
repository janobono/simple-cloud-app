package sk.janobono.sca.roomreservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import sk.janobono.sca.roomreservationservice.config.ConfigProperties;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "sk.janobono.sca.roomreservationservice")
@EnableConfigurationProperties(ConfigProperties.class)
public class RoomReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomReservationServiceApplication.class, args);
    }
}
