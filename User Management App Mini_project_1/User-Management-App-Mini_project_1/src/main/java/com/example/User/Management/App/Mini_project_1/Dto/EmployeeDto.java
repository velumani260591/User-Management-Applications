package com.example.User.Management.App.Mini_project_1.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(
        description = "Employee Model Information"
)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            description = "EmployeeDto First Name"
    )
    private String firstName;

    @Schema(
            description = "EmployeeDto Last Name"
    )
    private String lastName;

    @Schema(
            description = "EmployeeDto emailId"
    )
    private String emailId;

    @Schema(
            description = "EmployeeDto phoneNumber"
    )
    private Long phoneNumber;
}
