package sk.janobono.sca.roomservice.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Room")
public record RoomDto(
        Long id,
        String name,
        String roomNumber,
        String bedInfo
) {
}
