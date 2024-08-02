package lk.ijse.hello_shoes_shop_backend.reqAndResp.secure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignIn {
    private String email;
    private String password;
}
