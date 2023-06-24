package com.e3.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class EmployeeRequestDto {

    private Long id;

    @NotNull(message = "First name should not be empty.")
    //@Pattern(regexp = "[a-zA-Z]")
    private String firstName;

    @NotNull(message = "Last name should not be empty.")
    //@Pattern(regexp = "[a-zA-Z]")
    private String lastName;

    @NotNull(message = "Employee must be assigned to a company.")
    private Long companyId;
}
