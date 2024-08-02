package lk.ijse.hello_shoes_shop_backend.Controller;

import lk.ijse.hello_shoes_shop_backend.Service.AuthenticationService;
import lk.ijse.hello_shoes_shop_backend.Service.UserService;
import lk.ijse.hello_shoes_shop_backend.reqAndResp.response.JwtAuthResponse;
import lk.ijse.hello_shoes_shop_backend.reqAndResp.secure.SignIn;
import lk.ijse.hello_shoes_shop_backend.reqAndResp.secure.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/user/")
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.PATCH, RequestMethod.DELETE ,RequestMethod.POST,RequestMethod.PUT,RequestMethod.GET})
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/singUp")
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody SignUp signUpReq){
        return ResponseEntity.ok(authenticationService.signUp(signUpReq));
    }

    @PostMapping("/signIn")
    public String signIn(@RequestBody SignIn signInReq){
        JwtAuthResponse jwtAuthResponse = authenticationService.signIn(signInReq);
        String token = jwtAuthResponse.getToken();
        return token;

    }


}
