import {SignUpModel} from "../Model/SignUpModel.js";


// User SigUp
$("#signUpBtn").on('click', () => {
    var signUpEmail = $("#singUpEmailTxt").val();
    var signUpPw    = $('#signUpPwTxt').val();
    var name        = $('#name').val();
    var selectRole  = $('#signUpSelectRoleOption').val();

    var signupDetails = new SignUpModel(signUpEmail, signUpPw, name, selectRole);

    var signUpDetailsJson = JSON.stringify(signupDetails);

    const sendAJAX = (singUpDetails) => {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/shoes/api/v1/user/singUp",
            contentType: "application/json",
            data: singUpDetails,
            success: function(data) {
                alert("Success");
            },
            error: function(xhr, status, error) {
                alert("Failed");
            }
        });
    };
    sendAJAX(signUpDetailsJson)


});


