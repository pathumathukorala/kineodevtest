package com.e3.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeSearchDto {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private Long companyId;

    private String companyName;
}
