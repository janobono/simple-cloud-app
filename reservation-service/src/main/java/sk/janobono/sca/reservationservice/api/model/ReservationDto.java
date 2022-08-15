package sk.janobono.sca.reservationservice.api.model;

import java.time.LocalDate;

public record ReservationDto(
        Long id,
        Long roomId,
        Long customerId,
        LocalDate date
) {
}
