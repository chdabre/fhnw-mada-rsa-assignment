import java.math.BigInteger;

/**
 * Provides the methods for encrypting and decrypting Strings.
 */
public class RSACipher {
    /**
     * Encrypt a string char-wise by x^e mod n for a given public Key.
     * @param publicKey The public Key to encrypt with
     * @param s The String to be encrypted.
     * @return String comma-separated decimal representation of encrypted chars.
     */
    public static String encryptString(RSAPublicKey publicKey, String s) {
        StringBuilder encryptedString = new StringBuilder();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            BigInteger encrypted = RSAUtilities.modExp(BigInteger.valueOf(c), publicKey.getE(), publicKey.getN());
            encryptedString.append(encrypted.toString());

            if (i != s.length() -1 ) encryptedString.append(",");
        }

        return encryptedString.toString();
    }

    /**
     * Encrypt a list of comma-separarted decimal values by y^d mod n for a given private Key.
     * @param privateKey The private Key to decrypt with
     * @param s The String (comma-separated list) to be decrypted
     * @return decrypted String
     */
    public static String decryptString(RSAPrivateKey privateKey, String s) {
        StringBuilder decryptedString = new StringBuilder();
        String[] cipherCodes = s.split(",");

        for (String cipher : cipherCodes){
            BigInteger decrypted = RSAUtilities.modExp(new BigInteger(cipher), privateKey.getD(), privateKey.getN());
            decryptedString.append((char) decrypted.shortValueExact());
        }

        return decryptedString.toString();
    }
}