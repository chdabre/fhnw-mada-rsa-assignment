import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class RSAPrivateKey extends RSAKey {
    private BigInteger d;

    public RSAPrivateKey(BigInteger n, BigInteger d) {
        super(n);
        this.d = d;
    }

    public BigInteger getD() {
        return d;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (getClass() == obj.getClass()) {
            RSAPrivateKey o = (RSAPrivateKey) obj;
            return this.getN().equals(o.getN()) && this.getD().equals(o.getD());
        }
        return false;
    }

    /**
     * Store a key to a file
     * @param fileName The name of the file
     */
    public void saveToFile(String fileName) { super.saveToFile(d, fileName); }

    /**
     * Read a RSA Private key from a given file.
     * @param fileName Format: comma separated, enclosed in parentheses "(n,d)"
     * @return RSAPrivateKey private key
     */
    public static RSAPrivateKey readFromFile(String fileName) {
        try {
            // Load Key from file
            String fileContent = FileUtilities.readStringFromFile(fileName);
            // Remove Parentheses and split by comma
            String[] keyTuple = fileContent.substring(1, fileContent.length() - 1).split(",");

            BigInteger n = new BigInteger(keyTuple[0]);
            BigInteger d = new BigInteger(keyTuple[1]);

            return new RSAPrivateKey(n, d);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
