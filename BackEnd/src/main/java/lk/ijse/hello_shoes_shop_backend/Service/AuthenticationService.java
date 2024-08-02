package lk.ijse.hello_shoes_shop_backend.Service;


import lk.ijse.hello_shoes_shop_backend.reqAndResp.response.JwtAuthResponse;
import lk.ijse.hello_shoes_shop_backend.reqAndResp.secure.SignIn;
import lk.ijse.hello_shoes_shop_backend.reqAndResp.secure.SignUp;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(SignUp signUp);

    JwtAuthResponse refreshToken(String accessToken);
}
