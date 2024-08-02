package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.CustomerRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Service.CustomerService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {

    @Autowired  // methon autowired yatin podi irak yane field injection recomend karne nathi handa
    DataConvert dataConvert;
    @Autowired
    CustomerRepo customerRepo;


    Logger logger = LoggerFactory.getLogger(CustomerServiceIMPL.class);

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> allCustomers = dataConvert.customerEntityListConvertCustomerDtoList(customerRepo.findAll());
        logger.info("Customer Get All Successes");
        return allCustomers;
    }

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        customerRepo.save(dataConvert.customerDtoConvertCustomerEntity(customerDto));
        logger.info("Customer Save Done");
    }

    @Override
    public CustomerDto searchCustomer(String searchCustomerId) {

        CustomerEntity searchCustomer = customerRepo.findById(searchCustomerId).orElse(null);

        if (searchCustomer != null) {
            logger.info("Customer Search Success");
            return dataConvert.customerEntityConvertCustomerDto(searchCustomer);
        } else {
            logger.info("This Id Have No Customer");
        }
        return null;
    }

    @Override
    public String updateCustomer(String updateCustomerId, CustomerDto updateCustomerDetailsDto) {

        CustomerEntity updateCustomer = customerRepo.findById(updateCustomerId).orElse(null);

        if (updateCustomer != null) {

            updateCustomer.setCustomerName(updateCustomerDetailsDto.getCustomerName());
            updateCustomer.setCustomerGender(updateCustomerDetailsDto.getCustomerGender());
            updateCustomer.setCustomerJoinDate(updateCustomer.getCustomerJoinDate());
            updateCustomer.setBirthDay(updateCustomer.getBirthDay());
            updateCustomer.setAddressLine1(updateCustomerDetailsDto.getAddressLine1());
            updateCustomer.setAddressLine2(updateCustomerDetailsDto.getAddressLine2());
            updateCustomer.setAddressLine3(updateCustomerDetailsDto.getAddressLine3());
            updateCustomer.setAddressLine4(updateCustomerDetailsDto.getAddressLine4());
            updateCustomer.setAddressLine5(updateCustomerDetailsDto.getAddressLine5());
            updateCustomer.setContactNumber(updateCustomerDetailsDto.getContactNumber());
            updateCustomer.setEmail(updateCustomerDetailsDto.getEmail());

            customerRepo.save(updateCustomer);
            logger.info("Customer Update Success");
            return "Update Customer";
        } else {
            logger.info("This Id Have No Customer");
            return "This Id Have Not Customer";
        }
    }

    @Override
    public String deleteCustomer(String deleteCustomerId) {
        boolean checkIdHaveCustomer = customerRepo.existsById(deleteCustomerId);

        if (checkIdHaveCustomer) {
            customerRepo.deleteById(deleteCustomerId);
            logger.info("Customer Delete Success");
        } else {
            logger.info("This Id Have No Customer");
            return "This Id Have No Customer";
        }
        return null;
    }
}
