import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RSAUtilitiesTest {

    @Test
    public void gcdShouldReturnAGcd() {
        BigInteger a = BigInteger.valueOf(18);
        BigInteger b = BigInteger.valueOf(7);

        assertEquals(BigInteger.ONE, RSAUtilities.gcd(a,b), "The GCD of 18 and 7 is 1");
    }

    @Test
    public void phiFromPrimeFactors() {
        BigInteger a = BigInteger.valueOf(3);
        BigInteger b = BigInteger.valueOf(11);

        assertEquals(BigInteger.valueOf(20), RSAUtilities.phiFromPrimeFactors(a,b), "phi of 3*11=33 should be 20");
    }

    @Test
    public void dfromE() {
        BigInteger e = BigInteger.valueOf(3);
        BigInteger phiN = BigInteger.valueOf(20);

        assertEquals(BigInteger.valueOf(7), RSAUtilities.getDfromE(e,phiN), "the d for e=3, phi(n) = 20 is 7");
    }

    @Test
    public void modExp() {
        BigInteger x = new BigInteger("30235702357023875023875023557757833773");
        BigInteger e = new BigInteger("329540239509238509283052854534534");
        BigInteger m = new BigInteger("9283749238474382342394234238487");

        assertEquals(x.modPow(e,m), RSAUtilities.modExp(x,e,m), "The Method does the same thing as BigInteger::modPow");
    }

}