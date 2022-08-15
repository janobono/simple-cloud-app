package sk.janobono.sca.roomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.janobono.sca.roomservice.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
