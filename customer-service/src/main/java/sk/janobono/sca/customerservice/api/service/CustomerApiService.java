package sk.janobono.sca.customerservice.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.janobono.sca.customerservice.api.model.CustomerDataDto;
import sk.janobono.sca.customerservice.api.model.CustomerDto;
import sk.janobono.sca.customerservice.common.exception.ApplicationExceptionCode;
import sk.janobono.sca.customerservice.dal.domain.Customer;
import sk.janobono.sca.customerservice.dal.repository.CustomerRepository;

@Service
public class CustomerApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerApiService.class);

    private final CustomerRepository customerRepository;

    public CustomerApiService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<CustomerDto> getCustomers(Pageable pageable) {
        LOGGER.debug("getCustomers({})", pageable);
        return customerRepository.findAll(pageable).map(this::mapCustomer);
    }

    public CustomerDto getCustomer(Long id) {
        LOGGER.debug("getCustomer({})", id);
        return mapCustomer(customerRepository.findById(id).orElseThrow(
                () -> ApplicationExceptionCode.CUSTOMER_NOT_FOUND.exception(id)
        ));
    }

    @Transactional
    public CustomerDto addCustomer(CustomerDataDto customerDataDto) {
        LOGGER.debug("addCustomer({})", customerDataDto);
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customerDataDto.firstName());
        newCustomer.setLastName(customerDataDto.lastName());
        newCustomer.setEmailAddress(customerDataDto.emailAddress());
        newCustomer.setAddress(customerDataDto.address());
        newCustomer.setCountry(customerDataDto.country());
        newCustomer.setState(customerDataDto.state());
        newCustomer.setPhoneNumber(customerDataDto.phoneNumber());
        newCustomer = customerRepository.save(newCustomer);
        return mapCustomer(newCustomer);
    }

    @Transactional
    public CustomerDto setCustomer(Long id, CustomerDataDto customerDataDto) {
        LOGGER.debug("setCustomer({},{})", id, customerDataDto);
        Customer savedCustomer = customerRepository.findById(id).orElseThrow(
                () -> ApplicationExceptionCode.CUSTOMER_NOT_FOUND.exception(id)
        );
        savedCustomer.setFirstName(customerDataDto.firstName());
        savedCustomer.setLastName(customerDataDto.lastName());
        savedCustomer.setEmailAddress(customerDataDto.emailAddress());
        savedCustomer.setAddress(customerDataDto.address());
        savedCustomer.setCountry(customerDataDto.country());
        savedCustomer.setState(customerDataDto.state());
        savedCustomer.setPhoneNumber(customerDataDto.phoneNumber());
        savedCustomer = customerRepository.save(savedCustomer);
        return mapCustomer(savedCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        LOGGER.debug("deleteCustomer({})", id);
        customerRepository.deleteById(id);
    }

    private CustomerDto mapCustomer(Customer customer) {
        LOGGER.debug("mapCustomer({})", customer);
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmailAddress(),
                customer.getAddress(),
                customer.getCountry(),
                customer.getState(),
                customer.getPhoneNumber()
        );
    }
}
