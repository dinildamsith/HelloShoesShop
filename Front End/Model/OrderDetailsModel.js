export class OrderDetailsModel{
    constructor(orderId,buyDate,custId,itemCode,size,unitPrice,qty,tot,payMethod,status,enpId) {
        this.orderCode = orderId;
        this.purchaseDate = buyDate;
        this.customerDetails = custId;
        this.buyItem = itemCode;
        this.size = size;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = tot;
        this.paymentMethod = payMethod;
        this.orderStatus = status;
        this.employeeEntity = enpId;
    }
}