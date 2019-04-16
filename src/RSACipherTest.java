import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RSACipherTest {

    @Test
    public void encryptDecryptTest() {
        String ciphertext = "Hello, world!";

        RSAKeyPair keyPair = RSAKeyPair.generateKeyPair(128);
        RSAPrivateKey privateKey = keyPair.getPrivateKey();
        RSAPublicKey publicKey = keyPair.getPublicKey();

        assertEquals(ciphertext, RSACipher.decryptString(privateKey, RSACipher.encryptString(publicKey, ciphertext)), "The decrypted string matches the initial string");
    }

    @Test
    public void encryptDecryptWithFiles() {
        // Generate Keypair and save it
        RSAKeyPair keyPair = RSAKeyPair.generateKeyPair(1000);
        keyPair.getPrivateKey().saveToFile("sk.txt");
        keyPair.getPublicKey().saveToFile("pk.txt");


        // Read the keys back into memory, just for fun
        RSAPublicKey publicKey = RSAPublicKey.readFromFile("pk.txt");
        RSAPrivateKey privateKey = RSAPrivateKey.readFromFile("sk.txt");

        // Read the text from text.txt and encrypt it using the public Key
        String text = FileUtilities.readStringFromFile("text.txt");
        String encryptedString = RSACipher.encryptString(publicKey, text);

        // Save the encrypted text to a file
        FileUtilities.saveStringToFile(encryptedString, "cipher.txt");

        // Read the encrypted text from the file and decrypt it using the private Key
        String ciphertext = FileUtilities.readStringFromFile("cipher.txt");
        String decryptedString = RSACipher.decryptString(privateKey, ciphertext);

        // Save the original text to text-d.txt
        FileUtilities.saveStringToFile(decryptedString, "text-d.txt");

        assertEquals(decryptedString, text, "The decrypted string matches the initial string");
    }

    @Test
    public void decryptCipherFromAssignment() {
        RSAPrivateKey privateKey = RSAPrivateKey.readFromFile("sk-assignment.txt");
        String encryptedString = FileUtilities.readStringFromFile("cipher-assignment.txt");

        assertEquals("Well done!\t\t\t\t\t", RSACipher.decryptString(privateKey, encryptedString), "The decrypted string should match Well done!");
    }
}
