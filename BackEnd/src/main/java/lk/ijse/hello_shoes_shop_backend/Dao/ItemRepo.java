package lk.ijse.hello_shoes_shop_backend.Dao;

import lk.ijse.hello_shoes_shop_backend.entity.ItemEntity;
import lk.ijse.hello_shoes_shop_backend.entity.OrderEntity;
import lk.ijse.hello_shoes_shop_backend.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<ItemEntity,String> {


    @Query(value = "SELECT itemCode FROM item ORDER BY itemCode DESC LIMIT 1", nativeQuery = true)
    String getLastItemId();

    @Query(value = "SELECT itemSize FROM size where itemId=?1" ,nativeQuery = true )
   List<String> selectItemHasAllSizesGet(String itemId);


    ItemEntity findByItemDesc(String desc);

}
