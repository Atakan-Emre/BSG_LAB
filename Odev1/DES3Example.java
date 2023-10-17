package Odev1;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator; // Bu satırı ekledik
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.util.Base64;

public class DES3Example {

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        return keyGenerator.generateKey();
    }

    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
    }

    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
    }

    public static void main(String[] args) {
        try {
            // Anahtarı oluştur
            SecretKey key = generateKey();

            // Şifreleme işlemi
            String plainText = "Hello 3DES!";
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
