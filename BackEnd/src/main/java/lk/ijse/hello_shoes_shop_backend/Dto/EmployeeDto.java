package lk.ijse.hello_shoes_shop_backend.Dto;

import jakarta.persistence.Column;
import lk.ijse.hello_shoes_shop_backend.enums.Gender;
import lk.ijse.hello_shoes_shop_backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String employeeCode;
    private String employeeName;
    private String employeePic;
    private Gender gender;
    private String status;
    private String designation;
    private Role role;
    private Date birthDay;
    private Date joinDate;
    private String attachedBranch;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String contact;
    private String email;
    private String guardianName;
    private String emergencyContact;
}
