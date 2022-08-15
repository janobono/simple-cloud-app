package sk.janobono.sca.reservationservice.api.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import sk.janobono.sca.reservationservice.BaseIntegrationTest;
import sk.janobono.sca.reservationservice.api.model.ReservationDataDto;
import sk.janobono.sca.reservationservice.api.model.ReservationDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ReservationControllerIT extends BaseIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationControllerIT.class);

    @Test
    public void fullControllerTest() throws Exception {
        Page<ReservationDto> page = getCustomers(1, 50);
        assertThat(page.getTotalElements()).isEqualTo(1L);
        Map<Long, ReservationDto> reservationMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            ReservationDto newReservation = addReservation(i);
            reservationMap.put(newReservation.id(), newReservation);
        }
        page = getCustomers(0, 25);
        assertThat(page.getTotalElements()).isEqualTo(11L);
        for (ReservationDto reservation : reservationMap.values()) {
            assertThat(getReservation(reservation.id())).usingRecursiveComparison().isEqualTo(reservationMap.get(reservation.id()));
        }
    }

    private Page<ReservationDto> getCustomers(Integer page, Integer pageSize) throws Exception {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page.toString());
        params.add("size", pageSize.toString());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/").params(params)).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        Page<ReservationDto> result = mapPagedResponse(mvcResult.getResponse().getContentAsString(), ReservationDto.class);
        LOGGER.debug("{}", result);
        return result;
    }

    private ReservationDto getReservation(Long id) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/{id}", id)).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        ReservationDto result = mapFromJson(mvcResult.getResponse().getContentAsString(), ReservationDto.class);
        LOGGER.debug("{}", result);
        return result;
    }

    private ReservationDto addReservation(int index) throws Exception {
        ReservationDataDto reservationDataDto = new ReservationDataDto(
                (long) index,
                (long) index,
                LocalDate.now()
        );
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(reservationDataDto))).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        return mapFromJson(mvcResult.getResponse().getContentAsString(), ReservationDto.class);
    }
}
