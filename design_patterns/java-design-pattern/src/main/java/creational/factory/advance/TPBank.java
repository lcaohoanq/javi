package creational.factory.advance;

import creational.factory.Bank;

public class TPBank implements Bank {
    private final String apiKey;
    private final RedisCache redisCache;
    private final AuditLogger auditLogger;
    private final BankConfig config;

    public TPBank(String apiKey, RedisCache redisCache, AuditLogger auditLogger, BankConfig config) {
        this.apiKey = apiKey;
        this.redisCache = redisCache;
        this.auditLogger = auditLogger;
        this.config = config;

        auditLogger.log("TPBank initialized with API key: " + apiKey);
    }

    public String getBankName() {
        return "TPBank - Secure Init";
    }
}

