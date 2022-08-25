package sk.janobono.sca.reservationservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema(name = "ReservationData")
public record ReservationDataDto(
        @NotNull Long roomId,
        @NotNull Long customerId,
        @NotNull LocalDate date
) {
}
