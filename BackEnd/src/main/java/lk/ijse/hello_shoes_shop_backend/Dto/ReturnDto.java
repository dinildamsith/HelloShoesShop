package lk.ijse.hello_shoes_shop_backend.Dto;

import lk.ijse.hello_shoes_shop_backend.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnDto {
    private String returnId;
    private String itemId;
    private String itemDes;
    private String size;
    private String qty;
    private String returnDate;
    private OrderEntity orderEntity;
}
