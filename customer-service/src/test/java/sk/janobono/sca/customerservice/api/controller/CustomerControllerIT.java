package sk.janobono.sca.customerservice.api.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import sk.janobono.sca.customerservice.BaseIntegrationTest;
import sk.janobono.sca.customerservice.api.model.CustomerDataDto;
import sk.janobono.sca.customerservice.api.model.CustomerDto;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerControllerIT extends BaseIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerControllerIT.class);

    @Test
    public void fullControllerTest() throws Exception {
        Page<CustomerDto> page = getCustomers(1, 50);
        assertThat(page.getTotalElements()).isEqualTo(200L);
        Map<Long, CustomerDto> customerMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            CustomerDto newCustomer = addCustomer(i);
            customerMap.put(newCustomer.id(), newCustomer);
        }
        page = getCustomers(4, 25);
        assertThat(page.getTotalElements()).isEqualTo(210L);
        for (CustomerDto customer : customerMap.values()) {
            assertThat(getCustomer(customer.id())).usingRecursiveComparison().isEqualTo(customerMap.get(customer.id()));
        }
    }

    private Page<CustomerDto> getCustomers(Integer page, Integer pageSize) throws Exception {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page.toString());
        params.add("size", pageSize.toString());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/").params(params)).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        Page<CustomerDto> result = mapPagedResponse(mvcResult.getResponse().getContentAsString(), CustomerDto.class);
        LOGGER.debug("{}", result);
        return result;
    }

    private CustomerDto getCustomer(Long id) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/{id}", id)).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        CustomerDto result = mapFromJson(mvcResult.getResponse().getContentAsString(), CustomerDto.class);
        LOGGER.debug("{}", result);
        return result;
    }

    private CustomerDto addCustomer(int index) throws Exception {
        CustomerDataDto customerDataDto = new CustomerDataDto(
                "firstName" + index,
                "lastName" + index,
                "email" + index + "@domain.lt",
                "address" + index,
                "country" + index,
                "EU" + index,
                "+421 1111 1111" + index
        );
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(customerDataDto))).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        return mapFromJson(mvcResult.getResponse().getContentAsString(), CustomerDto.class);
    }
}
