package Odev1;

public class BasicCrypt3 {

    // Şifreleme fonksiyonu
    public static String encrypt(String text) {
        // StringBuilder nesnesi ile metni tersine çeviriyoruz.
        return new StringBuilder(text).reverse().toString();
    }

    // Deşifreleme fonksiyonu
    public static String decrypt(String encryptedText) {
        // Şifreleme yöntemi metni tersine çevirmek olduğu için, deşifreleme için aynı yöntemi kullanıyoruz.
        return new StringBuilder(encryptedText).reverse().toString();
    }

    public static void main(String[] args) {
        String originalText = "Atakan Emre";
        String encrypted = encrypt(originalText);
        String decrypted = decrypt(encrypted);

        System.out.println("Orijinal Metin: " + originalText);
        System.out.println("Şifrelenmiş Metin: " + encrypted);
        System.out.println("Deşifre Edilmiş Metin: " + decrypted);
    }
}
