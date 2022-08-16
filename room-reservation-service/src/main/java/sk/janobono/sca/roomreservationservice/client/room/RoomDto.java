package sk.janobono.sca.roomreservationservice.client.room;

public record RoomDto(
        Long id,
        String name,
        String roomNumber,
        String bedInfo
) {
}
