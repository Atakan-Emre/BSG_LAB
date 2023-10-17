package Odev1;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class BlowfishExample {

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        return keyGenerator.generateKey();
    }

    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
    }

    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
    }

    public static void main(String[] args) {
        try {
            // Anahtarı oluştur
            SecretKey key = generateKey();

            // Şifreleme işlemi
            String plainText = "Hello Blowfish!";
            String encryptedText = encrypt(plainText, key);
            System.out.println("Encrypted: " + encryptedText);

            // Deşifreleme işlemi
            String decryptedText = decrypt(encryptedText, key);
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
