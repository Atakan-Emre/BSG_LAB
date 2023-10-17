public class BasicCrpt {

    // Şifreleme fonksiyonu
    public static String encrypt(String text) {
        // Şifrelenmiş metni oluşturmak için bir StringBuilder nesnesi tanımlıyoruz.
        StringBuilder encryptedText = new StringBuilder();

        // Orijinal metnin ilk karakterini şifrelenmiş metne doğrudan ekliyoruz.
        encryptedText.append(text.charAt(0));

        // Orijinal metnin uzunluğunun bir eksiğine kadar bir döngü başlatıyoruz.
        for (int i = 0; i < text.length() - 1; i++) {
            // Şu anki karakteri alıyoruz.
            char charAtI = text.charAt(i);
            // Bir sonraki karakteri alıyoruz.
            char nextChar = text.charAt(i + 1);

            // İki ardışık karakterin ASCII değerlerinin farkını alıyoruz.
            // 256'ya eklememizin sebebi, negatif bir değer elde etmekten kaçınmaktır.
            char encryptedChar = (char) ((nextChar - charAtI + 256) % 256);
            // Şifrelenmiş karakteri sonuca ekliyoruz.
            encryptedText.append(encryptedChar);
        }

        // Oluşturulan şifrelenmiş metni geri döndürüyoruz.
        return encryptedText.toString();
    }

    // Deşifreleme fonksiyonu
    public static String decrypt(String encryptedText) {
        // Deşifre edilmiş metni oluşturmak için bir StringBuilder nesnesi tanımlıyoruz.
        StringBuilder decryptedText = new StringBuilder();
        // Şifrelenmiş metnin ilk karakterini deşifre edilmiş metne doğrudan ekliyoruz.
        decryptedText.append(encryptedText.charAt(0));

        // Şifrelenmiş metnin uzunluğuna kadar bir döngü başlatıyoruz.
        for (int i = 1; i < encryptedText.length(); i++) {
            // Deşifre edilmiş metinden şu anki karakteri alıyoruz.
            char charAtI = decryptedText.charAt(i - 1);
            // Şifrelenmiş metinden şu anki karakteri alıyoruz.
            char encryptedChar = encryptedText.charAt(i);

            // Orijinal karakteri geri almak için şifrelenmiş karakteri şu anki karakterle topluyoruz.
            char decryptedChar = (char) ((charAtI + encryptedChar) % 256);
            // Deşifre edilmiş karakteri sonuca ekliyoruz.
            decryptedText.append(decryptedChar);
        }

        // Oluşturulan deşifre edilmiş metni geri döndürüyoruz.
        return decryptedText.toString();
    }

    // Ana fonksiyon
    public static void main(String[] args) {
        // Orijinal metni tanımlıyoruz.
        String originalText = "Atakan Emre";
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
