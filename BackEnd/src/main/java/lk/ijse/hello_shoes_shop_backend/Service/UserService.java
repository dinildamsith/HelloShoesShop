package lk.ijse.hello_shoes_shop_backend.Service;


import lk.ijse.hello_shoes_shop_backend.Dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();
    void saveUser(UserDto userDTO);
}
