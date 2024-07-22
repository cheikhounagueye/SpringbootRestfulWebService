package org.bambacompany.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private Long id;

    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;

    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email should be valid")
    private String email;





}
