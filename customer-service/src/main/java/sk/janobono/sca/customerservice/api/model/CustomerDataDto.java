package sk.janobono.sca.customerservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Schema(name = "CustomerData")
public record CustomerDataDto(
        @NotEmpty @Length(max = 64) String firstName,
        @NotEmpty @Length(max = 64) String lastName,
        @NotEmpty @Length(max = 64) @Email String emailAddress,
        @NotEmpty @Length(max = 64) String address,
        @NotEmpty @Length(max = 32) String country,
        @NotEmpty @Length(max = 12) String state,
        @NotEmpty @Length(max = 24) String phoneNumber
) {
}
