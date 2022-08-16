package sk.janobono.sca.roomreservationservice.client.customer;

public record CustomerDto(
        Long id,
        String firstName,
        String lastName,
        String emailAddress,
        String address,
        String country,
        String state,
        String phoneNumber) {
}
