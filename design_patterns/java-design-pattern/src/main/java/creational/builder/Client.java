package creational.builder;

import creational.builder.Order.BreadType;
import creational.builder.Order.OrderType;
import creational.builder.Order.SauceType;

public class Client {

    public static void main(String[] args) {
        Order order = new FastFoodOrderBuilder()
            .orderType(OrderType.ON_SITE).orderBread(BreadType.OMELETTE)
            .orderSauce(SauceType.SOY_SAUCE).build();
        System.out.println(order);
    }
}
