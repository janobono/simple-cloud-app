package sk.janobono.sca.roomservice.model;

public record RoomDto(
        Long id,
        String name,
        String roomNumber,
        String bedInfo
) {
}
