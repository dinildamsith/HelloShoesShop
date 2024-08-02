export class ReturnModel{
    constructor(returnId,itemId,itemDes,size,qty,returnDate,orderEntity) {
     this.returnId = returnId;
     this.itemId = itemId;
     this.itemDes = itemDes;
     this.size = size;
     this.qty = qty;
     this.returnDate =returnDate;
     this.orderEntity = orderEntity;
    }
}