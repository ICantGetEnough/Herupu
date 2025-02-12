package utility;

import java.util.Random;

public class ClientUtility {
    public static Integer getCode(){
        Random random = new Random();
        return random.nextInt(9999);
    }
}
