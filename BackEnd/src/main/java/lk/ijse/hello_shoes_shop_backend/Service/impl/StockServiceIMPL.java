package lk.ijse.hello_shoes_shop_backend.Service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.hello_shoes_shop_backend.Dao.SizeRepo;
import lk.ijse.hello_shoes_shop_backend.Service.StockService;
import lk.ijse.hello_shoes_shop_backend.entity.StockEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StockServiceIMPL implements StockService {
    @Autowired
    SizeRepo sizeRepo;

    Logger logger = LoggerFactory.getLogger(StockServiceIMPL.class);
    @Override
    public List<StockEntity> getAllStock() {
        List<StockEntity> allStock = sizeRepo.findAll();
        return allStock;
    }

    @Override
    public List<String> checkItemQtySendInfo() {
        List<StockEntity> allItemsStock = sizeRepo.findAll();

        List<String> lowItemMassages = new ArrayList<>();

        for (StockEntity stockEntity:allItemsStock) {
            String stockId = stockEntity.getStockId();
            if (50<Integer.parseInt(stockEntity.getQty())){
                logger.info(stockEntity.getStockId() +" Availed Item");
            }else{
                logger.info(stockId+" This Item Low Quantity");
                lowItemMassages.add(stockId + " " + "Item Low Quantity Have Stock.Please Buy This Item");
                //return stockId + " " + "Item Low Quantity Have Stock.Please Buy This Item";
            }
        }
        return lowItemMassages;
    }
}
