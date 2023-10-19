package Odev1;
import java.util.Scanner;

public class BasicCrypt4 {

    public static String encrypt(String text) {
        char[] encryptedText = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {
            char charAtI = text.charAt(i);
            encryptedText[i] = (char) (charAtI + (i + 1) * (i + 1));  // ASCII değerine sırasının karesini ekliyoruz.
        }

        return new String(encryptedText);
    }

    public static String decrypt(String encryptedText) {
        char[] decryptedText = new char[encryptedText.length()];

        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedChar = encryptedText.charAt(i);
            decryptedText[i] = (char) (encryptedChar - (i + 1) * (i + 1));  // ASCII değerinden sırasının karesini çıkarıyoruz.
        }

        return new String(decryptedText);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Lütfen şifrelemek istediğiniz metni girin: ");
        String originalText = scanner.nextLine();

        String encrypted = encrypt(originalText);
        String decrypted = decrypt(encrypted);

        System.out.println("Orijinal Metin: " + originalText);
        System.out.println("Şifrelenmiş Metin: " + encrypted);
        System.out.println("Deşifre Edilmiş Metin: " + decrypted);
    }
}
