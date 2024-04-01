package encryption;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        RSA rsa = new RSA(256);
        // flow for BigInteger
        BigInteger value = new BigInteger("126207244316550804821666916");
        BigInteger encrypted = rsa.encrypt(value);
        BigInteger decrypted = rsa.decrypt(encrypted);
        System.out.printf("initial value: %s, encrypted value: %s, decrypted value: %s%n", BigInteger.TEN, encrypted, decrypted);
        // flow for real message (String)
        String msg = "hello world";
        BigInteger msgToBigInt = strToBigInt(msg);
        BigInteger encryptedStr = rsa.encrypt(msgToBigInt);
        BigInteger decryptedStr = rsa.decrypt(encryptedStr);
        System.out.printf("initial value: %s, encrypted value: %s, decrypted value: %s%n", msg, encryptedStr, bigIntToStr(decryptedStr));
    }

    private static BigInteger strToBigInt(String msg) {
        return new BigInteger(msg.getBytes());
    }

    private static String bigIntToStr(BigInteger bi) {
        return new String(bi.toByteArray(), StandardCharsets.UTF_8);
    }
}
