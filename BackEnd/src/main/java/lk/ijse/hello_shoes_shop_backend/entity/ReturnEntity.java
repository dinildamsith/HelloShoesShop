package lk.ijse.hello_shoes_shop_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "return_details")
@Entity
public class ReturnEntity implements SuperEntity{
    @Id
    private String returnId;
    private String itemId;
    private String itemDes;
    private String size;
    private String qty;
    private Date  returnDate;



    @OneToOne
    @JoinColumn(name = "return_order_Id")
    private OrderEntity orderEntity;
}
