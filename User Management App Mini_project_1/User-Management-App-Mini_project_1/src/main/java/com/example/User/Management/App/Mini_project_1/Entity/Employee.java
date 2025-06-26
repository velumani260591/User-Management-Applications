package com.example.User.Management.App.Mini_project_1.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Schema(
        description = "Employee Model Information"
)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_details")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            description = "Employee First Name"
    )
    @Column(nullable = false)
    @NotEmpty(message = "FirstName should not be empty!")
    private String firstName;


    @Schema(
            description = "Employee Last Name"
    )
    @Column(nullable = false)
    @NotEmpty(message = "LastName should not be empty!")
    private String lastName;


    @Schema(
            description = "Employee emailId"
    )
    @Column(nullable = false,unique = true)
    @Email
    private String emailId;


    @Schema(
            description = "Employee phoneNumber"
    )
    @Column(nullable = false)
    private Long phoneNumber;
}
