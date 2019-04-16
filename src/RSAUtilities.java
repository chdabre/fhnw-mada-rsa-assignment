import java.math.BigInteger;
import java.util.ArrayList;

/**
 * This Class provides the mathematical building blocks of the RSA encryption.
 */
public class RSAUtilities {

    /**
     * The euler phi function of two prime factors p*q can be calculated as (p-1) * (q-1)
     * @param p BigInteger
     * @param q BigInteger
     * @return BigInteger phi(p*q)
     */
    public static BigInteger phiFromPrimeFactors(BigInteger p, BigInteger q) {
        return p.subtract(BigInteger.ONE)
                .multiply(q.subtract(BigInteger.ONE));
    }

    /**
     * Extended Euclidean Algorithm
     * @param a BigInteger
     * @param b BigInteger
     * @return BigInteger The gcd of a and b
     */
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        // Implemented as described in Number Theory Slides
        BigInteger _a = a;
        BigInteger _b = b;
        BigInteger x0 = BigInteger.ONE;
        BigInteger _x0;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger _y0;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;
        BigInteger q;
        BigInteger r;

        while (!_b.equals(BigInteger.ZERO)) {
            q = _a.divide(_b);
            r = _a.mod(_b);

            _a = _b;
            _b = r;

            _x0 = x0;
            _y0 = y0;
            x0 = x1;
            y0 = y1;

            x1 = _x0.subtract(q.multiply(x1));
            y1 = _y0.subtract(q.multiply(y1));
        }

        return x0.multiply(a)
                .add(y0.multiply(b));
    }

    /**
     * Use the Extended Euclidean Algorithm to calculate d
     * @param a BigInteger
     * @param b BigInteger
     * @return BigInteger d from E and phi(n)
     */
    public static BigInteger getDfromE(BigInteger e, BigInteger phiN) {
        // Implemented as described in Number Theory Slides
        BigInteger _a = phiN;
        BigInteger _b = e;
        BigInteger x0 = BigInteger.ONE;
        BigInteger _x0;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger _y0;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;
        BigInteger q;
        BigInteger r;

        while (!_b.equals(BigInteger.ZERO)) {
            q = _a.divide(_b);
            r = _a.mod(_b);

            _a = _b;
            _b = r;

            _x0 = x0;
            _y0 = y0;
            x0 = x1;
            y0 = y1;

            x1 = _x0.subtract(q.multiply(x1));
            y1 = _y0.subtract(q.multiply(y1));
        }

        if (y0.compareTo(BigInteger.ZERO) > 0) {
            return y0;
        } else {
            return y0.add(phiN);
        }
    }

    /**
     * Fast Exponentiation algorithm
     * @param x Base
     * @param e Exponent
     * @param m Modulo
     * @return x^e mod m
     */
    public static BigInteger modExp(BigInteger x, BigInteger e, BigInteger m) {
        // Implemented as described in Number Theory Slides
        int i = e.bitLength();
        BigInteger h = BigInteger.ONE;
        BigInteger k = x;

        while (i >= 0) {
            if (e.testBit(e.bitLength() - i)) {
                h = h.multiply(k).mod(m);
            }
            k = k.pow(2).mod(m);
            i--;
        }

        return h;
    }
}