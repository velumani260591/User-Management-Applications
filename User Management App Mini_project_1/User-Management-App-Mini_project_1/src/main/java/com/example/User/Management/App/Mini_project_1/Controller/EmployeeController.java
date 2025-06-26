package com.example.User.Management.App.Mini_project_1.Controller;

import com.example.User.Management.App.Mini_project_1.Dto.EmployeeDto;
import com.example.User.Management.App.Mini_project_1.Entity.Employee;
import com.example.User.Management.App.Mini_project_1.Service.EmployeeSerive;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(

        name = "CRUD operation or API operation controller areas",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"

)
@Controller
@AllArgsConstructor

public class EmployeeController
{
    // EmployeeSerive file for calling the method in that class
    private EmployeeSerive employeeSerive;

// The homepage of the application that lists all employee records.

    @Operation(
            summary = "The main page of the website displays all the user data from the database."

    )
    @GetMapping("/index")
    public String homePage(Model model)
    {
        List<Employee> allusers= employeeSerive.getallusers();
        model.addAttribute("users",allusers);
        return "index";
    }

// It opens the user add page and user gives there data and it stores in employeeDto

    @Operation(
            summary = "Get details for user by using the adduser form"
    )

    @GetMapping("/Adduser")
    public String Adduser(Model model)
    {
        EmployeeDto employeeDto =new EmployeeDto();
        model.addAttribute("userDetails", employeeDto);
        return "adduser";
    }


// This method takes user data from the above method, checks if the email is already in use, and then saves the user if it's not.
   @Operation(
           summary = "Save user details",
           description = "Save the Employee Details and check the employee emailid was not used by any other user if another user used if it says the data was used"
   )
//    @PostMapping("/saveuser")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String saveuser(@Valid @ModelAttribute("userDetails") EmployeeDto employeeDto, BindingResult result , Model model)
//    {
//
//        if (employeeSerive.finduserByEmail(employeeDto.getEmailId())!=null)
//        {
//                result.rejectValue("emailId","email.exists",
//                        "This email is already registered. Please use another email.");
//
//        }
//
//        if (result.hasErrors())
//        {
////            model.addAttribute("userDetails", employeeDto);
//            return "Adduser";
//        }
//        employeeSerive.saveuser(employeeDto);
//        return "redirect:/index?success";
//
//    }
   @PostMapping("/saveuser")
   public String saveuser(
           @Valid @ModelAttribute("userDetails") EmployeeDto employeeDto,
           BindingResult result,
           Model model) {

       if (employeeSerive.finduserByEmail(employeeDto.getEmailId()) != null) {
           result.rejectValue("emailId", "email.exists", "This email is already registered. Please use another email.");
       }
       if (result.hasErrors()) {
           return "adduser";
       }
       employeeSerive.saveuser(employeeDto);
       return "redirect:/index?success";
   }

    @Operation(
            summary = "Delete employee by id",
            description ="Deletes a specific employee from the database using their employee Id."
    )

// it takes old user id and deleted the employee
    @GetMapping("/delete/{id}")
    public String deleteuser(@PathVariable("id") Long id)
    {
        employeeSerive.deleteUserByid(id);
        return "redirect:/index?delete";
    }


// get old user and asking new data from user

    @Operation(
            summary = "asked the user the new details"
    )
    @GetMapping("/update/{id}")
    public String getOldUser_With_new_details(@Valid @PathVariable("id") Long id,Model model)
    {
        EmployeeDto userdata= employeeSerive.getuseByid(id);
        model.addAttribute("userDetails",userdata);
        return "update_user_form";
    }

    // handle the update submission
    @Operation(
            summary = "Save the new details and also check the if the user given email used by another user or not"
    )
    @PostMapping("/updateuser")
    public String save_the_UpdateUserData(@ModelAttribute("userDetails") EmployeeDto employeeDto,BindingResult result, Model model)
    {

        if (employeeSerive.isEmailTakenByAnotherUser(employeeDto.getEmailId(),employeeDto.getId()))
        {
            result.rejectValue("emailId","email.exists",
                    "This email is already registered. Please use another email.");
        }

        if (result.hasErrors())
        {
            return "update_user_form";
        }
        employeeSerive.updateuserdata(employeeDto);
        return "redirect:/index?update";


    }



    // view single user data
    @GetMapping("/view/{id}")
    @Operation(
            summary = "View details of a user using their employee ID"

    )
    public String singeluser(@PathVariable("id") Long id,Model model)
    {
        EmployeeDto employeeDto = employeeSerive.getuseByid(id);
        model.addAttribute("user", employeeDto);
        return "view";
    }


}
