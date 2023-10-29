package com.darsgateway;


import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptConfigDESTest {
    public String key = "yes3420911$";

    @Test
    void stringEncryptor() {
        String url = null;
        String userName = null;
        String password = null;
        String redisPassword = null;

        System.out.println("En_url : " + jasyptEncoding(url));
        System.out.println("En_username : " + jasyptEncoding(userName));
        System.out.println("En_password : " + jasyptEncoding(password));
        System.out.println("En_redisPassword : " + jasyptEncoding(redisPassword));
    }

    @Test
    void stringDecryptor() {
        String url = null;
        String userName = null;
        String password = null;
        String redisPassword = null;

        System.out.println("De_url : " + jasyptDecoding(url));
        System.out.println("De_username : " + jasyptDecoding(userName));
        System.out.println("De_password : " + jasyptDecoding(password));
        System.out.println("De_redisPassword : " + jasyptDecoding(redisPassword));
    }

    public String jasyptEncoding(String value) {
        PooledPBEStringEncryptor pbeEnc = new PooledPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }

    public String jasyptDecoding(String value) {
        PooledPBEStringEncryptor pbeEnc = new PooledPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.decrypt(value);
    }
}
