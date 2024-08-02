package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.CustomerRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.MailDto;
import lk.ijse.hello_shoes_shop_backend.Service.EmailService;
import lk.ijse.hello_shoes_shop_backend.entity.CustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class EmailServiceIMPL implements EmailService {

    @Autowired
    CustomerRepo customerRepo;
    Logger logger = LoggerFactory.getLogger(EmailServiceIMPL.class);

    @Override
    public void birthdayGreetingsToCustomers() {

        try {
            List<CustomerEntity> all = customerRepo.findAll();
            Date currentDate = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String formattedCurrentDate = sdf.format(date);

            for (CustomerEntity customer : all) {


                int CustBodMonth = customer.getBirthDay().getMonth()+1;
                int CustBodDay = customer.getBirthDay().getDate();

                if (currentDate.getMonth()+1 == CustBodMonth && currentDate.getDate() == CustBodDay) {
                    MailDto mailDto = new MailDto();
                    mailDto.setTo(customer.getEmail()); // Receiver's email
                    mailDto.setSubject("\uD83C\uDF89 Happy Birthday from Hello Shoe Shop! \uD83C\uDF82"); // Email subject
                    mailDto.setMsg("Happy Birthday " + customer.getCustomerName() + " !!! " + "\uD83C\uDF89 We hope your special day is filled with joy and laughter. As a valued member of our shop, we're delighted to celebrate with you. Wishing you a fantastic year ahead filled with wonderful moments and exciting surprises! \uD83C\uDF82\uD83C\uDF88"); // Email message
                    Thread thread = new Thread(mailDto);
                    thread.start();

                }else{
                    logger.info("Today No Birthdays");
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
