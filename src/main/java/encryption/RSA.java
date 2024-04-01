package encryption;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private final BigInteger privateKey;
    private final BigInteger publicKey;
    private final BigInteger modulus;

    public RSA(int bitLength) {
        SecureRandom r = new SecureRandom();

        BigInteger p = new BigInteger(bitLength / 2, 100, r);
        BigInteger q = new BigInteger(bitLength / 2, 100, r);
        modulus = p.multiply(q);

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        publicKey = new BigInteger("65537");
        privateKey = publicKey.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger msg) {
        return msg.modPow(publicKey, modulus);
    }

    public BigInteger decrypt(BigInteger crypt) {
        return crypt.modPow(privateKey, modulus);
    }
}
