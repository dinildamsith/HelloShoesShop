package lk.ijse.hello_shoes_shop_backend.Controller;

import lk.ijse.hello_shoes_shop_backend.Dao.UserRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.EmployeeDto;
import lk.ijse.hello_shoes_shop_backend.Service.EmployeeService;
import lk.ijse.hello_shoes_shop_backend.Service.UserService;
import lk.ijse.hello_shoes_shop_backend.entity.EmployeeEntity;
import lk.ijse.hello_shoes_shop_backend.entity.UserEntity;
import lk.ijse.hello_shoes_shop_backend.enums.Gender;
import lk.ijse.hello_shoes_shop_backend.enums.Role;
import lk.ijse.hello_shoes_shop_backend.util.UtilMatters;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping( "/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.PATCH, RequestMethod.DELETE ,RequestMethod.POST,RequestMethod.PUT,RequestMethod.GET})
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;


    @RequestMapping("/allEmployees")
    @GetMapping
    List<EmployeeDto> getAllEmployees(){
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping
    @RequestMapping("/search/{id}")
    EmployeeDto searchEmployee(@PathVariable ("id") String searchEmployeeId){
        EmployeeDto employeeDto = employeeService.searchEmployee(searchEmployeeId);
        return employeeDto;
    }

    @RequestMapping("/save")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String saveEmployee(@RequestPart("employee_code") String emp_code,
                        @RequestPart("employee_name") String emp_name,
                        @RequestPart("employee_pic") String emp_pic,
                        @RequestPart("gender")  String gender,
                        @RequestPart("status") String status,
                        @RequestPart("designation") String designation,
                        @RequestPart("role")String role,
                        @RequestPart("birthday")String birthday,
                        @RequestPart("joinDate") String joinDate,
                        @RequestPart("attachedBranch") String branch,
                        @RequestPart("address1") String addr1,
                        @RequestPart("address2") String addr2,
                        @RequestPart("address3") String addr3,
                        @RequestPart("address4") String addr4,
                        @RequestPart("address5") String addr5,
                        @RequestPart("contact") String contact,
                        @RequestPart("email") String email,
                        @RequestPart("guardiaName") String guardName,
                        @RequestPart("emergencyContact") String emgContact){


        // Deine the date format
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the strings into LocalDate objects
        LocalDate parsedBirthday = LocalDate.parse(birthday, dateTimeFormatter);
        LocalDate parsedJoinDate = LocalDate.parse(joinDate, dateTimeFormatter);

        // Convert LocalDate objects to Date objects
        Date birthdayDate = java.sql.Date.valueOf(parsedBirthday);
        Date joinDateDate = java.sql.Date.valueOf(parsedJoinDate);




        EmployeeDto employeeDto  = new EmployeeDto();
        employeeDto.setEmployeeCode(emp_code);
        employeeDto.setEmployeeName(emp_name);
        employeeDto.setEmployeePic(emp_pic);
        employeeDto.setGender(Gender.valueOf(gender));
        employeeDto.setStatus(status);
        employeeDto.setDesignation(designation);
        employeeDto.setRole(Role.valueOf(role));
        employeeDto.setBirthDay(birthdayDate);
        employeeDto.setJoinDate(joinDateDate);
        employeeDto.setAttachedBranch(branch);
        employeeDto.setAddress1(addr1);
        employeeDto.setAddress2(addr2);
        employeeDto.setAddress3(addr3);
        employeeDto.setAddress4(addr4);
        employeeDto.setAddress5(addr5);
        employeeDto.setContact(contact);
        employeeDto.setEmail(email);
        employeeDto.setGuardianName(guardName);
        employeeDto.setEmergencyContact(emgContact);


        String employeeToken = employeeService.saveEmployee(employeeDto);
        return employeeToken;
    }


    @RequestMapping("/update/{id}")
    @PutMapping
    String updateEmployee(@PathVariable ("id") String updateId,
                          @RequestPart("employee_name") String emp_name,
                          @RequestPart("employee_pic") String emp_pic,
                          @RequestPart("gender")  String gender,
                          @RequestPart("status") String status,
                          @RequestPart("designation") String designation,
                          @RequestPart("role")String role,
                          @RequestPart("birthday")String birthday,
                          @RequestPart("joinDate") String joinDate,
                          @RequestPart("attachedBranch") String branch,
                          @RequestPart("address1") String addr1,
                          @RequestPart("address2") String addr2,
                          @RequestPart("address3") String addr3,
                          @RequestPart("address4") String addr4,
                          @RequestPart("address5") String addr5,
                          @RequestPart("contact") String contact,
                          @RequestPart("email") String email,
                          @RequestPart("guardiaName") String guardName,
                          @RequestPart("emergencyContact") String emgContact){


        // Deine the date format
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the strings into LocalDate objects
        LocalDate parsedBirthday = LocalDate.parse(birthday, dateTimeFormatter);
        LocalDate parsedJoinDate = LocalDate.parse(joinDate, dateTimeFormatter);

        // Convert LocalDate objects to Date objects
        Date birthdayDate = java.sql.Date.valueOf(parsedBirthday);
        Date joinDateDate = java.sql.Date.valueOf(parsedJoinDate);



        EmployeeDto employeeDto  = new EmployeeDto();
        employeeDto.setEmployeeName(emp_name);
        employeeDto.setEmployeePic(emp_pic);
        employeeDto.setGender(Gender.valueOf(gender));
        employeeDto.setStatus(status);
        employeeDto.setDesignation(designation);
        employeeDto.setRole(Role.valueOf(role));
        employeeDto.setBirthDay(birthdayDate);
        employeeDto.setJoinDate(joinDateDate);
        employeeDto.setAttachedBranch(branch);
        employeeDto.setAddress1(addr1);
        employeeDto.setAddress2(addr2);
        employeeDto.setAddress3(addr3);
        employeeDto.setAddress4(addr4);
        employeeDto.setAddress5(addr5);
        employeeDto.setContact(contact);
        employeeDto.setEmail(email);
        employeeDto.setGuardianName(guardName);
        employeeDto.setEmergencyContact(emgContact);

        employeeService.updateEmployee(updateId,employeeDto);

        return "Update";
    }

    @DeleteMapping
    @RequestMapping("/delete/{email}/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEmployee(@PathVariable ("email") String userDeleteEmail, @PathVariable ("id") String employeeDeleteId) {
        String s = employeeService.deleteEmployee(userDeleteEmail, employeeDeleteId);
        return s;

    }
}
