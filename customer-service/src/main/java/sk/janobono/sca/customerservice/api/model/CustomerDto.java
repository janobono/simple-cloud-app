package sk.janobono.sca.customerservice.api.model;

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
