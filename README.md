# Hashing Algorithm Examples

This repository contains examples of two widely used hashing algorithms: SHA-1 and SHA-3. These examples are provided to demonstrate the usage and differences between these algorithms.

## SHA-1 Algorithm

The SHA-1 (Secure Hash Algorithm 1) is a cryptographic hash function that produces a 160-bit (20-byte) hash value. It operates using the Merkle-Damgård construction and consists of a series of rounds compressing the input message blocks into a fixed-size output. Despite its historical significance, SHA-1 is considered weak in terms of security due to vulnerabilities discovered over time, including collision attacks. Therefore, it's no longer recommended for cryptographic purposes.

## SHA-3 Algorithm

The SHA-3 (Secure Hash Algorithm 3) is a cryptographic hash function designed as a part of a competition organized by NIST. It employs the Keccak algorithm, which utilizes a sponge construction, absorbing input bits into an internal state and then squeezing out a fixed-size hash. SHA-3 provides various output lengths, including 224, 256, 384, and 512 bits. It was designed with a primary focus on security and resistance to cryptanalytic attacks, making it suitable for modern cryptographic applications.

**coming soon**

## Note

The SHA-2 algorithm has been omitted from this repository since it shares similarities with SHA-1 but has more rounds, providing increased security. However, for the sake of simplicity and to highlight the differences between hashing algorithms, examples of SHA-1 and SHA-3 are provided.

# RSA Encryption and Decryption

This repository contains a Java class named `RSA` that implements a basic RSA encryption and decryption scheme.

## Description

The `RSA` class generates public and private keys along with a modulus based on the provided bit length. It utilizes the `BigInteger` class for handling large integer operations and `SecureRandom` for generating secure random numbers.

### Constructor

- `RSA(int bitLength)`: Initializes the RSA object with the specified bit length and generates the public and private keys along with the modulus.

### Methods

- `BigInteger encrypt(BigInteger msg)`: Encrypts the provided message using the public key and modulus.
- `BigInteger decrypt(BigInteger crypt)`: Decrypts the provided encrypted message using the private key and modulus.

## Usage

To use the `RSA` class, simply instantiate an object with the desired bit length:

```java
RSA rsa = new RSA(1024);
```

The `RSA` class implements a basic RSA encryption and decryption scheme. RSA (Rivest-Shamir-Adleman) is a widely used public-key cryptosystem that relies on the difficulty of factoring large prime numbers. The functionality of this class can be explained as follows:

1. **Key Generation**: In the constructor `RSA(int bitLength)`, two large prime numbers (p and q) are generated using a secure random number generator (`SecureRandom`). These primes are used to calculate the modulus (n = p * q). Additionally, the totient of n (phi = (p - 1) * (q - 1)) is computed. The public exponent (e) is typically chosen as a small prime, commonly 65537 (0x10001). The private exponent (d) is then calculated as the modular multiplicative inverse of e modulo phi.

2. **Encryption**: The `encrypt(BigInteger msg)` method takes a message `msg` as input and encrypts it using the public key (e) and modulus (n) with the formula: \( c = m^e \mod n \), where \( c \) is the encrypted ciphertext and \( m \) is the message.

3. **Decryption**: The `decrypt(BigInteger crypt)` method takes an encrypted ciphertext `crypt` as input and decrypts it using the private key (d) and modulus (n) with the formula: \( m = c^d \mod n \), where \( m \) is the original message.

The RSA encryption and decryption processes rely on modular exponentiation, which is efficient for large numbers due to the nature of the modular arithmetic operations involved. The security of RSA is based on the difficulty of factoring large semiprime numbers, which makes it computationally infeasible for an attacker to derive the private key from the public key.
