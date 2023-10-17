package Odev1;
public class BasicCrypt2 {

    // Kaydırma miktarını belirleyen sabit
    private static final int SHIFT = 3;

    // Şifreleme fonksiyonu
    public static String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char encryptedChar = (char) ((c - 'A' + SHIFT) % 26 + 'A');
                encryptedText.append(encryptedChar);
            } else if (Character.isLowerCase(c)) {
                char encryptedChar = (char) ((c - 'a' + SHIFT) % 26 + 'a');
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    // Deşifreleme fonksiyonu
    public static String decrypt(String encryptedText) {
        StringBuilder decryptedText = new StringBuilder();

        for (char c : encryptedText.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char decryptedChar = (char) ((c - 'A' - SHIFT + 26) % 26 + 'A');
                decryptedText.append(decryptedChar);
            } else if (Character.isLowerCase(c)) {
                char decryptedChar = (char) ((c - 'a' - SHIFT + 26) % 26 + 'a');
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
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
