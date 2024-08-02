export function employeeWiseGetAllOrder(empMail) {
    const sendAJAX = () => {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/shoes/order/employeeSaleOrders/"+empMail,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
            },
            success: function (data) {
                $('#orderDetailsTable').empty();
                data.forEach(order => {
                    var newRow = "<tr><th scope='row'>" + order.orderCode + "</th><td>" + order.purchaseDate + "</td><td>" + order.customerDetails.customerCode + "</td><td>" + order.buyItem[0].itemCode + "</td><td>" + order.size + "</td><td>" + order.unitPrice + "</td><td>" + order.qty + "</td><td>" + order.total + "</td><td>" + order.paymentMethod + "</td><td>" + order.orderStatus + "</td><td>" + order.employeeEntity.employeeCode + "</td></tr>";
                    $("#orderDetailsTable").append(newRow);
                });

            },
            error: function (xhr, status, error) {
                console.log("hii")
            }
        });
    };
    sendAJAX()
}







export function getAllOrder() {
    const sendAJAX = () => {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/shoes/order/getAllOrders",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
            },
            success: function (data) {
                $('#orderDetailsTable').empty();
                data.forEach(order => {
                    var newRow = "<tr><th scope='row'>" + order.orderCode + "</th><td>" + order.purchaseDate + "</td><td>" + order.customerDetails.customerCode + "</td><td>" + order.buyItem[0].itemCode + "</td><td>" + order.size + "</td><td>" + order.unitPrice + "</td><td>" + order.qty + "</td><td>" + order.total + "</td><td>" + order.paymentMethod + "</td><td>" + order.orderStatus + "</td><td>" + order.employeeEntity.employeeCode + "</td></tr>";
                    $("#orderDetailsTable").append(newRow);

                });

            },
            error: function (xhr, status, error) {
                console.log("hii")
            }
        });
    };
    sendAJAX()
}


    $('#orderBranchOption').change(function() {

        var selectBranch = $('#orderBranchOption').val()


        $.ajax({
            type: "GET",
            url: "http://localhost:8080/shoes/order/branchWiseOrderDetailsGet/"+selectBranch,
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
            },
            success: function(data) {
                console.log(data)
                $('#orderDetailsTable').empty();
                data.forEach(order => {
                    var newRow = "<tr><th scope='row'>" + order.orderCode + "</th><td>" + order.purchaseDate + "</td><td>" + order.customerDetails.customerCode + "</td><td>" + order.buyItem[0].itemCode + "</td><td>" + order.size + "</td><td>" + order.unitPrice + "</td><td>" + order.qty + "</td><td>" + order.total + "</td><td>" + order.paymentMethod + "</td><td>" + order.orderStatus + "</td><td>" + order.employeeEntity.employeeCode + "</td></tr>";
                    $("#orderDetailsTable").append(newRow);
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

        if (selectBranch === "allBranch"){
            getAllOrder();
        }

    });


    document.addEventListener('DOMContentLoaded', function() {
     //  getAllOrder()
    
    });