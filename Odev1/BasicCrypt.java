package Odev1;
import java.util.Scanner;

public class BasicCrypt {

    // Metni şifreleyen fonksiyon.
    public static String encrypt(String text) {
        // Şifrelenmiş metni saklamak için bir karakter dizisi (char array) oluşturuyoruz.
        char[] encryptedText = new char[text.length()];

        // Metindeki her bir karakteri döngü ile geziyoruz.
        for (int i = 0; i < text.length(); i++) {
            // Şu anki karakteri alıyoruz.
            char charAtI = text.charAt(i);
            // Bu karakterin ASCII değerine, sırasının karesini ekleyerek basit bir şifreleme gerçekleştiriyoruz.
            encryptedText[i] = (char) (charAtI + (i + 1) * (i + 1));
        }

        // Oluşturulan şifrelenmiş metni döndürüyoruz.
        return new String(encryptedText);
    }

    // Şifrelenmiş metni deşifre eden fonksiyon.
    public static String decrypt(String encryptedText) {
        // Deşifre edilmiş metni saklamak için bir karakter dizisi (char array) oluşturuyoruz.
        char[] decryptedText = new char[encryptedText.length()];

        // Şifrelenmiş metindeki her bir karakteri döngü ile geziyoruz.
        for (int i = 0; i < encryptedText.length(); i++) {
            // Şu anki şifrelenmiş karakteri alıyoruz.
            char encryptedChar = encryptedText.charAt(i);
            // Orijinal karakteri geri almak için, şifrelenmiş karakterin ASCII değerinden sırasının karesini çıkarıyoruz.
            decryptedText[i] = (char) (encryptedChar - (i + 1) * (i + 1));
        }

        // Oluşturulan deşifre edilmiş metni döndürüyoruz.
        return new String(decryptedText);
    }

    // Ana fonksiyon.
    public static void main(String[] args) {
        // Kullanıcıdan metin girişi alabilmek için Scanner nesnesini oluşturuyoruz.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Lütfen şifrelemek istediğiniz metni girin: ");
        // Kullanıcının girdiği metni okuyoruz.
        String originalText = scanner.nextLine();

        // Orijinal metni şifreliyoruz.
        String encrypted = encrypt(originalText);
        // Şifrelenmiş metni deşifre ediyoruz.
        String decrypted = decrypt(encrypted);

        // Sonuçları ekrana yazdırıyoruz.
        System.out.println("Orijinal Metin: " + originalText);
        System.out.println("Şifrelenmiş Metin: " + encrypted);
        System.out.println("Deşifre Edilmiş Metin: " + decrypted);
    }
}
