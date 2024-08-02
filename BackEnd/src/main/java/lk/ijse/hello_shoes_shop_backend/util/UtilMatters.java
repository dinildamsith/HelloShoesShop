package lk.ijse.hello_shoes_shop_backend.util;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;


@Component
public class UtilMatters {


    public static String convertBase64(String data){
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public static byte[] decode(String d){
        return Base64.getDecoder().decode(d);
    }


}
