package creational.builder;

import creational.builder.Order.BreadType;
import creational.builder.Order.OrderType;
import creational.builder.Order.SauceType;
import creational.builder.Order.VegetableType;

public interface OrderBuilder {
    OrderBuilder orderType(OrderType orderType);

    OrderBuilder orderBread(BreadType breadType);

    OrderBuilder orderSauce(SauceType sauceType);

    OrderBuilder orderVegetable(VegetableType vegetableType);

    Order build();

}
