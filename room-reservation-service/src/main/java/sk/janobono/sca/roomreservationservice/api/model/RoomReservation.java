package sk.janobono.sca.roomreservationservice.api.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomReservation {

    private Long roomId;

    private Long customerId;

    private String roomName;

    private String roomNumber;

    private String firstName;

    private String lastName;

    private LocalDate date;
}
