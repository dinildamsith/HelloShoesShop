package lk.ijse.hello_shoes_shop_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.ijse.hello_shoes_shop_backend.enums.Gender;
import lk.ijse.hello_shoes_shop_backend.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity implements SuperEntity{
    @Id
    private String customerCode;
    private String customerName;
    @Enumerated(EnumType.STRING)
    private Gender customerGender;
    @Temporal(TemporalType.DATE)
    private Date customerJoinDate;
    @Enumerated(EnumType.STRING)
    private Level level;
    private int  totalPoints;
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNumber;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date recentPurchaseDate;

    @JsonIgnore // Research  This Annotation
    @ToStringExclude
    @OneToMany(mappedBy = "customerDetails" ,targetEntity = OrderEntity.class , cascade = CascadeType.REMOVE,orphanRemoval =true)
    private List<OrderEntity> orderEntities = new ArrayList<>();

}
