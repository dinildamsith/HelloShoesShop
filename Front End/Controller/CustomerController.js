import {CustomerModel} from "../Model/CustomerModel.js";


let jwtToken = localStorage.getItem("jwtToken")


// Save Customer
$('#customerSaveBtn').on('click', ()=>{
    var customerId = $('#customerIdTxt').val();
    var customerName = $('#customerNameTxt').val();
    var contact= $('#custContactTxt').val();
    var mail = $('#custEmailTxt').val();
    var gender = $('#customerGenderOpation').val();
    var address1 = $('#custAddress1').val();
    var address2 = $('#custAddress2').val();
    var bod = $('#custBirthday').val();
    var joinDate = $('#custJoinDate').val();
    var level = $('#custLevel').val();


    if (validate(customerId,"Customer Id") && validate(customerName,"Customer Name") && validate(contact,"Contact") && validate(mail,"Mail") && validate(gender,"Gender") && validate(address1,"Address 1") && validate(address2,"Address 2") && validate(bod,"Birthday") && validate(joinDate,"Join Date") && validate(level,"Level")){

        var customerDetails = new CustomerModel(customerId,customerName,gender,joinDate,level,bod,address1,address2,contact,mail);
        var customerDetailsJson = JSON.stringify(customerDetails);


        const sendAJAX = (customerDetails,jwtToken) => {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/shoes/customer/save",
                contentType: "application/json",
                data: customerDetails,
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
                },
                success: function(data) {
                    $("#customer_Table").empty();
                    getAllCustomerSendAJAX(jwtToken)
                    clearTextFields();
                    $('#custLevel').val('BRONZE')
                    Swal.fire({
                        title: "Customer Save Success",
                        icon: "success"
                    });
                },
                error: function(xhr, status, error) {
                    alert("Failed");
                }
            });
        };
        sendAJAX(customerDetailsJson, jwtToken);



    }


})


// Update Customer
$('#custUpdateBtn').on('click', ()=>{
    var customerId = $('#customerIdTxt').val();
    var customerName = $('#customerNameTxt').val();
    var contact= $('#custContactTxt').val();
    var mail = $('#custEmailTxt').val();
    var gender = $('#customerGenderOpation').val();
    var address1 = $('#custAddress1').val();
    var address2 = $('#custAddress2').val();
    var bod = $('#custBirthday').val();
    var joinDate = $('#custJoinDate').val();
    var level = $('#custLevel').val();



    if (validate(customerId,"Customer Id") && validate(customerName,"Customer Name") && validate(contact,"Contact") && validate(mail,"Mail") && validate(gender,"Gender") && validate(address1,"Address 1") && validate(address2,"Address 2") && validate(bod,"Birthday") && validate(joinDate,"Join Date") && validate(level,"Level")){

        var customerDetails = new CustomerModel(customerId,customerName,gender,joinDate,level,bod,address1,address2,contact,mail);

        var customerDetailsJson = JSON.stringify(customerDetails);

        console.log(customerDetailsJson)
        const sendAJAX = (customerDetails,jwtToken) => {
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/shoes/customer/update/"+ customerId,
                contentType: "application/json",
                data: customerDetails,
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
                },
                success: function(data) {
                    $("#customer_Table").empty();
                    getAllCustomerSendAJAX(jwtToken)
                    clearTextFields();
                    $('#custLevel').val('BRONZE')
                    Swal.fire({
                        title: "Customer Update Success",
                        icon: "info"
                    });
                    $('#custUpdateBtn').css('display','none')
                    $('#custDeleteBtn').css('display','none')
                    $('#customerSaveBtn').css('display','block')
                },
                error: function(xhr, status, error) {
                    alert("Failed");
                }
            });
        };
        sendAJAX(customerDetailsJson, jwtToken);

    }

})


// Delete Customer
$('#custDeleteBtn').on('click', ()=>{

    var customerId = $('#customerIdTxt').val();

    if (validate(customerId,"Customer Id")){

        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: "btn btn-success m-1",
                cancelButton: "btn btn-danger"
            },
            buttonsStyling: false
        });
        swalWithBootstrapButtons.fire({
            title: "Do you want to Remove this Customer ?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, cancel!",
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {

                $.ajax({
                    type: "DELETE",
                    url: "http://localhost:8080/shoes/customer/delete/"+ customerId,
                    contentType: "application/json",
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
                    },
                    success: function(data) {
                        $("#customer_Table").empty();
                        getAllCustomerSendAJAX(jwtToken)
                        clearTextFields();
                        $('#custLevel').val('BRONZE')
                        
                       swalWithBootstrapButtons.fire({
                         title: "Customer Deleted Success !",
                          icon: "success"
                         });

                    },
                    error: function(xhr, status, error) {
                        Swal.fire({
                            title: "Sorry Sir !!",
                            text:  " Your account does not have permission to delete the customer details!",
                            icon: "error"
                        });
                    }
                });

            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire({
                    title: "Customer Deleted Cancelled !",
                    icon: "error"
                });
            }
        });

    }

})


// Customer Search
$('#customerSearchBtn').on('click', ()=>{
    var customerId = $('#custSearchTxt').val();


    if (validate(customerId,"Customer Id")){
        const sendAJAX = (jwtToken) => {
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/shoes/customer/search/"+ customerId,
                contentType: "application/json",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
                },
                success: function(data) {
                    if (!data){
                        Swal.fire({
                            title: "Sorry This Id Have No Customer !",
                            icon: "info"
                        });
                    }else {
                        $("#customerIdTxt").val(data.customerCode);
                        $("#customerNameTxt").val(data.customerName);
                        $("#custContactTxt").val(data.contactNumber);
                        $("#custEmailTxt").val(data.email);
                        $("#customerGenderOpation").val(data.customerGender);
                        $("#custAddress1").val(data.addressLine1);
                        $("#custAddress2").val(data.addressLine2);
                        $("#custBirthday").val(data.birthDay);
                        $("#custJoinDate").val(data.customerJoinDate);
                        $("#custLevel").val(data.level);
                        $("#totalPoints").val(data.totalPoints);
                        $("#recentPurchaseDate").val(data.recentPurchaseDate);

                        $('#custUpdateBtn').css('display','block')
                        $('#custDeleteBtn').css('display','block')
                        $('#customerSaveBtn').css('display','none')
                    }
                },
                error: function(xhr, status, error) {
                    alert("Failed");
                }
            });
        };
        sendAJAX(jwtToken);
    }


})

// Get All Customer
const getAllCustomerSendAJAX = (jwtToken) => {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/customer/getAllCustomer",
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
        },
        success: function(data) {
            console.log(data)
            data.forEach(customer => {
                var newRow = "<tr><th scope='row'>" + customer.customerCode + "</th><td>" + customer.customerName + "</td><td>" + customer.contactNumber + "</td><td>" + customer.email + "</td><td>" + customer.customerGender + "</td><td>" + customer.addressLine1 + "</td><td>" + customer.addressLine2 + "</td><td>" + customer.birthDay + "</td><td>" + customer.customerJoinDate + "</td><td>" + customer.level + "</td><td>" + customer.totalPoints + "</td><td>" + customer.recentPurchaseDate  + "</td></tr>";
                $("#customer_Table").append(newRow);
            });

        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });
};

//row click and get values text fields
$("#customer_Table").on("click","tr", function (){
    let id = $(this).find("th");
    let data = $(this).find("td");

    $("#customerIdTxt").val(id.eq(0).text());
    $("#customerNameTxt").val(data.eq(0).text());
    $("#custContactTxt").val(data.eq(1).text());
    $("#custEmailTxt").val(data.eq(2).text());
    $("#customerGenderOpation").val(data.eq(3).text());
    $("#custAddress1").val(data.eq(4).text());
    $("#custAddress2").val(data.eq(5).text());
    $("#custBirthday").val(data.eq(6).text());
    $("#custJoinDate").val(data.eq(7).text());
    $("#custLevel").val(data.eq(8).text());
    $("#totalPoints").val(data.eq(9).text());
    $("#recentPurchaseDate").val(data.eq(10).text());


    $('#custUpdateBtn').css('display','block')
    $('#custDeleteBtn').css('display','block')
    $('#customerSaveBtn').css('display','none')

 
});


// Text Fields Clear 
function clearTextFields() {
    $("#customerIdTxt").val("");
    $("#customerNameTxt").val("");
    $("#custContactTxt").val("");
    $("#custEmailTxt").val("");
    $("#customerGenderOpation").val("");
    $("#custAddress1").val("");
    $("#custAddress2").val("");
    $("#custBirthday").val("");
    $("#custJoinDate").val("");
    $("#custLevel").val("");
    $("#totalPoints").val("");
    $("#recentPurchaseDate").val("");
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
    getAllCustomerSendAJAX(jwtToken)
    $('#custLevel').val('NEW')


    $('#custUpdateBtn').css('display','none')
    $('#custDeleteBtn').css('display','none')



});

