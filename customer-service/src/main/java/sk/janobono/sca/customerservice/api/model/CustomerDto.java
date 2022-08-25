package sk.janobono.sca.customerservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Customer")
public record CustomerDto(
        Long id,
        String firstName,
        String lastName,
        String emailAddress,
        String address,
        String country,
        String state,
        String phoneNumber
) {
}
