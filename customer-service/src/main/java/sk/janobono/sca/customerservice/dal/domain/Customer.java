package sk.janobono.sca.customerservice.dal.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "address")
    private String address;
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "phone_number")
    private String phoneNumber;
}
