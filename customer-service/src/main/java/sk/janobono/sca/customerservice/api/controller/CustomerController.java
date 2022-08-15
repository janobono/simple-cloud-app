package sk.janobono.sca.customerservice.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.janobono.sca.customerservice.api.model.CustomerDataDto;
import sk.janobono.sca.customerservice.api.model.CustomerDto;
import sk.janobono.sca.customerservice.api.service.CustomerApiService;

import javax.validation.Valid;

@RestController
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerApiService customerApiService;

    public CustomerController(CustomerApiService customerApiService) {
        this.customerApiService = customerApiService;
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> getCustomers(Pageable pageable) {
        LOGGER.debug("getCustomers({})", pageable);
        return new ResponseEntity<>(customerApiService.getCustomers(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id) {
        LOGGER.debug("getCustomer({})", id);
        return new ResponseEntity<>(customerApiService.getCustomer(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDataDto customerDataDto) {
        LOGGER.debug("addCustomer({})", customerDataDto);
        return new ResponseEntity<>(customerApiService.addCustomer(customerDataDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> setCustomer(@PathVariable("id") Long id, @Valid @RequestBody CustomerDataDto customerDataDto) {
        LOGGER.debug("setCustomer({},{})", id, customerDataDto);
        return new ResponseEntity<>(customerApiService.setCustomer(id, customerDataDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        LOGGER.debug("deleteCustomer({})", id);
        customerApiService.deleteCustomer(id);
    }
}
