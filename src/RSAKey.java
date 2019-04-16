import java.math.BigInteger;

public class RSAKey {
    private BigInteger n;

    public RSAKey(BigInteger n) {
        this.n = n;
    }

    public BigInteger getN() { return n; }

    /**
     * Store a key to a file
     * @param x the secondary part of the key
     */
    public void saveToFile(BigInteger x, String fileName) {
        FileUtilities.saveStringToFile("(" + getN() + "," + x + ")", fileName);
    }
}
