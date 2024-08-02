import {SignInModel} from "../Model/SignInModel.js";


// Login
$("#loginBtn").on('click', () => {
    var loginMail = $("#loginPwMail").val();
    var loginPw   = $('#loginPwTxt').val();


    console.log(loginMail,loginPw)
    var loginDetails = new SignInModel(loginMail,loginPw)

    var loginDetailsJson = JSON.stringify(loginDetails);


    const sendAJAX = (loginDetails) => {
        return new Promise((resolve, reject) => {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/shoes/api/v1/user/signIn",
                contentType: "application/json",
                data: loginDetails,
                success: function(data) {
                    resolve(data); // Resolve the promise with the received data


                },
                error: function(xhr, status, error) {
                    reject(error); // Reject the promise with the error
                }
            });
        });
    };

// Usage:
    sendAJAX(loginDetailsJson)
        .then(token => {
            localStorage.setItem("jwtToken",token)
            window.location.href = '../view/adminPanel.html';
        })
        .catch(error => {

            alert("Wrong Password Or Email");
        });



});






