package sk.janobono.sca.reservationservice.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.janobono.sca.reservationservice.api.model.ReservationDataDto;
import sk.janobono.sca.reservationservice.api.model.ReservationDto;
import sk.janobono.sca.reservationservice.common.exception.ApplicationExceptionCode;
import sk.janobono.sca.reservationservice.dal.domain.Reservation;
import sk.janobono.sca.reservationservice.dal.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class ReservationApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationApiService.class);

    private final ReservationRepository reservationRepository;

    public ReservationApiService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Page<ReservationDto> getReservations(LocalDate date, Pageable pageable) {
        LOGGER.debug("getReservations({})", pageable);
        if (Objects.isNull(date)) {
            return reservationRepository.findAll(pageable).map(this::mapReservation);
        } else {
            return reservationRepository.findAllByDate(date, pageable).map(this::mapReservation);
        }
    }

    public ReservationDto getReservation(Long id) {
        LOGGER.debug("getReservation({})", id);
        return mapReservation(reservationRepository.findById(id).orElseThrow(
                () -> ApplicationExceptionCode.RESERVATION_NOT_FOUND.exception(id)
        ));
    }

    @Transactional
    public ReservationDto addReservation(ReservationDataDto reservationDataDto) {
        LOGGER.debug("addReservation({})", reservationDataDto);
        Reservation newReservation = new Reservation();
        newReservation.setRoomId(reservationDataDto.roomId());
        newReservation.setCustomerId(reservationDataDto.customerId());
        newReservation.setDate(reservationDataDto.date());
        newReservation = reservationRepository.save(newReservation);
        return mapReservation(newReservation);
    }

    @Transactional
    public ReservationDto setReservation(Long id, ReservationDataDto reservationDataDto) {
        LOGGER.debug("setReservation({},{})", id, reservationDataDto);
        Reservation savedReservation = reservationRepository.findById(id).orElseThrow(
                () -> ApplicationExceptionCode.RESERVATION_NOT_FOUND.exception(id)
        );
        savedReservation.setRoomId(reservationDataDto.roomId());
        savedReservation.setCustomerId(reservationDataDto.customerId());
        savedReservation.setDate(reservationDataDto.date());
        savedReservation = reservationRepository.save(savedReservation);
        return mapReservation(savedReservation);
    }

    @Transactional
    public void deleteReservation(Long id) {
        LOGGER.debug("deleteReservation({})", id);
        reservationRepository.deleteById(id);
    }

    private ReservationDto mapReservation(Reservation reservation) {
        LOGGER.debug("mapReservation({})", reservation);
        return new ReservationDto(
                reservation.getId(),
                reservation.getRoomId(),
                reservation.getCustomerId(),
                reservation.getDate()
        );
    }
}
