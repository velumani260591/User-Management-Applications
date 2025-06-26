package com.example.User.Management.App.Mini_project_1.Controller;

import com.example.User.Management.App.Mini_project_1.Dto.EmployeeDto;
import com.example.User.Management.App.Mini_project_1.Service.EmployeeSerive;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeSerive employeeSerive;

    @Autowired
    private ObjectMapper objectMapper;

    private EmployeeDto employeeDto;
    private EmployeeDto existingEmployeeDto;

    @BeforeEach
    public void setUp() {
        employeeDto = new EmployeeDto();
        employeeDto.setFirstName("thrivendra");
        employeeDto.setLastName("Naidu");
        employeeDto.setEmailId("thrivendra123@gmail.com");
        employeeDto.setPhoneNumber(1234567890L);

        // FOR THE FAILING TEST CASE: Initialize existingEmployeeDto
        existingEmployeeDto = new EmployeeDto();
        existingEmployeeDto.setId(1L); // Crucial for update scenarios
        existingEmployeeDto.setFirstName("Existing");
        existingEmployeeDto.setLastName("User");
        existingEmployeeDto.setEmailId("existing.user@example.com");
        existingEmployeeDto.setPhoneNumber(9988776655L);
    }

    @Test
    public void givenEmployee_whenSave_thenReturnSavedEmployee() throws Exception {
        // Mock the service
        given(employeeSerive.saveuser(any(EmployeeDto.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        given(employeeSerive.finduserByEmail(any(String.class)))
                .willReturn(null);

        mockMvc.perform(post("/saveuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDto))).andExpect(status().isCreated());
    }

    @Test
    public void giveEmployee_whenDEletedByid_thenNoReturn() throws Exception {
        Long id=1L;

        willDoNothing().given(employeeSerive).deleteUserByid(eq(id));

        mockMvc.perform(get("/delete/{id}",id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index?delete"));

    }


    @Test
    public void givenEmployeeId_whenGetOldUserWithNewDetails_thenReturnUpdateUserFormAndModel() throws Exception {
        Long employeeIdToUpdate = 1L;


        given(employeeSerive.getuseByid(eq(employeeIdToUpdate)))
                .willReturn(existingEmployeeDto); // Return the DTO for the existing user

        mockMvc.perform(get("/update/{id}", employeeIdToUpdate))
                .andExpect(status().isOk()) // Expect HTTP 200 OK because it returns a view
                .andExpect(model().attribute("userDetails", existingEmployeeDto));
    }

}
