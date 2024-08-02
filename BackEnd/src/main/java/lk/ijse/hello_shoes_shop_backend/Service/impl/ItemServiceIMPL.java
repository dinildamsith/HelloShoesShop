package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.ItemRepo;
import lk.ijse.hello_shoes_shop_backend.Dao.SizeRepo;
import lk.ijse.hello_shoes_shop_backend.Dao.SupplierRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ItemDto;
import lk.ijse.hello_shoes_shop_backend.Service.ItemService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.ItemEntity;
import lk.ijse.hello_shoes_shop_backend.entity.StockEntity;

import lk.ijse.hello_shoes_shop_backend.entity.SupplierEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ItemServiceIMPL implements ItemService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    DataConvert dataConvert;
    @Autowired
    SupplierRepo supplierRepo;
    @Autowired
    SizeRepo sizeRepo;

    Logger logger = LoggerFactory.getLogger(ItemServiceIMPL.class);

    @Override
    public List<ItemDto> getAllItems() {
        List<ItemEntity> all = itemRepo.findAll();
        logger.info("Items All Get Success");
        return dataConvert.itemEntityListConvertItemDtoList(all);
    }

    @Override
    public void saveItem(String itemSupplySupplierId ,ItemDto itemDto,String size,String qty) {

        SupplierEntity supplierEntity = supplierRepo.findById(itemSupplySupplierId).orElse(null);


        if (supplierEntity != null){


            ItemEntity itemEntity = dataConvert.itemDtoConvertItemEntity(itemDto);
            StockEntity stockEntity = new StockEntity();

            stockEntity.setStockId(itemDto.getItemDesc()+ " ( size - "+size+")");
            stockEntity.setItemSize(size);
            stockEntity.setQty(qty);
            stockEntity.setItemEntititys(itemEntity);

            String haveQty = sizeRepo.checkItemHaveStock(stockEntity.getStockId());

            if (50 < Integer.parseInt(qty)){
                itemEntity.setStatus("Available");
            }else if(50 > Integer.parseInt(qty)){
                itemEntity.setStatus("Low");
            }

            if (haveQty !=null){
                int haveQtyConvert = Integer.parseInt(haveQty);
                String qty1 = stockEntity.getQty();
                int qt = Integer.parseInt(qty1);


                haveQtyConvert+=qt;
                stockEntity.setQty(String.valueOf(haveQtyConvert));
            }

            List<SupplierEntity> supplierEntityList = new ArrayList<>();
            List<ItemEntity> itemEntityList = new ArrayList<>();
            List<StockEntity> stockEntityList = new ArrayList<>();



            supplierEntityList.add(supplierEntity);
            itemEntityList.add(itemEntity);

            itemEntity.setSupplierEntityList(supplierEntityList);
            supplierEntity.setItemEntityList(itemEntityList);

             stockEntity.setItemEntititys(itemEntity);
             stockEntityList.add(stockEntity);
             stockEntityList.add(stockEntity);


            // Set Item Type
            switch (itemDto.getItemType()) {
                case "M":
                    itemEntity.setItemType("MEN");
                    break;
                case "W":
                    itemEntity.setItemType("WOMEN");
            }

            // Set Verities Type
            switch (itemDto.getVerities()){
                case "H":
                    itemEntity.setVerities("Heel");
                    break;
                case "F":
                    itemEntity.setVerities("Flats");
                    break;
                case "W":
                    itemEntity.setVerities("Wedges");
                    break;
                case "FF":
                    itemEntity.setVerities("Flip Flops");
                    break;
                case "SD":
                    itemEntity.setVerities("Sandals");
                    break;
                case "S":
                    itemEntity.setVerities("Shoes");
                    break;
                case "SL":
                    itemEntity.setVerities("Slippers");
            }

            // set occasion type
            switch (itemDto.getOccasion()){
                case "F":
                    itemEntity.setOccasion("Formal");
                    break;
                case "C":
                    itemEntity.setOccasion("Casual");
                    break;
                case "I":
                    itemEntity.setOccasion("Industrial");
                    break;
                case "S":
                    itemEntity.setOccasion("Sport");
                    break;
            }


            itemRepo.save(itemEntity);
            sizeRepo.save(stockEntity);

            logger.info("Item Save Success");
            logger.info("Item Have Stock Save Success");

        }else{

            logger.info("This Id Have No Supplier");
        }

    }

    @Override
    public void updateItem(String updateItemId, ItemDto updateItemDetailsDto,String supplierId) {
        ItemEntity updateItemEntity = itemRepo.findById(updateItemId).orElse(null);
        SupplierEntity supplierEntity = supplierRepo.findById(supplierId).orElse(null);

        if (updateItemEntity != null && supplierEntity !=null){


            List<SupplierEntity> supplierEntityList = new ArrayList<>();
            List<ItemEntity> itemEntityList = new ArrayList<>();

            supplierEntity.setSupplierCode(supplierId);
            updateItemEntity.setCategory(updateItemDetailsDto.getCategory());
            updateItemEntity.setBuyPrice(updateItemDetailsDto.getBuyPrice());
            updateItemEntity.setExpectedProfit(updateItemDetailsDto.getExpectedProfit());
            updateItemEntity.setItemDesc(updateItemDetailsDto.getItemDesc());
            updateItemEntity.setItemPic(updateItemDetailsDto.getItemPic());
            updateItemEntity.setProfitMargin(updateItemDetailsDto.getProfitMargin());
            updateItemEntity.setStatus(updateItemEntity.getStatus());
            updateItemEntity.setUnitPriceSale(updateItemDetailsDto.getUnitPriceSale());
            supplierEntityList.add(supplierEntity);
            itemEntityList.add(updateItemEntity);

            updateItemEntity.setSupplierEntityList(supplierEntityList);
            supplierEntity.setItemEntityList(itemEntityList);

            // Set Item Type
            switch (updateItemDetailsDto.getItemType()) {
                case "M":
                    updateItemEntity.setItemType("MEN");
                    break;
                case "W":
                    updateItemEntity.setItemType("WOMEN");
            }

            // Set Verities Type
            switch (updateItemDetailsDto.getVerities()){
                case "H":
                    updateItemEntity.setVerities("Heel");
                    break;
                case "F":
                    updateItemEntity.setVerities("Flats");
                    break;
                case "W":
                    updateItemEntity.setVerities("Wedges");
                    break;
                case "FF":
                    updateItemEntity.setVerities("Flip Flops");
                    break;
                case "SD":
                    updateItemEntity.setVerities("Sandals");
                    break;
                case "S":
                    updateItemEntity.setVerities("Shoes");
                    break;
                case "SL":
                    updateItemEntity.setVerities("Slippers");
            }

            // set occasion type
            switch (updateItemDetailsDto.getOccasion()){
                case "F":
                    updateItemEntity.setOccasion("Formal");
                    break;
                case "C":
                    updateItemEntity.setOccasion("Casual");
                    break;
                case "I":
                    updateItemEntity.setOccasion("Industrial");
                    break;
                case "S":
                    updateItemEntity.setOccasion("Sport");
                    break;
            }


            itemRepo.save(updateItemEntity);
            logger.info("Item Details Update Success");
        }else{
            logger.info("This id have no Supplier");
        }
    }

    @Override
    public void deleteItem(String deleteItemId) {
        ItemEntity itemEntity = itemRepo.findById(deleteItemId).orElse(null);

        if (itemEntity != null){
            itemRepo.deleteById(deleteItemId);
        }else {
            logger.info("This Id Have No Item");
        }

    }

    @Override
    public ItemDto searchItem(String searchItemId) {
        ItemEntity itemEntity = itemRepo.findById(searchItemId).orElse(null);

        if (itemEntity != null){
            return dataConvert.itemEntityConvertItemDto(itemEntity);
        }else {
            logger.info("This Id Have No Item");
            return null;
        }


    }

    @Override
    public String getLastItemId() {
        String lastItemId = itemRepo.getLastItemId();

        if (lastItemId == null){
            return "00000";
        }else{
            // Find the index of the first digit in the string
            int index = 0;
            for (int i = 0; i < lastItemId.length(); i++) {
                if (Character.isDigit(lastItemId.charAt(i))) {
                    index = i;
                    break;
                }
            }

            // Remove the first English letters
            String itemIdWithoutLetters = lastItemId.substring(index);


            return itemIdWithoutLetters;
        }

    }

    @Override
    public List<String> selectItemHasAllSizesGet(String selectItemId) {
        List<String> selectedItemHasAllSizes = itemRepo.selectItemHasAllSizesGet(selectItemId);
        return selectedItemHasAllSizes;
    }


}
