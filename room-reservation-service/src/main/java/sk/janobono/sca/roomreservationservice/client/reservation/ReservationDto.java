package sk.janobono.sca.roomreservationservice.client.reservation;

import java.time.LocalDate;

public record ReservationDto(
        Long id,
        Long roomId,
        Long customerId,
        LocalDate date
) {
}
