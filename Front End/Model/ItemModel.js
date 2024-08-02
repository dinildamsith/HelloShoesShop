export class ItemModel {
    constructor(itemCode, itemDesc, itemPic, Category,verities,occasion,itemType, unitPriceSale, expectedProfit, profitMargin, status, buyPrice, supplierEntityList, stockEntityList) {
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
        this.itemPic = itemPic;
        this.category = Category;
        this.verities = verities;
        this.occasion = occasion;
        this.itemType = itemType;
        this.unitPriceSale = unitPriceSale;
        this.expectedProfit = expectedProfit;
        this.profitMargin = profitMargin;
        this.status = status;
        this.buyPrice = buyPrice;
        this.supplierEntityList =  supplierEntityList;
        this.stockEntityList = stockEntityList;
    }
}


// private String verities;
// private String occasion;
// private String itemType;