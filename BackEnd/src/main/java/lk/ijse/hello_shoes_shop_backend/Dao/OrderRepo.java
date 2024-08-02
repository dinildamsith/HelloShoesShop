package lk.ijse.hello_shoes_shop_backend.Dao;

import lk.ijse.hello_shoes_shop_backend.Dto.OrderDto;
import lk.ijse.hello_shoes_shop_backend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,String> {


    // TODO
    @Query(value = "SELECT itemDesc FROM orders WHERE purchaseDate = :date GROUP BY itemDesc ORDER BY COUNT(itemDesc) DESC LIMIT 1", nativeQuery = true)
    String mostSaleItemGet(@Param("date") String date);


    @Query("SELECT itemDesc FROM OrderEntity  WHERE purchaseDate =?1 GROUP BY itemDesc ORDER BY COUNT(*) DESC LIMIT 1")
    String findMostSaleItemDesc(Date date);

    @Query(value = "SELECT orders.total FROM  orders WHERE orders.purchaseDate=?1",nativeQuery = true)
    List<Double> getAllOrderTotal(String date);
    @Query(value = "SELECT orderCode FROM orders ORDER BY orderCode DESC LIMIT 1", nativeQuery = true)
    String getLastOrderId();

    List<OrderEntity> findByPurchaseDate(Date date);
}
