package sk.janobono.sca.roomreservationservice.client.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("customerservice")
public interface CustomerClient {

    @GetMapping("/api/v1/customers")
    Page<CustomerDto> getCustomers(@RequestParam Pageable pageable);

    @GetMapping("/api/v1/customers/{id}")
    CustomerDto getCustomer(@PathVariable("id") long id);
}
