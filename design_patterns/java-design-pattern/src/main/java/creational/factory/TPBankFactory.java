package creational.factory;

public class TPBankFactory extends BankFactory {
    public Bank createBank() {
        return new TPBank();
    }
}
