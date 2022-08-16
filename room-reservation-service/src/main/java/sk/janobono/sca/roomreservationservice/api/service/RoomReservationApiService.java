package sk.janobono.sca.roomreservationservice.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.janobono.sca.roomreservationservice.api.model.RoomReservation;
import sk.janobono.sca.roomreservationservice.client.customer.CustomerClient;
import sk.janobono.sca.roomreservationservice.client.customer.CustomerDto;
import sk.janobono.sca.roomreservationservice.client.reservation.ReservationClient;
import sk.janobono.sca.roomreservationservice.client.reservation.ReservationDto;
import sk.janobono.sca.roomreservationservice.client.room.RoomClient;
import sk.janobono.sca.roomreservationservice.client.room.RoomDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomReservationApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomReservationApiService.class);

    private CustomerClient customerClient;
    private ReservationClient reservationClient;
    private RoomClient roomClient;

    @Autowired
    public void setCustomerClient(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @Autowired
    public void setReservationClient(ReservationClient reservationClient) {
        this.reservationClient = reservationClient;
    }

    @Autowired
    public void setRoomClient(RoomClient roomClient) {
        this.roomClient = roomClient;
    }

    public List<RoomReservation> getRoomReservations(LocalDate date) {
        LOGGER.debug("getRoomReservations({})", date);

        List<RoomDto> rooms = roomClient.getRooms(null).getContent();
        Map<Long, RoomReservation> roomReservations = new HashMap<>();
        rooms.forEach(room -> {
                    RoomReservation roomReservation = new RoomReservation();
                    roomReservation.setRoomNumber(room.roomNumber());
                    roomReservation.setRoomName(room.name());
                    roomReservation.setRoomId(room.id());
                    roomReservations.put(room.id(), roomReservation);
                }
        );
        List<ReservationDto> reservations = this.reservationClient.getReservations(date, null).getContent();
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.roomId());
            roomReservation.setDate(date);
            CustomerDto customer = customerClient.getCustomer(reservation.customerId());
            roomReservation.setCustomerId(customer.id());
            roomReservation.setFirstName(customer.firstName());
            roomReservation.setLastName(customer.lastName());
        });
        return new ArrayList<>(roomReservations.values());
    }
}
