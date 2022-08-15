package sk.janobono.sca.customerservice.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.janobono.sca.customerservice.dal.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
