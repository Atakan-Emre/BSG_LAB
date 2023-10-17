package Odev1;
// Gerekli paketleri içe aktarıyoruz.
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESExample {

    // IV (Initialization Vector) boyutunu tanımlıyoruz. Bu, AES şifrelemesinin CBC modu için gereklidir.
    private static final int IV_SIZE = 16;

    // AES için rastgele bir anahtar oluşturmak için bir fonksiyon.
    public static SecretKey generateKey() throws Exception {
        // AES için KeyGenerator sınıfından bir nesne oluşturuyoruz.
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Anahtar boyutunu 128 bit olarak belirliyoruz.
        return keyGenerator.generateKey(); // Anahtarı döndürüyoruz.
    }

    // Verilen bir metni AES ile şifrelemek için bir fonksiyon.
    public static String encrypt(String plainText, SecretKey key) throws Exception {
        // AES şifrelemesi için Cipher sınıfından bir nesne oluşturuyoruz.
        // CBC modunu ve PKCS5Padding şemasını kullanıyoruz.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Rastgele bir IV (Initialization Vector) oluşturuyoruz.
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Şifreleme modunu, anahtarı ve IV'yi belirterek Cipher nesnesini başlatıyoruz.
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

        // Metni şifreliyoruz.
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        // Şifrelenmiş metni ve IV'yi birleştiriyoruz, böylece deşifre ederken IV'yi biliriz.
        byte[] combined = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);

        // Şifrelenmiş veriyi Base64 formatına dönüştürerek döndürüyoruz.
        return Base64.getEncoder().encodeToString(combined);
    }

    // Verilen şifrelenmiş metni AES ile deşifre etmek için bir fonksiyon.
    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        // AES şifrelemesi için Cipher sınıfından bir nesne oluşturuyoruz.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Şifrelenmiş metni Base64 formatından çözüyoruz.
        byte[] combined = Base64.getDecoder().decode(encryptedText);

        // IV'yi ayıklıyoruz.
        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(combined, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Şifrelenmiş metni ayıklıyoruz.
        byte[] encryptedBytes = new byte[combined.length - IV_SIZE];
        System.arraycopy(combined, IV_SIZE, encryptedBytes, 0, encryptedBytes.length);

        // Deşifreleme modunu, anahtarı ve IV'yi belirterek Cipher nesnesini başlatıyoruz.
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

        // Şifrelenmiş metni deşifre ediyoruz.
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Deşifre edilmiş metni döndürüyoruz.
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            // Test metni belirliyoruz.
            String text = "Hello AES!";

            // Rastgele bir anahtar oluşturuyoruz.
            SecretKey key = generateKey();

            // Metni şifreliyoruz.
            String encryptedText = encrypt(text, key);
            System.out.println("Encrypted: " + encryptedText);

            // Şifrelenmiş metni deşifre ediyoruz.
            String decryptedText = decrypt(encryptedText, key);
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            // Hataları yakalayıp yazdırıyoruz.
            e.printStackTrace();
        }
    }
}
