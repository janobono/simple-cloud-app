package sk.janobono.sca.roomreservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "sk.janobono.sca.roomreservationservice")
@ConfigurationPropertiesScan(basePackages = "sk.janobono.sca.roomreservationservice")
public class RoomReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomReservationServiceApplication.class, args);
    }
}
