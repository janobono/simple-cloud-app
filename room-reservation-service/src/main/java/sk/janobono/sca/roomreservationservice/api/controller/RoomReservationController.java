package sk.janobono.sca.roomreservationservice.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.janobono.sca.roomreservationservice.api.model.RoomReservation;
import sk.janobono.sca.roomreservationservice.api.service.RoomReservationApiService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
public class RoomReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomReservationController.class);

    private final RoomReservationApiService roomReservationApiService;

    public RoomReservationController(RoomReservationApiService roomReservationApiService) {
        this.roomReservationApiService = roomReservationApiService;
    }

    @GetMapping
    public ResponseEntity<List<RoomReservation>> getRoomReservations(
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        LOGGER.debug("getRoomReservations({})", date);
        return new ResponseEntity<>(roomReservationApiService.getRoomReservations(date), HttpStatus.OK);
    }
}
