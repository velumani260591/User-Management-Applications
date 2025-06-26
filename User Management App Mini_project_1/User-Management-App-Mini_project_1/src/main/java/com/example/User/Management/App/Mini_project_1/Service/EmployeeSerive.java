package com.example.User.Management.App.Mini_project_1.Service;

import com.example.User.Management.App.Mini_project_1.Dto.EmployeeDto;
import com.example.User.Management.App.Mini_project_1.Entity.Employee;

import java.util.List;

public interface EmployeeSerive {
    EmployeeDto saveuser(EmployeeDto employeeDto);

    List<Employee> getallusers();

    void deleteUserByid(Long id);

    EmployeeDto getuseByid(Long id);

    EmployeeDto updateuserdata(EmployeeDto employeeDto);


    EmployeeDto  finduserByEmail(String emailId);

    boolean isEmailTakenByAnotherUser(String email, Long id);



}
