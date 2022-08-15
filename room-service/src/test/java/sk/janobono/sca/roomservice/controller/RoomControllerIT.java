package sk.janobono.sca.roomservice.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import sk.janobono.sca.roomservice.BaseIntegrationTest;
import sk.janobono.sca.roomservice.model.RoomDataDto;
import sk.janobono.sca.roomservice.model.RoomDto;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RoomControllerIT extends BaseIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomControllerIT.class);

    @Test
    public void fullControllerTest() throws Exception {
        Page<RoomDto> page = getRooms(1, 50);
        assertThat(page.getTotalElements()).isEqualTo(28L);
        Map<Long, RoomDto> roomMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            RoomDto newRoom = addRoom(i);
            roomMap.put(newRoom.id(), newRoom);
        }
        page = getRooms(4, 25);
        assertThat(page.getTotalElements()).isEqualTo(38L);
        for (RoomDto room : roomMap.values()) {
            assertThat(getRoom(room.id())).usingRecursiveComparison().isEqualTo(roomMap.get(room.id()));
        }
    }

    private Page<RoomDto> getRooms(Integer page, Integer pageSize) throws Exception {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page.toString());
        params.add("size", pageSize.toString());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/").params(params)).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        Page<RoomDto> result = mapPagedResponse(mvcResult.getResponse().getContentAsString(), RoomDto.class);
        LOGGER.debug("{}", result);
        return result;
    }

    private RoomDto getRoom(Long id) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/{id}", id)).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        RoomDto result = mapFromJson(mvcResult.getResponse().getContentAsString(), RoomDto.class);
        LOGGER.debug("{}", result);
        return result;
    }

    private RoomDto addRoom(int index) throws Exception {
        RoomDataDto roomDataDto = new RoomDataDto(
                "TestHotel",
                "T" + index,
                "0" + index
        );
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(roomDataDto))).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        return mapFromJson(mvcResult.getResponse().getContentAsString(), RoomDto.class);
    }
}
