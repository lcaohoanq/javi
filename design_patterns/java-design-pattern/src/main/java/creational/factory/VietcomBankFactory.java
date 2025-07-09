package creational.factory;

public class VietcomBankFactory extends BankFactory {
    public Bank createBank() {
        return new VietcomBank();
    }
}
