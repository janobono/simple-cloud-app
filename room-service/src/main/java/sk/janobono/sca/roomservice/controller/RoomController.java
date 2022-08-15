package sk.janobono.sca.roomservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.janobono.sca.roomservice.model.RoomDataDto;
import sk.janobono.sca.roomservice.model.RoomDto;
import sk.janobono.sca.roomservice.service.RoomApiService;

import javax.validation.Valid;

@RestController
public class RoomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    private final RoomApiService roomApiService;

    public RoomController(RoomApiService roomApiService) {
        this.roomApiService = roomApiService;
    }

    @GetMapping
    public ResponseEntity<Page<RoomDto>> getRooms(Pageable pageable) {
        LOGGER.debug("getRooms({})", pageable);
        return new ResponseEntity<>(roomApiService.getRooms(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable("id") Long id) {
        LOGGER.debug("getRoom({})", id);
        return new ResponseEntity<>(roomApiService.getRoom(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomDto> addRoom(@Valid @RequestBody RoomDataDto roomDataDto) {
        LOGGER.debug("addRoom({})", roomDataDto);
        return new ResponseEntity<>(roomApiService.addRoom(roomDataDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDto> setRoom(@PathVariable("id") Long id, @Valid @RequestBody RoomDataDto roomDataDto) {
        LOGGER.debug("setRoom({},{})", id, roomDataDto);
        return new ResponseEntity<>(roomApiService.setRoom(id, roomDataDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") Long id) {
        LOGGER.debug("deleteRoom({})", id);
        roomApiService.deleteRoom(id);
    }
}
