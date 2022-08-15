package sk.janobono.sca.reservationservice.dal.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_id")
    private Long roomId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "res_date")
    private LocalDate date;
}
