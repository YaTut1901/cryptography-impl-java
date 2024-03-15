# Hashing Algorithm Examples

This repository contains examples of two widely used hashing algorithms: SHA-1 and SHA-3. These examples are provided to demonstrate the usage and differences between these algorithms.

## SHA-1 Algorithm

The SHA-1 (Secure Hash Algorithm 1) is a cryptographic hash function that produces a 160-bit (20-byte) hash value. It operates using the Merkle-Damgård construction and consists of a series of rounds compressing the input message blocks into a fixed-size output. Despite its historical significance, SHA-1 is considered weak in terms of security due to vulnerabilities discovered over time, including collision attacks. Therefore, it's no longer recommended for cryptographic purposes.

## SHA-3 Algorithm

The SHA-3 (Secure Hash Algorithm 3) is a cryptographic hash function designed as a part of a competition organized by NIST. It employs the Keccak algorithm, which utilizes a sponge construction, absorbing input bits into an internal state and then squeezing out a fixed-size hash. SHA-3 provides various output lengths, including 224, 256, 384, and 512 bits. It was designed with a primary focus on security and resistance to cryptanalytic attacks, making it suitable for modern cryptographic applications.

**coming soon**

## Note

The SHA-2 algorithm has been omitted from this repository since it shares similarities with SHA-1 but has more rounds, providing increased security. However, for the sake of simplicity and to highlight the differences between hashing algorithms, examples of SHA-1 and SHA-3 are provided.

