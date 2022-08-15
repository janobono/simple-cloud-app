package sk.janobono.sca.reservationservice.dal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sk.janobono.sca.reservationservice.dal.domain.Reservation;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAllByDate(LocalDate date, Pageable pageable);
}
