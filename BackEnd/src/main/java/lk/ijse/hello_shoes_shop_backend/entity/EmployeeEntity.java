package lk.ijse.hello_shoes_shop_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.ijse.hello_shoes_shop_backend.enums.Gender;
import lk.ijse.hello_shoes_shop_backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeEntity implements SuperEntity{
    @Id
    private String employeeCode;
    private String employeeName;
    @Column(columnDefinition = "LONGTEXT")
    private String employeePic;
    private Gender gender;
    private String status;
    private String designation;
    private Role role;
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    @Temporal(TemporalType.DATE)
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

    @OneToMany(mappedBy = "employeeEntity",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<OrderEntity> sales;

}
