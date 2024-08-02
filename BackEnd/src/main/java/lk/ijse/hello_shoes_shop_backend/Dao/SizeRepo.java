package lk.ijse.hello_shoes_shop_backend.Dao;

import lk.ijse.hello_shoes_shop_backend.entity.ItemEntity;
import lk.ijse.hello_shoes_shop_backend.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public interface SizeRepo extends JpaRepository<StockEntity,String> {
    @Query(value = "SELECT size.qty FROM size  WHERE stockId = ?1", nativeQuery = true)
    String checkItemHaveStock(String stockId);

    @Query(value = "SELECT * FROM size WHERE itemId = ?1 AND itemSize = ?2", nativeQuery = true)
    StockEntity getItemQty(String itemId,String itemSize);



}
