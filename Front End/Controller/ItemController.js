import {ItemModel} from "../Model/ItemModel.js";
import {SupplierModel} from "../Model/SupplierModel.js";
import {StockModel} from "../Model/StockModel.js";


var jwtToken = localStorage.getItem("jwtToken")
document.addEventListener('DOMContentLoaded', function() {
    const jwtToken = localStorage.getItem("jwtToken");
    getSuppliersIds(jwtToken)


});


// Get Supplier Ids
const getSuppliersIds = (jwtToken) => {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/supplier/getAllSuppliers",
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
        },
        success: function(data) {
                var selectElement = $('#supplierCodeOption');
                data.forEach(supplier => {
                    selectElement.append(`<option value="${supplier.supplierCode}">${supplier.supplierCode}</option>`);
                });



        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });
};

document.getElementById('itemImageSelect').addEventListener('change', displaySelectedImage);
// Select Image File Get
var image = '';
function displaySelectedImage(event) {


    const selectedFile = event.target.files[0];
    if (!selectedFile) return;
    if (!selectedFile.type.startsWith('image/')) {
        alert('Please select an image file.');
        return;
    }
    const reader = new FileReader();
    reader.onload = e => image=e.target.result

    reader.readAsDataURL(selectedFile);



}

// Item Save
$('#itemBuyBtn').on('click', ()=>{





    var  itemId = $('#itemIdTxt').val();
    var  itemDesc = $('#itemDescTxt').val();
    var itemType = $('#itemTypeOpation').val()
    var occasionType = $('#occasionOpation').val();
    var verities = $('#veritiesOpation').val();
    var  category = $('#itemCategory').val();
    var  size = $('#sizeTxt').val();
    var  qty = $('#quantityTxt').val();
    var  salePrice = $('#salePriceTxt').val();
    var  buyPrice = $('#buyPriceTxt').val();
    var supId = $('#supplierCodeOption').val();
    var expectedProfit = $('#expectedProfitTxt').val();


    if(validate(itemId,"Item Id") && validate(itemDesc,"Item Decription") && validate(itemType,"Item Type") && validate(occasionType,"Occasion Type") && validate(verities,"Verities") && validate(category,"Category") && validate(size,"Size") && validate(qty,"Quantity") && validate(buyPrice,"Buy Price") && validate(salePrice,"Sale Price") && validate(supId,"Supplier Id") && validate(expectedProfit,"Expected Profit") ){

        var form = new FormData();
        form.append("item_code", itemId);
        form.append("item_desc", itemDesc);
        form.append("item_pic",  image);
        form.append("category", category);
        form.append("itemType",itemType);
        form.append("occasion",occasionType);
        form.append("verities",verities);
        form.append("salePrice", salePrice);
        form.append("expectedProfit", expectedProfit);
        form.append("profitMargin", 0);
        form.append("buyPrice", buyPrice);


        const sendAJAX = (itemDetails,jwtToken) => {
            $.ajax({
                type: "POST",
                url : "http://localhost:8080/shoes/item/save/" + supId + "/" + size + "/" + qty,
                processData: false,
                mimeType: "multipart/form-data",
                contentType: false,
                data: form,
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
                },
                success: function(data) {
                    $("#item_Table").empty();
                    getAllItemSetTabelSendAJAX(jwtToken)
                    Swal.fire({
                        title: "Item Save Success",
                        icon: "success"
                    });
                },
                error: function(xhr, status, error) {
                    alert("Failed");
                }
            });
        };
        sendAJAX(form, jwtToken);

    }

})



// Item Update
$('#itemUpdateBtn').on('click', ()=>{
    var  itemId = $('#itemIdTxt').val();
    var  itemDesc = $('#itemDescTxt').val();
    var  category = $('#itemCategory').val();
    var itemType = $('#itemTypeOpation').val()
    var occasionType = $('#occasionOpation').val();
    var verities = $('#veritiesOpation').val();
    var  salePrice = $('#salePriceTxt').val();
    var  buyPrice = $('#buyPriceTxt').val();
    var supId = $('#supplierCodeOption').val();
    var expectedProfit = $('#expectedProfitTxt').val();


    if(validate(itemId,"Item Id") && validate(itemDesc,"Item Description") && validate(itemType,"Item Type") && validate(occasionType,"Occasion Type") && validate(verities,"Verities") && validate(category,"Category")  && validate(buyPrice,"Buy Price") && validate(salePrice,"Sale Price") && validate(supId,"Supplier Id") && validate(expectedProfit,"Expected Profit") && validate(image,"Item Image")) {


        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: "btn btn-success m-1",
                cancelButton: "btn btn-danger"
            },
            buttonsStyling: false
        });
        swalWithBootstrapButtons.fire({
            title: "Do you want to Update this Item ?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Yes, Update it!",
            cancelButtonText: "No, cancel!",
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {

                var form = new FormData();
                form.append("item_desc", itemDesc);
                form.append("item_pic",  image);
                form.append("category", category);
                form.append("itemType",itemType);
                form.append("occasion",occasionType);
                form.append("verities",verities);
                form.append("salePrice", salePrice);
                form.append("expectedProfit", expectedProfit);
                form.append("buyPrice", buyPrice);


                const sendAJAX = (itemDetails,jwtToken) => {
                    $.ajax({
                        type: "PUT",
                        url : "http://localhost:8080/shoes/item/update/" + itemId + "/" + supId,
                        processData: false,
                        mimeType: "multipart/form-data",
                        contentType: false,
                        data: form,
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
                        },
                        success: function(data) {
                            $("#item_Table").empty();
                            getAllItemSetTabelSendAJAX(jwtToken)
                            swalWithBootstrapButtons.fire({
                                title: "Item Update Success !",
                                icon: "success"
                            });
                        },
                        error: function(xhr, status, error) {
                            Swal.fire({
                                title: "Sorry Sir !!",
                                text:  " Your account does not have permission to Update the Item details!",
                                icon: "error"
                            });
                        }
                    });
                };
                sendAJAX(form, jwtToken);

            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire({
                    title: "Item Update Cancelled !",
                    icon: "error"
                });
            }
        });


    }

})


// Item Delete
$('#itemDeleteBtn').on('click', ()=>{
    var  itemId = $('#itemIdTxt').val();


    if (validate(itemId,"Delete Item Id")){

        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: "btn btn-success m-1",
                cancelButton: "btn btn-danger"
            },
            buttonsStyling: false
        });
        swalWithBootstrapButtons.fire({
            title: "Do you want to Delete this Item ?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Yes, Delete it!",
            cancelButtonText: "No, cancel!",
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {

                const sendAJAX = (jwtToken) => {
                    $.ajax({
                        type: "DELETE",
                        url: "http://localhost:8080/shoes/item/delete/"+ itemId,
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
                        },
                        success: function(data) {
                            $("#item_Table").empty();
                            getAllItemSetTabelSendAJAX(jwtToken)

                            swalWithBootstrapButtons.fire({
                                title: "Item Delete Success !",
                                icon: "success"
                            });


                        },
                        error: function(xhr, status, error) {
                            Swal.fire({
                                title: "Sorry Sir !!",
                                text:  " Your account does not have permission to delete the Item details!",
                                icon: "error"
                            });
                        }
                    });
                };
                sendAJAX(jwtToken);

            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire({
                    title: "Item Delete Cancelled !",
                    icon: "error"
                });
            }
        });


    }
})

// Item Search
$('#itemSearchBtn').on('click', ()=>{
    var  itemId = $('#itemSearchTxt').val();

    // $('#itemTypeOpation').prop('disabled', true);
    // $('#occasionOpation').prop('disabled', true);
    // $('#veritiesOpation').prop('disabled', true);
    // $('#quantityTxt').prop('disabled', true);


    if (validate(itemId,"Search Item Id")){

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/shoes/item/search/"+itemId,
            contentType: "application/json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
            },
            success: function(data) {
                    if (data === ""){
                        Swal.fire({
                            title: "This Id Have No Item",
                            icon: "info"
                        });
                    }else {

                        const imageElement = document.getElementById('selectImage');
                        imageElement.src = data.itemPic;
                        $('#itemIdTxt').val(data.itemCode);
                        $('#itemDescTxt').val(data.itemDesc);
                        $('#itemCategory').val(data.category);
                        $("#itemTypeOpation").val(data.itemType.charAt(0).toUpperCase())
                        $('#occasionOpation').val(data.occasion.charAt(0).toUpperCase())
                        if (data.verities === 'Flip Flops'){
                            $('#veritiesOpation').val('FF')
                        }else if(data.verities === "Sandals"){
                            $('#veritiesOpation').val('SD')
                        }else if(data.verities === "Slippers"){
                            $('#veritiesOpation').val('SL')
                        }else {
                            $('#veritiesOpation').val(data.verities.charAt(0).toUpperCase())
                        }
                        $('#sizeTxt').val(data.stockEntityList[0].itemSize);
                        $('#quantityTxt').val(data.stockEntityList[0].qty);
                        $('#salePriceTxt').val(data.unitPriceSale);
                        $('#buyPriceTxt').val(data.buyPrice);
                        $('#supplierCodeOption').val(data.supplierEntityList[0].supplierCode);
                        $('#expectedProfitTxt').val(data.expectedProfit);
                        $('#statusTxt').val(data.status);
                        $('#profitMarginTxt').val(data.profitMargin);


                    }



                //
                // $('#itemUpdateBtn').css('display','block')
                // $('#itemDeleteBtn').css('display','block')
                // $('#itemBuyBtn').css('display','none')


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



//item Table Set Data
const getAllItemSetTabelSendAJAX = (jwtToken) => {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/item/allItems",
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
        },
        success: function(data) {
            data.forEach(items => {
                var newRow = "<tr><th scope='row'>" + items.itemCode + "</th><td>" + items.itemDesc + "</td><td>" + items.category + "</td><td>" + items.unitPriceSale + "</td><td>" + items.buyPrice + "</td><td>" + items.supplierEntityList[0].supplierCode +  "</td></tr>";
                $("#item_Table").append(newRow);
            });

        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });
};

var itemTypeElement = document.getElementById("itemTypeOpation");
var occasionElement = document.getElementById("occasionOpation");
var veritiesElement = document.getElementById("veritiesOpation");


itemTypeElement.addEventListener("change", handleChange);
occasionElement.addEventListener("change", handleChange);
veritiesElement.addEventListener("change", handleChange);

function handleChange() {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/item/lastId",
        contentType: "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
        },
        success: function (data) {
       
                let incrementedId = parseInt(data, 10) + 1;
                var id = incrementedId.toString().padStart(data.length, '0');
                var createItemId = idFirstLetters+id
                $('#itemIdTxt').val(createItemId);


        },
        error: function (xhr, status, error) {
            alert("Failed");
        }
    });


    var itemType = itemTypeElement.value;
    var occasionType = occasionElement.value;
    var veritiesType = veritiesElement.value;

    var idFirstLetters =occasionType+veritiesType+itemType

}


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
    const jwtToken = localStorage.getItem("jwtToken");
    getAllItemSetTabelSendAJAX(jwtToken)
    // $('#itemUpdateBtn').css('display','none')
    // $('#itemDeleteBtn').css('display','none')
    checkHaveItemQtyAndSetNotify()
});


