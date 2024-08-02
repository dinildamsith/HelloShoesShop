export class OrderModel{
    constructor(orderCode,purchaseDate,customerName,itemDesc,size,unitPrice,qty,total,paymentMethod,points,cashierName,orderStatus,customerDetails,buyItem,employeeEntity,returnEntity) {
        this.orderCode = orderCode;
        this.purchaseDate = purchaseDate;
        this.customerName = customerName;
        this.itemDesc = itemDesc;
        this.size = size;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.points = points;
        this.cashierName = cashierName;
        this.orderStatus = orderStatus;
        this.customerDetails = customerDetails;
        this.buyItem = buyItem;
        this.employeeEntity = employeeEntity;
        this.returnEntity = returnEntity;

    }
}