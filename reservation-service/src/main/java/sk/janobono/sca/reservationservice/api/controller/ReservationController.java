package sk.janobono.sca.reservationservice.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.janobono.sca.reservationservice.api.model.ReservationDataDto;
import sk.janobono.sca.reservationservice.api.model.ReservationDto;
import sk.janobono.sca.reservationservice.api.service.ReservationApiService;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    private final ReservationApiService reservationApiService;

    public ReservationController(ReservationApiService reservationApiService) {
        this.reservationApiService = reservationApiService;
    }

    @GetMapping
    public ResponseEntity<Page<ReservationDto>> getReservations(
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Pageable pageable) {
        LOGGER.debug("getReservations({},{})", date, pageable);
        return new ResponseEntity<>(reservationApiService.getReservations(date, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable("id") long id) {
        LOGGER.debug("getReservation({})", id);
        return new ResponseEntity<>(reservationApiService.getReservation(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> addReservation(@Valid @RequestBody ReservationDataDto reservationDataDto) {
        LOGGER.debug("addReservation({})", reservationDataDto);
        return new ResponseEntity<>(reservationApiService.addReservation(reservationDataDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> setReservation(@PathVariable("id") Long id,
                                                         @Valid @RequestBody ReservationDataDto reservationDataDto) {
        LOGGER.debug("setReservation({},{})", id, reservationDataDto);
        return new ResponseEntity<>(reservationApiService.setReservation(id, reservationDataDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") Long id) {
        LOGGER.debug("deleteReservation({})", id);
        reservationApiService.deleteReservation(id);
    }
}
