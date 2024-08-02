package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.EmployeeDto;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();
    String saveEmployee(EmployeeDto employeeDto);
    EmployeeDto searchEmployee(String searchEmployeeId);
    void updateEmployee(String updateEmpId , EmployeeDto employeeDto);
    String deleteEmployee(String email , String id);
}
