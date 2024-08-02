package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Service.DateServices;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@Component
public class DateServiceIMPL implements DateServices {
    @Override
    public LocalDate orderLastReturnDay(Date orderPurchaseDate) {
        // Get the current date as java.util.Date
        Date currentDate = new Date();


        // Convert java.util.Date to java.time.LocalDate
        LocalDate orderBuyDate = orderPurchaseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate todayDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Add 3 days to the order return last day
        LocalDate orderReturnLstDay = orderBuyDate.plusDays(3);

        // Format the date if needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = orderReturnLstDay.format(formatter);

        // Print the date after 3 days
        System.out.println("Date after 3 days: " + formattedDate);

        return orderReturnLstDay;
    }
}
