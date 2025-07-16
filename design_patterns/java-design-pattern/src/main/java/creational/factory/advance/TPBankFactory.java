package creational.factory.advance;

import creational.factory.Bank;
import creational.factory.BankFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TPBankFactory extends BankFactory {
    private final RedisCache redisCache;
    private final AuditLogger auditLogger;

    @Override
    public Bank createBank() {
        // Setup logic giả định
        String apiKey = System.getenv().getOrDefault("TPBANK_API_KEY", "default-api-key");
        BankConfig config = new BankConfig("VN-HCM");

        // Có thể thêm validate, log, connect Redis, v.v.
        redisCache.connect();
        auditLogger.log("Preparing to init TPBank with config: " + config.region());

        return new TPBank(apiKey, redisCache, auditLogger, config);
    }
}

