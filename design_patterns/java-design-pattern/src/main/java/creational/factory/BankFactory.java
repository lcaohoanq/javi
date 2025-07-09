package creational.factory;

//V1
//public class BankFactory {
//
//    private BankFactory() {
//    }
//
//    public static Bank getBank(BankType bankType) {
//        return switch (bankType) {
//            case TPBANK -> new TPBank();
//            case VIETCOMBANK -> new VietcomBank();
//        };
//    }
//
//}

//V2
public abstract class BankFactory {
    public abstract Bank createBank();
}