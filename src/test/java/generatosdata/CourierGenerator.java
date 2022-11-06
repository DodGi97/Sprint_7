package generatosdata;

import org.apache.commons.lang3.RandomStringUtils;
import pojos.CourierCreate;

public class CourierGenerator {
public static CourierCreate getNewCourier(){
    return new CourierCreate(RandomStringUtils.randomAlphanumeric(16),
            RandomStringUtils.randomAlphanumeric(16), RandomStringUtils.randomAlphanumeric(16));
}
}
