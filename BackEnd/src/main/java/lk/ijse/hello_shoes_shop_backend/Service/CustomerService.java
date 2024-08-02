package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.entity.CustomerEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    void saveCustomer(CustomerDto customerDto);
    CustomerDto searchCustomer(String searchCustomerId);
    String updateCustomer(String updateCustomerId, CustomerDto updateCustomerDetailsDto);
    String deleteCustomer(String deleteCustomerId);

}
