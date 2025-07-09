package creational.factory;

public class Client {
    public static void main(String[] args) {
//        Bank bank = BankFactory.getBank(BankType.TPBANK);
//        System.out.println(bank.getBankName()); // TPBank

        BankFactory factory = new TPBankFactory();
        Bank bank = factory.createBank();
        System.out.println(bank.getBankName());
    }
}
