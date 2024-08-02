package lk.ijse.hello_shoes_shop_backend.Service.impl;


import lk.ijse.hello_shoes_shop_backend.Dao.UserRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.UserDto;
import lk.ijse.hello_shoes_shop_backend.Service.UserService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final DataConvert dataConvert;

    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userRepo.findByEmail(username)
                        .orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    @Override
    public void saveUser(UserDto userDTO) {
        userRepo.save(dataConvert.userDtoConvertUserEntity(userDTO));
    }
}
