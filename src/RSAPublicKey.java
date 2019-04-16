import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RSAPublicKey extends RSAKey {
    private BigInteger e;

    public RSAPublicKey(BigInteger n, BigInteger e) {
        super(n);
        this.e = e;
    }

    public BigInteger getE() {
        return e;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (getClass() == obj.getClass()) {
            RSAPublicKey o = (RSAPublicKey) obj;
            return this.getN().equals(o.getN()) && this.getE().equals(o.getE());
        }
        return false;
    }

    /**
     * Store a key to a file
     * @param fileName The name of the file
     */
    public void saveToFile(String fileName) {
        super.saveToFile(e, fileName);
    }

    /**
     * Read a RSA Public key from a given file.
     * @param fileName Format: comma separated, enclosed in parentheses "(n,e)"
     * @return RSAPublicKey public key
     */
    public static RSAPublicKey readFromFile(String fileName) {
        try {
            // Load Key from file
            String fileContent = FileUtilities.readStringFromFile(fileName);
            // Remove Parentheses and split by comma
            String[] keyTuple = fileContent.substring(1, fileContent.length() - 1).split(",");

            BigInteger n = new BigInteger(keyTuple[0]);
            BigInteger e = new BigInteger(keyTuple[1]);

            return new RSAPublicKey(n, e);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
