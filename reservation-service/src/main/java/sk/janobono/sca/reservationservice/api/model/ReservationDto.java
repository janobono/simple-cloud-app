package sk.janobono.sca.reservationservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "Reservation")
public record ReservationDto(
        Long id,
        Long roomId,
        Long customerId,
        LocalDate date
) {
}
