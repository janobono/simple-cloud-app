package sk.janobono.sca.roomreservationservice.client.room;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "room-service", url = "${app.room-service-url}")
public interface RoomClient {
    @GetMapping("/api/v1/rooms")
    Page<RoomDto> getRooms(@RequestParam Pageable pageable);

    @GetMapping("/api/v1/rooms/{id}")
    RoomDto getRoom(@PathVariable("id") long id);
}
