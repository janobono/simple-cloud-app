package sk.janobono.sca.roomservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.janobono.sca.roomservice.common.exception.ApplicationExceptionCode;
import sk.janobono.sca.roomservice.domain.Room;
import sk.janobono.sca.roomservice.model.RoomDataDto;
import sk.janobono.sca.roomservice.model.RoomDto;
import sk.janobono.sca.roomservice.repository.RoomRepository;

@Service
public class RoomApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomApiService.class);

    private final RoomRepository roomRepository;

    public RoomApiService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Page<RoomDto> getRooms(Pageable pageable) {
        LOGGER.debug("getRooms({})", pageable);
        return roomRepository.findAll(pageable).map(this::mapRoom);
    }

    public RoomDto getRoom(Long id) {
        LOGGER.debug("getRoom({})", id);
        return mapRoom(roomRepository.findById(id).orElseThrow(
                () -> ApplicationExceptionCode.ROOM_NOT_FOUND.exception(id)
        ));
    }

    @Transactional
    public RoomDto addRoom(RoomDataDto roomDataDto) {
        LOGGER.debug("addRoom({})", roomDataDto);
        Room newRoom = new Room();
        newRoom.setName(roomDataDto.name());
        newRoom.setRoomNumber(roomDataDto.roomNumber());
        newRoom.setBedInfo(roomDataDto.bedInfo());
        newRoom = roomRepository.save(newRoom);
        return mapRoom(newRoom);
    }

    @Transactional
    public RoomDto setRoom(Long id, RoomDataDto roomDataDto) {
        LOGGER.debug("setRoom({},{})", id, roomDataDto);
        Room savedRoom = roomRepository.findById(id).orElseThrow(
                () -> ApplicationExceptionCode.ROOM_NOT_FOUND.exception(id)
        );
        savedRoom.setName(roomDataDto.name());
        savedRoom.setRoomNumber(roomDataDto.roomNumber());
        savedRoom.setBedInfo(roomDataDto.bedInfo());
        savedRoom = roomRepository.save(savedRoom);
        return mapRoom(savedRoom);
    }

    @Transactional
    public void deleteRoom(Long id) {
        LOGGER.debug("deleteRoom({})", id);
        roomRepository.deleteById(id);
    }

    private RoomDto mapRoom(Room room) {
        LOGGER.debug("mapRoom({})", room);
        return new RoomDto(
                room.getId(),
                room.getName(),
                room.getRoomNumber(),
                room.getBedInfo()
        );
    }
}
