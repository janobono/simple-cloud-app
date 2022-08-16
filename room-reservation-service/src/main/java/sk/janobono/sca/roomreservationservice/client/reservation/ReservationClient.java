package sk.janobono.sca.roomreservationservice.client.reservation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient("reservationservice")
public interface ReservationClient {

    @GetMapping("/api/v1/reservations")
    Page<ReservationDto> getReservations(
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam Pageable pageable
    );

    @GetMapping("/api/v1/reservations/{id}")
    ReservationDto getReservation(@PathVariable("id") long id);
}
