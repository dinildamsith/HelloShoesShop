package lk.ijse.hello_shoes_shop_backend.Controller;

import lk.ijse.hello_shoes_shop_backend.Service.StockService;
import lk.ijse.hello_shoes_shop_backend.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.PATCH, RequestMethod.DELETE ,RequestMethod.POST,RequestMethod.PUT,RequestMethod.GET})
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping
    public List<StockEntity> getAllStock(){
        List<StockEntity> allStock = stockService.getAllStock();
        return allStock;
    }

    @GetMapping
    @RequestMapping("/sendInfoItemQty")
    public List<String> checkItemQtySendInfo(){
        List<String> itemQtyMassage = stockService.checkItemQtySendInfo();
        return itemQtyMassage;
    }
}
