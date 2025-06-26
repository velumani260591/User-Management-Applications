package com.example.User.Management.App.Mini_project_1.Service.imp;

import com.example.User.Management.App.Mini_project_1.Dto.EmployeeDto;
import com.example.User.Management.App.Mini_project_1.Entity.Employee;
import com.example.User.Management.App.Mini_project_1.Repository.EmployeeRepository;
import com.example.User.Management.App.Mini_project_1.Service.EmployeeSerive;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Tag(name = "Handles full database operations: save, find, delete, and update for employees")

@Service
@AllArgsConstructor
public class EmployeeSeriveimpl implements EmployeeSerive {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;


    // creating a user and saving the user details in a database

    @Operation(
            summary = "save the user to database"
    )
    @Override
    public EmployeeDto saveuser(EmployeeDto employeeDto) {


        if(employeeRepository.findByemailId(employeeDto.getEmailId())!=null)
        {
            throw new IllegalArgumentException("Email already exists!");
        }


        Employee employee =modelMapper.map(employeeDto, Employee.class);

        Employee savedata= employeeRepository.save(employee);

        return modelMapper.map(savedata, EmployeeDto.class);
    }

    // get all users

    @Operation(
            summary = "Get All users from database and return it"
    )
    @Override
    public List<Employee> getallusers() {
        return employeeRepository.findAll();
    }

    @Operation(
            summary = "Deleted  the user from database by using id"
    )

    @Override
    public void deleteUserByid(Long id) {
        employeeRepository.deleteById(id);

    }

    @Operation(
            summary = "Get  the user from database by using id"    )
    @Override
    public EmployeeDto getuseByid(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Operation(
            summary = "Updated  the user from database by using id with new details form user"
    )

    @Override
    public EmployeeDto updateuserdata(EmployeeDto employeeDto) {

        if (employeeRepository.existsByEmailIdAndIdNot(employeeDto.getEmailId(),employeeDto.getId()))
        {
            throw new IllegalArgumentException("Email already exists!");
        }
        Employee existingEmployee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(()-> new RuntimeException("user Not found"));


        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmailId(employeeDto.getEmailId());
        existingEmployee.setPhoneNumber(employeeDto.getPhoneNumber());

        Employee updateuser= employeeRepository.save(existingEmployee);
        return modelMapper.map(updateuser, EmployeeDto.class);
    }


    @Override
    public EmployeeDto finduserByEmail(String email) {

        Employee employee = employeeRepository.findByemailId(email);
        if(employee==null )
        {
            return null;
        }
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public boolean isEmailTakenByAnotherUser(String email, Long id) {
        return employeeRepository.existsByEmailIdAndIdNot(email, id);
    }


}
