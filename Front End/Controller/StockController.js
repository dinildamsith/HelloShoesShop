const getAllStock = () => {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/stock",
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwtToken"));
        },
        success: function(data) {

            data.forEach(stock => {
                console.log(stock.itemEntititys.itemCode)
                var newRow = "<tr><th scope='row'>" + stock.stockId + "</th><td>" + stock.itemEntititys.itemCode + "</td><td>" + stock.itemSize + "</td><td>" + stock.qty +  "</td></tr>";
                $("#stock_Table").append(newRow);
            });

        },
        error: function(xhr, status, error) {
            alert("Failed");
        }
    });
};
document.addEventListener('DOMContentLoaded', function() {
getAllStock()
});
