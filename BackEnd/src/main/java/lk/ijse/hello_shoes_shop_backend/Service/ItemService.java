package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ItemDto;
import lk.ijse.hello_shoes_shop_backend.entity.StockEntity;

import java.sql.ResultSet;
import java.util.List;

public interface ItemService {
    List<ItemDto> getAllItems();
    void saveItem(String itemSupplierId,ItemDto itemDto,String size,String qty);
    void updateItem(String updateItemId , ItemDto itemDto,String supplierId);
    void deleteItem(String deleteItemId);

    ItemDto searchItem(String searchItemId);

    String getLastItemId();

    List<String> selectItemHasAllSizesGet(String selectItemId);

}
