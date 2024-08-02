package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.OrderDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ReturnDto;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrderService {
    String saveOrder(OrderDto orderDto);
    String returnOrder(ReturnDto returnDto);

    String getLastOrderId();

    List<OrderDto> getAllOrders();

    String getNextReturnId();

    OrderDto searchOrder(String searchOrderId);

    Double totalSaleGet(String date);

   int mostSaleItemQtyGet(Date date);

   String mostSaleItemImgGet(Date date);

    List<OrderDto> branchWiseOrderDetailsGet(String branch);

    List<OrderDto> employeeSaleAllOrdersGet(String empMail);

    //TODO
//    String mostSaleItemGet();


}
