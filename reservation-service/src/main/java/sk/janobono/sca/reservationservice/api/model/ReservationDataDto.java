package sk.janobono.sca.reservationservice.api.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReservationDataDto(
        @NotNull Long roomId,
        @NotNull Long customerId,
        @NotNull LocalDate date
) {
}
