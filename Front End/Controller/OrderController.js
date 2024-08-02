import {OrderModel} from "../Model/OrderModel.js";

const getAllCustomerSendAJAX = () => {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/customer/getAllCustomer",
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
        },
        success: function(data) {

            var dropdown = $('#customerCodeOption');
            data.forEach(customer => {
                dropdown.append($('<option></option>').attr('value', customer.customerCode).text(customer.customerCode));
            });
        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });
};

// Select Customer Name Set Text Field
$('#customerCodeOption').change(function() {

    var selectCustomerId = $('#customerCodeOption').val()

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/customer/search/"+ selectCustomerId,
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
        },
        success: function(data) {
           $('#customerNameTxt').val(data.customerName)
        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });

});

// Get All Items Ids
const getAllItems = () => {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/item/allItems",
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
        },
        success: function(data) {
            data.forEach(items => {
                $('#itemCodeOption').append($('<option></option>').attr('value', items.itemCode).text(items.itemCode));
            });

        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });
};

// Select Item Id after item sizE set
$('#itemCodeOption').change(function() {

    var selectItemId = $('#itemCodeOption').val()
    console.log(selectItemId)
    // Select Item After Item Have All Sizes Set Item Option
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/item/selectItemSizesGet/"+ selectItemId,
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
        },
        success: function(data) {
            data.forEach(items => {

                $('#sizeOption').append($('<option></option>').attr('value', items).text(items));
            });
        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });

});

$('#itemCodeOption').change(function (){
    var selectItemId = $('#itemCodeOption').val()
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/item/search/"+ selectItemId,
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
        },
        success: function(data) {
            $('#itemDescriptionTxt').empty
            $('#itemDescriptionTxt').val(data.itemDesc)
            $('#unitPriceTxt').val(data.unitPriceSale)
        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });
})

// Purchase Date Set
function purchaseDateSet(){
var currentDate = new Date();
var year = currentDate.getFullYear();
var month = (currentDate.getMonth() + 1 < 10) ? '0' + (currentDate.getMonth() + 1) : (currentDate.getMonth() + 1);
var day = (currentDate.getDate() < 10) ? '0' + currentDate.getDate() : currentDate.getDate();
var formattedDate = year + '-' + month + '-' + day;
$('#purchaseDateTxt').val(formattedDate);
}




// Add Cart
let total = 0;
$('#addCartBtn').on('click', ()=>{
    var itemCode = $('#itemCodeOption').val();
    var itemName = $('#itemDescriptionTxt').val();
    var unitPrice = $("#unitPriceTxt").val();
    var qty = $('#quantityTxt').val();

    var newRow = "<tr><th scope='row'>" + itemCode + "</th><td>" + itemName + "</td><td>" + unitPrice + "</td><td>" + qty + "</td></tr>";
    $("#cart_Table").append(newRow);

    // Update the total
    total += unitPrice * qty;
    $('#totalLabel').text("Rs "+total+".00")


})


var cashInput = document.getElementById("cashTxt");// Get the input element with the id "cash"

cashInput.addEventListener("input", (event) => {// Add an input event listener to the input element
    let cash = $("#cashTxt").val(); // Parse the cash value as a float
    console.log(cash)
    let balance = cash - total;
    console.log(balance)

    // Update the content of the "balance" element
    $("#balanceLbl").text("Rs " +balance +".00");
});




// Order Purchase
$('#orderBuyBtn').on('click', ()=>{

    var orderId = $('#ordrCodeTxt').val();
    var date = $('#purchaseDateTxt').val();
    var customerCode = $('#customerCodeOption').val();
    var customerName = $('#customerNameTxt').val();
    var itemCode = $('#itemCodeOption').val();
    var itemName = $('#itemDescriptionTxt').val();
    var size = $('#sizeOption').val();
    var qty = $('#quantityTxt').val();
    var unitPrice = $("#unitPriceTxt").val();
    var empId = $('#orderSaleEmpIdTxt').val();
    var empName = $('#empNameTxt').val();
    var payMethod = $('#paymentMethod').val()


    if (validate(orderId,"Order Id") && validate(date,"Date") && validate(customerCode,"Customer Code") && validate(customerName,"Customer Name") && validate(itemCode,"Item Code") && validate(itemName,"Item Name") && validate(size,"Size") && validate(qty,"Quantity") && validate(unitPrice,"Unit Price") && validate(empId,"Employee Id") && validate(empName,"Employee Name")&& validate(payMethod,"Select Payment Method")){


        var orderData = JSON.stringify({
            "orderCode": orderId,
            "purchaseDate": date,
            "customerName": customerName,
            "itemDesc": itemName,
            "size" : parseInt(size),
            "unitPrice": unitPrice,
            "qty": parseInt(qty),
            "paymentMethod": payMethod,
            "cashierName": empName,
            "customerDetails": {
                "customerCode": customerCode
            },
            "buyItem": [
                {
                    "itemCode": itemCode
                }
            ],
            "order": [
                {
                    "orderCode": orderId
                }
            ],
            "employeeEntity": {
                "employeeCode": empId
            }
        });



        $.ajax({
            type: "POST",
            url: "http://localhost:8080/shoes/order/save",
            contentType: "application/json",
            data: orderData,
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
            },
            success: function(data) {
                console.log(data)
                generateNewOrderId()
                if (data === 'Sorry This Not Available This Quantity in stock'){

                    Swal.fire({
                        icon: 'info',
                        title: data
                    });

                }else if ( data === 'This Item Quantity Unavailable Stock'){

                    Swal.fire({
                        icon: 'info',
                        title: data
                    });

                }else if (data === 'Order Placed'){

                    Swal.fire({
                        icon: 'success',
                        title: data
                    });

                }

            },
            error: function(xhr, status, error) {
                alert("Failed");
            }
        });


    }


})

function checkHaveItemQtyAndSetNotify(){

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/stock/sendInfoItemQty",
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
        },
        success: function(data) {
            data.forEach(massage => {
                toastr["warning"](massage);
            });

        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });

}


// next Order Id Generate
function generateNewOrderId(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/order/newOrderId",
        contentType: "application/json",
        data: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
        },
        success: function(data) {
            $('#ordrCodeTxt').val(data)
        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });
}

//
$('#paymentMethod').change(function (){
    var selectPayMethod = $('#paymentMethod').val();

    if(selectPayMethod === "CREDITCARD"){
           $('#cashTxt').css('display','none')
           $('#cashLbl').css('display','none')
           $('#creditCardTxt').css('display','block')
           $('#cardLbl').css('display','block')
    }else{
        $('#cashTxt').css('display','block')
        $('#cashLbl').css('display','block')
        $('#creditCardTxt').css('display','none')
        $('#cardLbl').css('display','none')
    }

})



document.addEventListener('DOMContentLoaded', () => {
    const creditCardInput = document.getElementById('creditCardTxt');

    creditCardInput.addEventListener('input', (event) => {
        const input = event.target.value;
        // Remove all non-numeric characters
        const numericInput = input.replace(/\D/g, '');
        // Limit the input to 10 digits
        if (numericInput.length > 10) {
            creditCardInput.value = numericInput.slice(0, 10);
        } else {
            creditCardInput.value = numericInput;
        }
    });
});



// Validation Function
function validate(value, field_name){
    if (!value){
        Swal.fire({
            icon: 'warning',
            title: `Please enter the ${field_name}!`
        });
        return false;
    }
    return true;
}



document.addEventListener('DOMContentLoaded', function() {
    $('#cashTxt').css('display','none')
    $('#cashLbl').css('display','none')
    $('#creditCardTxt').css('display','none')
    $('#cardLbl').css('display','none')
    getAllCustomerSendAJAX()
    purchaseDateSet();
    getAllItems();
    generateNewOrderId()
    checkHaveItemQtyAndSetNotify();
});
