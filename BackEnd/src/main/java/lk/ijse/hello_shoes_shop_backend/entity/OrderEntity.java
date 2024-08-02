package lk.ijse.hello_shoes_shop_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "orders")
public class OrderEntity implements SuperEntity{
    @Id
    private String orderCode;
//   @Temporal(TemporalType.DATE)  //
    private Date purchaseDate;
    private String customerName;
    private String itemDesc;
    private int size;
    private double unitPrice;
    private int qty;
    private double total;
    private String paymentMethod;
    private int points;
    private String cashierName;
    private String orderStatus;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customerDetails;


    @ManyToMany(cascade = CascadeType.DETACH)
    private List<ItemEntity> buyItem;


    @ManyToOne
    @JoinColumn//(name = "order_sale_EmployeeId")
    private EmployeeEntity employeeEntity;



    @JsonIgnore/////////////////////////////////////////////////////////////////
    @OneToOne(mappedBy = "orderEntity")
    private ReturnEntity returnEntity;



}
