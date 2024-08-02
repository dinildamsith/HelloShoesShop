package lk.ijse.hello_shoes_shop_backend.Dao;


import lk.ijse.hello_shoes_shop_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);

}
