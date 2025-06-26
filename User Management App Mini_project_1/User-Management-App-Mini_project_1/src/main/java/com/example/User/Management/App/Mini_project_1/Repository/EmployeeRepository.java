package com.example.User.Management.App.Mini_project_1.Repository;

import com.example.User.Management.App.Mini_project_1.Entity.Employee;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
@Tag(name = "Database connection from mysql to spring")
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByEmailIdAndIdNot(String emailId,Long id);

    Employee findByemailId(String emailId);



}
