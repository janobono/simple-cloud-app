package sk.janobono.sca.roomreservationservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties("app")
@ConstructorBinding
@Validated
public record ConfigProperties(
        @NotBlank String customerServiceUrl,
        @NotBlank String reservationServiceUrl,
        @NotBlank String roomServiceUrl
) {
}
