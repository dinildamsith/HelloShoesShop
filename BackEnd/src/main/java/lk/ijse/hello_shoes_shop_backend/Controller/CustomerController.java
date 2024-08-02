package lk.ijse.hello_shoes_shop_backend.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Dto.SupplierDto;
import lk.ijse.hello_shoes_shop_backend.Service.CustomerService;
import lk.ijse.hello_shoes_shop_backend.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = { RequestMethod.PATCH, RequestMethod.DELETE,
        RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET })
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/getAllCustomer", produces = "application/json")
    @GetMapping(produces = "application/json")
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/save")
    @PostMapping
    public String saveCustomer(@RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
        return "Save";
    }

    @GetMapping(value = "search/{id}")
    public CustomerDto searchCustomer(@PathVariable("id") String searchCustomerId) {
        CustomerDto customerDto = customerService.searchCustomer(searchCustomerId);
        return customerDto;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "update/{id}")
    public void updateCustomer(@PathVariable("id") String updateId, @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(updateId, customerDto);
    }

    @DeleteMapping(value = "delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCustomer(@PathVariable("id") String deleteCustomerId) {
        String deleteCustomer = customerService.deleteCustomer(deleteCustomerId);
        return deleteCustomer;
    }

    @PostConstruct
    public void birthdayGreetingsToCustomers() {
        emailService.birthdayGreetingsToCustomers();
    }



}
