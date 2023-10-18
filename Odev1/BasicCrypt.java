package Odev1;

// Kullanıcıdan metin girişi alabilmek için Scanner sınıfını dahil ediyoruz.
import java.util.Scanner;

public class BasicCrypt {

    // Metni şifreleyen fonksiyon.
    public static String encrypt(String text) {
        // Şifrelenmiş metni saklamak için boş bir string tanımlıyoruz.
        String encryptedText = "";

        // Metindeki her bir karakteri döngü ile geziyoruz.
        for (int i = 0; i < text.length(); i++) {
            // Şu anki karakteri alıyoruz.
            char charAtI = text.charAt(i);
            // Bu karakterin ASCII değerine 3 ekleyerek basit bir şifreleme gerçekleştiriyoruz.
            char encryptedChar = (char) (charAtI + 3);
            // Şifrelenmiş karakteri sonuca ekliyoruz.
            encryptedText += encryptedChar;
        }

        // Oluşturulan şifrelenmiş metni döndürüyoruz.
        return encryptedText;
    }

    // Şifrelenmiş metni deşifre eden fonksiyon.
    public static String decrypt(String encryptedText) {
        // Deşifre edilmiş metni saklamak için boş bir string tanımlıyoruz.
        String decryptedText = "";

        // Şifrelenmiş metindeki her bir karakteri döngü ile geziyoruz.
        for (int i = 0; i < encryptedText.length(); i++) {
            // Şu anki şifrelenmiş karakteri alıyoruz.
            char encryptedChar = encryptedText.charAt(i);
            // Bu karakterin ASCII değerinden 3 çıkararak orijinal karakteri geri alıyoruz.
            char decryptedChar = (char) (encryptedChar - 3);
            // Deşifre edilmiş karakteri sonuca ekliyoruz.
            decryptedText += decryptedChar;
        }

        // Oluşturulan deşifre edilmiş metni döndürüyoruz.
        return decryptedText;
    }

    // Ana fonksiyon.
    public static void main(String[] args) {
        // Kullanıcıdan metin girişi alabilmek için Scanner nesnesini oluşturuyoruz.
        Scanner scanner = new Scanner(System.in);
        // Kullanıcıya metin girmesi için bir mesaj yazdırıyoruz.
        System.out.print("Lütfen şifrelemek istediğiniz metni girin: ");
        // Kullanıcının girdiği metni okuyoruz.
        String originalText = scanner.nextLine();

        // Orijinal metni şifreleyerek şifrelenmiş metni elde ediyoruz.
        String encrypted = encrypt(originalText);
        // Şifrelenmiş metni deşifre ederek orijinal metni geri alıyoruz.
        String decrypted = decrypt(encrypted);

        // Orijinal metni, şifrelenmiş metni ve deşifre edilmiş metni ekrana yazdırıyoruz.
        System.out.println("Orijinal Metin: " + originalText);
        System.out.println("Şifrelenmiş Metin: " + encrypted);
        System.out.println("Deşifre Edilmiş Metin: " + decrypted);
    }
}
