package com.example.User.Management.App.Mini_project_1.Service;

import com.example.User.Management.App.Mini_project_1.Dto.EmployeeDto;
import com.example.User.Management.App.Mini_project_1.Entity.Employee;
import com.example.User.Management.App.Mini_project_1.Repository.EmployeeRepository;
import com.example.User.Management.App.Mini_project_1.Service.imp.EmployeeSeriveimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeSeriveTests {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private EmployeeSeriveimpl employeeSeriveimpl;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
        employee.setFirstName("thrivendra");
        employee.setLastName("naidu");
        employee.setEmailId("thrivendra123@gmail.com");
        employee.setPhoneNumber(9182280832L);

        employeeDto = new EmployeeDto();
        employeeDto.setFirstName("thrivendra");
        employeeDto.setLastName("naidu");
        employeeDto.setEmailId("thrivendra123@gmail.com");
        employeeDto.setPhoneNumber(9182280832L);

        // Mock the mapping behavior
        given(modelMapper.map(employee, EmployeeDto.class)).willReturn(employeeDto);
    }

    @Test
    public void givenemployee_whenfindbyemailid_thereturnemployee()
    {
        //given
        given(employeeRepository.findByemailId(employee.getEmailId()))
                .willReturn(employee);

        //when
        EmployeeDto result = employeeSeriveimpl.finduserByEmail(employee.getEmailId());

        //then
        assertThat(result).isNotNull();
        assertThat(result.getEmailId()).isEqualTo(employee.getEmailId());
    }
}
