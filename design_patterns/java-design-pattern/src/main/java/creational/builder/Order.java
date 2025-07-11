package creational.builder;

public class Order {

    private OrderType orderType;
    private BreadType breadType;
    private SauceType sauceType;
    private VegetableType vegetableType;

    public Order(OrderType orderType, BreadType breadType, SauceType sauceType, VegetableType vegetableType) {
        super();
        this.orderType = orderType;
        this.breadType = breadType;
        this.sauceType = sauceType;
        this.vegetableType = vegetableType;
    }

    @Override
    public String toString() {
        return "Order [orderType=" + orderType + ", breadType=" + breadType + ", sauceType=" + sauceType
            + ", vegetableType=" + vegetableType + "]";
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public SauceType getSauceType() {
        return sauceType;
    }

    public VegetableType getVegetableType() {
        return vegetableType;
    }

    public enum BreadType {
        SIMPLE, OMELETTE, FRIED_EGG, GRILLED_FISH, PORK, BEEF,
    }

    public enum OrderType {
        ON_SITE, TAKE_AWAY;
    }

    public enum SauceType {
        SOY_SAUCE, FISH_SAUCE, OLIVE_OIL, KETCHUP, MUSTARD;
    }

    public enum VegetableType {
        SALAD, CUCUMBER, TOMATO
    }

}
