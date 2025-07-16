package creational.factory;

import creational.factory.advance.AuditLogger;
import creational.factory.advance.RedisCache;
import creational.factory.advance.TPBankFactory;

public class Client {
    public static void main(String[] args) {
//        Bank bank = BankFactory.getBank(BankType.TPBANK);
//        System.out.println(bank.getBankName()); // TPBank

//        V2
//        BankFactory factory = new TPBankFactory();
//        Bank bank = factory.createBank();
//        System.out.println(bank.getBankName());

        var redis = new RedisCache();
        var logger = new AuditLogger();

        BankFactory factory = new TPBankFactory(redis, logger);
        Bank bank = factory.createBank();

        System.out.println("Bank: " + bank.getBankName());

    }
}
