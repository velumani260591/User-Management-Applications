package com.example.User.Management.App.Mini_project_1.Repository;

import com.example.User.Management.App.Mini_project_1.Entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

     Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
        employee.setFirstName("thrivendra");
        employee.setLastName("naidu");
        employee.setEmailId("thrivendra123@gmail.com");
        employee.setPhoneNumber(9182280832L);
    }

    @Test
    public void givenEmployee_whenSave_thenReturnSavedEmployee() {
        //Given (we use the setUp() )

        //when
        Employee savedEmployee = employeeRepository.save(employee);

        // then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
        assertThat(savedEmployee.getFirstName()).isEqualTo("thrivendra");
        assertThat(savedEmployee.getEmailId()).isEqualTo("thrivendra123@gmail.com");
    }

    @Test
    public void givenEmployeList_WhenFindALl_thenReturnEmployeesList()
    {
        //Given
        Employee employee1=new Employee();
        employee1.setFirstName("Rudrapati");
        employee1.setLastName("thrivendra");
        employee1.setEmailId("thrivendra@gmail.com");
        employee1.setPhoneNumber(91822832L);

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        //when
        List<Employee> employeeList=employeeRepository.findAll();

        //then

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }
    @Test
    public void givenEmployee_whenDeltedById_thenNoReturn()
    {
        //given
            Employee employee1=employeeRepository.save(employee);
            Long id=employee1.getId();
        //when
        employeeRepository.deleteById(id);
        //then
        Optional<Employee> deletedemployee=employeeRepository.findById(id);
        assertThat(deletedemployee).isEmpty();
    }

    @Test
    public void givenEmployee_whenFindById_thenNoReturn()
    {
        // given
        Employee employee1=employeeRepository.save(employee);

        //when
       Optional<Employee> employee2= employeeRepository.findById(employee1.getId());

       //then
        assertThat(employee2).isNotEmpty();
        assertThat(employee2).get().isEqualTo(employee1);
    }

    @Test
    public void givenEmployee_whenfindByemailId_thenReturnEMployee()
    {
        // given
        Employee employee1=employeeRepository.save(employee);

        //when
        Employee employee2=employeeRepository.findByemailId(employee1.getEmailId());

        //then
        assertThat(employee2).isNotNull();
        assertThat(employee2).isEqualTo(employee1);
    }
}

