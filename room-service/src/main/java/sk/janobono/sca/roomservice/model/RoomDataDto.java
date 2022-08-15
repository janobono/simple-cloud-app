package sk.janobono.sca.roomservice.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public record RoomDataDto(
        @NotEmpty @Length(max = 16) String name,
        @NotEmpty @Length(min = 2, max = 2) String roomNumber,
        @NotEmpty @Length(min = 2, max = 2) String bedInfo
) {
}
