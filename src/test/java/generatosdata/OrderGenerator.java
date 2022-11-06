package generatosdata;

import org.apache.commons.lang3.RandomStringUtils;
import pojos.OrderCreate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class OrderGenerator {
    public static OrderCreate getOrder(List<String> colors) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        OrderCreate createOrderRequest = new OrderCreate();
        createOrderRequest.setFirstName(RandomStringUtils.randomAlphabetic(10));
        createOrderRequest.setLastName(RandomStringUtils.randomAlphabetic(10));
        createOrderRequest.setAddress(RandomStringUtils.randomAlphabetic(10));
        createOrderRequest.setMetroStation(new Random().nextInt(10));
        createOrderRequest.setPhone(RandomStringUtils.randomNumeric(10));
        createOrderRequest.setRentTime(new Random().nextInt(10));
        createOrderRequest.setDeliveryDate(LocalDate.now().format(formatter));
        createOrderRequest.setComment(RandomStringUtils.randomAlphabetic(10));
        createOrderRequest.setColor(colors);
        return createOrderRequest;
    }
}
