import java.math.BigInteger;
import java.util.Random;

public class RSAKeyPair {
    private RSAPrivateKey sk;
    private RSAPublicKey pk;

    public RSAKeyPair(RSAPrivateKey sk, RSAPublicKey pk) {
        this.sk = sk;
        this.pk = pk;
    }

    public RSAPrivateKey getPrivateKey() {
        return sk;
    }

    public RSAPublicKey getPublicKey() {
        return pk;
    }

    /**
     * Generate a new RSA Keypair.
     * @param length Length of primes to be chosen in bytes.
     * @return RSAKeypair - The generated Keypair.
     */
    public static RSAKeyPair generateKeyPair(int length) {
        BigInteger p = BigInteger.probablePrime(length, new Random());

        // Ensure that p != q
        BigInteger q;
        do {
            q = BigInteger.probablePrime(length, new Random());
        } while (q.equals(p));

        // n = p * q
        BigInteger n = p.multiply(q);

        // Calculate phi(n)
        BigInteger phiN = RSAUtilities.phiFromPrimeFactors(p,q);

        // All prime numbers smaller than phi(n) are a subset of Zstar phi(n).
        // Therefore, we can pick a random prime smaller than phi(n) as e.
        BigInteger e;
        do {
            e = BigInteger.probablePrime(length, new Random());
        } while (phiN.compareTo(e) <= 0);

        // Use the Extended Euclidean Algorithm to calculate d from e.
        BigInteger d = RSAUtilities.getDfromE(e, phiN);

        // Create Keypair object
        return new RSAKeyPair(new RSAPrivateKey(n, d), new RSAPublicKey(n, e));
    }
}
