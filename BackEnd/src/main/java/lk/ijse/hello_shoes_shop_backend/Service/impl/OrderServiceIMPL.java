package lk.ijse.hello_shoes_shop_backend.Service.impl;

import jakarta.persistence.Id;
import lk.ijse.hello_shoes_shop_backend.Dao.*;
import lk.ijse.hello_shoes_shop_backend.Dto.OrderDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ReturnDto;
import lk.ijse.hello_shoes_shop_backend.Service.DateServices;
import lk.ijse.hello_shoes_shop_backend.Service.OrderService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.*;
import lk.ijse.hello_shoes_shop_backend.enums.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    DataConvert dataConvert;
    @Autowired
    ReturnRepo returnRepo;
    @Autowired
    SizeRepo sizeRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    DateServices dateServices;

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public String saveOrder(OrderDto orderDto) {

        OrderEntity orderEntity = dataConvert.orderDtoConvertOrderEntity(orderDto);
        orderEntity.setOrderStatus("ORDER CONFIRM");
        ItemEntity itemEntity = new ItemEntity();

        String customerCode = orderDto.getCustomerDetails().getCustomerCode();
        CustomerEntity customerEntity = customerRepo.findById(customerCode).orElse(null);
        int totalPoints = customerEntity.getTotalPoints();

        int qty = orderDto.getQty();
        double unitPrice = orderDto.getUnitPrice();
        double orderTotal = qty*unitPrice;

        orderEntity.setTotal(orderTotal);
       if (800<orderTotal){
            orderEntity.setPoints(1);
            totalPoints+=1;
            customerEntity.setTotalPoints(totalPoints);


            if (200 < customerEntity.getTotalPoints()){
                customerEntity.setLevel(Level.GOLD);
            }else if (100 < customerEntity.getTotalPoints()){
                customerEntity.setLevel(Level.SILVER);
            }else if (50 < customerEntity.getTotalPoints()){
                customerEntity.setLevel(Level.BRONZE);
            }else if (50 > customerEntity.getTotalPoints()){
                customerEntity.setLevel(Level.NEW);
            }


//            if (50<customerEntity.getTotalPoints()){
//                customerEntity.setLevel(Level.BRONZE);
//            }else if (100 < customerEntity.getTotalPoints()){
//                customerEntity.setLevel(Level.SILVER);
//            }else if (200 <customerEntity.getTotalPoints()){
//                customerEntity.setLevel(Level.GOLD);
//            }else if (50 > customerEntity.getTotalPoints()){
//                customerEntity.setLevel(Level.NEW);
//            }
            customerRepo.save(customerEntity);
       }
        ItemEntity itemEntity2 = orderDto.getBuyItem().get(0);
        StockEntity stockEntity = sizeRepo.getItemQty(itemEntity2.getItemCode(), String.valueOf(orderDto.getSize()));

        if (0 < Integer.parseInt(stockEntity.getQty())){

            System.out.println(stockEntity.getQty());
            int i = Integer.parseInt(stockEntity.getQty());
            if (i < orderDto.getQty()){
                    System.out.println("Sorry This Not Available This Quantity in stock");
                    return "Sorry This Not Available This Quantity in stock";
            }else{
                int x = i-orderDto.getQty();
                stockEntity.setQty(String.valueOf(x));

                sizeRepo.save(stockEntity);

                ItemEntity itemEntity1 = orderDto.getBuyItem().get(0);
                itemEntity.setItemCode(itemEntity1.getItemCode());

                List<OrderEntity> orderEntityList = new ArrayList<>();
                List<ItemEntity> itemEntityList = new ArrayList<>();

                orderEntityList.add(orderEntity);
                itemEntityList.add(itemEntity);

                orderEntity.setBuyItem(itemEntityList);
                itemEntity.setOrder(orderEntityList);


                customerEntity.setRecentPurchaseDate(orderDto.getPurchaseDate());

                customerRepo.save(customerEntity);
                orderRepo.save(orderEntity);
                return "Order Placed";
            }

        }else{
            System.out.println("This Item Quantity Unavailable Stock");
            return "This Item Quantity Unavailable Stock";
        }


    }

    @Override
    public String returnOrder(ReturnDto returnDto) {

        OrderEntity orderEntity = orderRepo.findById(returnDto.getOrderEntity().getOrderCode()).orElse(null);
        Date orderPurchaseDate = orderEntity.getPurchaseDate();

        Date currentDate = new Date();
        LocalDate orderReturnLstDay = dateServices.orderLastReturnDay(orderPurchaseDate);
        LocalDate todayDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        if (orderReturnLstDay.isBefore(todayDate)) {

            return "Sorry Customer This Order Can't return you item return last day missing !!1";

        } else{

            String orderStatus = orderEntity.getOrderStatus();

            if (orderStatus.equals("ORDER CONFIRM")){

                returnRepo.save(dataConvert.returnDtoConvertReturnEntity(returnDto));

                OrderEntity placeOrde = orderRepo.getReferenceById(returnDto.getOrderEntity().getOrderCode());
                System.out.println(placeOrde.getPurchaseDate());

                placeOrde.setOrderStatus("ORDER RETURN");
                orderRepo.save(placeOrde);

                StockEntity itemQty = sizeRepo.getItemQty(returnDto.getItemId(), returnDto.getSize());
                int qty = Integer.parseInt(itemQty.getQty());
                int updateQty = qty + Integer.parseInt(returnDto.getQty());
                itemQty.setQty(String.valueOf(updateQty));
                sizeRepo.save(itemQty);
                return "Order Returned";

            }else {
                return "Sorry Sir You Can order Return One Chanes Only";
            }

        }

    }

    @Override
    public String getLastOrderId() {
        String lastOrderId = orderRepo.getLastOrderId();
        if (lastOrderId == null || lastOrderId.isEmpty()) {
            return "O0001";
        } else {
            String numericPart = lastOrderId.replaceAll("\\D", ""); // Remove non-numeric characters
            int x = Integer.parseInt(numericPart);
            x++; // Increment the numeric part
            String nextOrderId = String.format("O%04d", x); // Format the incremented value back into the order ID format
            return nextOrderId;
        }

    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderEntity> allOrder = orderRepo.findAll();
        return dataConvert.ordeEntityListConvertOrdeDtoList(allOrder);
    }

    @Override
    public String getNextReturnId() {
        List<ReturnEntity> allReturnData = returnRepo.findAll();
        if (allReturnData.isEmpty()){
            return "R1";
        }else{
            int lastReturnId = 0;
            for (ReturnEntity returnEntity :allReturnData) {
                lastReturnId= Integer.parseInt(returnEntity.getReturnId().substring(1));
            }
            lastReturnId+=1;
            return "R"+lastReturnId;
        }
    }

    @Override
    public OrderDto searchOrder(String searchOrderId) {
        OrderEntity orderEntity = orderRepo.findById(searchOrderId).orElse(null);
        if (orderEntity != null){
            return dataConvert.orderEntityConvertOrderDto(orderEntity);
        }else{
            return null;
        }

    }

    @Override
    public Double totalSaleGet(String date) {
        List<OrderEntity> allOrders = orderRepo.findAll();
        List<Double> allOrderTotal = orderRepo.getAllOrderTotal(date);

        Double totalSale = 0.00;
        Double totalProfit = 0.00;

        for (Double price :allOrderTotal) {
            totalSale+=price;
        }
        return totalSale;
    }

    @Override
    public int mostSaleItemQtyGet(Date date) {

        String mostSaleItemDesc = orderRepo.findMostSaleItemDesc(date);
        List<OrderEntity> byPurchaseDate = orderRepo.findByPurchaseDate(date);

        int totalQty = 0;

        for (OrderEntity orderEntity:byPurchaseDate) {

            if (mostSaleItemDesc.equals(orderEntity.getItemDesc())){
               totalQty+=orderEntity.getQty();
            }

        }

        return totalQty;
    }

    @Override
    public String mostSaleItemImgGet(Date date) {


        String mostSaleItemDesc = orderRepo.findMostSaleItemDesc(date);

        if (mostSaleItemDesc == null){
            return null;
        }else {

            ItemEntity byItemDesc = itemRepo.findByItemDesc(mostSaleItemDesc);
            return byItemDesc.getItemPic();
        }
    }

    @Override
    public List<OrderDto> branchWiseOrderDetailsGet(String branch) {
        List<OrderEntity> allOrders = orderRepo.findAll();

        List<OrderDto> branchWiseOrders = new ArrayList<>();

        for (OrderEntity all:allOrders) {
            EmployeeEntity employeeEntity = employeeRepo.findById(all.getEmployeeEntity().getEmployeeCode()).orElse(null);

            if (branch.equals(employeeEntity.getAttachedBranch())){
                branchWiseOrders.add(dataConvert.orderEntityConvertOrderDto(all));
            }
        }

        return branchWiseOrders;
    }

    @Override
    public List<OrderDto> employeeSaleAllOrdersGet(String empMail) {
        String employeeCode = employeeRepo.findByEmail(empMail).getEmployeeCode();
        List<OrderEntity> allOrders = orderRepo.findAll();

        List<OrderDto> employeeAllSale = new ArrayList<>();

        for (OrderEntity all :allOrders) {

            if (all.getEmployeeEntity().getEmployeeCode().equals(employeeCode)){
                employeeAllSale.add(dataConvert.orderEntityConvertOrderDto(all));
            }

        }


        return employeeAllSale;
    }

    //TODO
//
//    @Override
//    public String mostSaleItemGet() {
//      //  String mostSaleItemName = orderRepo.mostSaleItemGet(date);
//
//
//     //   ItemEntity kidsShoes = itemRepo.findByItemDesc("Kids Shoes");
//
////        ItemEntity kidsShoes = itemRepo.getItemDescBy("Kids Shoes");
////
////        System.out.println(kidsShoes);
//
//        return "";
//    }
}
