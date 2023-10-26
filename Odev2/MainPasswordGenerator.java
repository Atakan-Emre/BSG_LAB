package Odev2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainPasswordGenerator {

    public static void main(String[] args) throws IOException {
        // Kullanıcıdan girdi almak için Scanner nesnesi oluşturuluyor.
        Scanner scanner = new Scanner(System.in);

        // Kullanıcıdan karakter setini al.
        System.out.print("Karakterleri girin: ");
        String characters = scanner.nextLine();

        // Kullanıcıdan şifre uzunluğunu al.
        System.out.print("Şifre uzunluğunu girin: ");
        int length = scanner.nextInt();
        scanner.nextLine();  // Yeni satır karakterini temizle

        // Kullanıcıdan karakterlerin tekrar edip edemeyeceğini öğren.
        System.out.print("Karakterler tekrar edebilir mi? (evet/hayir): ");
        String repeatResponse = scanner.nextLine().trim().toLowerCase();

        // Şifre oluşturma işlemini başlat, tekrar edilebilirlik durumuna göre uygun sınıfı çağır.
        if (repeatResponse.equals("evet")) {
            PasswordGeneratorRepeating.generateCombinations(characters, length);
        } else {
            PasswordGeneratorUnique.generateCombinations(characters, length);
        }

        scanner.close();  // Scanner nesnesini kapat.
    }
}

class PasswordGeneratorUniqueA {

    // Tekil karakterlerle şifre kombinasyonları oluştur.
    public static void generateCombinations(String characters, int length) throws IOException {
        char[] combination = new char[length];  // Şifreleri tutacak dizi
        Set<Character> uniqueCharacters = new HashSet<>();  // Tekil karakterler kümesi
        for (char ch : characters.toCharArray()) {  // String'i karakter dizisine dönüştür ve her karakteri küme ye ekle
            uniqueCharacters.add(ch);
        }
        generateUniqueCombinationsRecursive(combination, uniqueCharacters, length, 0);  // Rekürsif metod çağrısı
    }

    // Rekürsif metod, her pozisyon için tüm karakterleri dener.
    private static void generateUniqueCombinationsRecursive(char[] combination, Set<Character> uniqueCharacters, int length, int position) throws IOException {
        if (position == length) {  // Temel durum, tüm pozisyonlar doldurulmuşsa şifreyi kaydet
            saveToFile(new String(combination), "unique_passwords.txt");
            return;
        }

        Set<Character> usedCharacters = new HashSet<>(uniqueCharacters);  // Karakter kümesinin bir kopyasını oluştur
        for (char ch : usedCharacters) {  // Her bir karakter için
            uniqueCharacters.remove(ch);  // Karakteri kümeden kaldır
            combination[position] = ch;  // Karakteri mevcut pozisyona ata
            generateUniqueCombinationsRecursive(combination, uniqueCharacters, length, position + 1);  // Bir sonraki pozisyon için rekürsif metod çağrısı
            uniqueCharacters.add(ch);  // Karakteri küme ye geri ekle (backtracking)
        }
    }

    // Şifreyi dosyaya kaydetme metodu
    private static void saveToFile(String password, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName, true)) {  // Dosya yazıcı oluştur
            writer.write(password);  // Şifreyi yaz
            writer.write('\n');  // Yeni satıra geç
        }  // FileWriter otomatik olarak kapatılır
    }
}

class PasswordGeneratorRepeatingA {

    // Tekrar eden karakterlerle şifre kombinasyonları oluştur.
    public static void generateCombinations(String characters, int length) throws IOException {
        char[] combination = new char[length];  // Şifreleri tutacak dizi
        generateCombinationsRecursive(combination, characters, length, 0);  // Rekürsif metod çağrısı
    }

    // Rekürsif metod, her pozisyon için tüm karakterleri dener.
    private static void generateCombinationsRecursive(char[] combination, String characters, int length, int position) throws IOException {
        if (position == length) {  // Temel durum, tüm pozisyonlar doldurulmuşsa şifreyi kaydet
            saveToFile(new String(combination), "repeating_passwords.txt");
            return;
        }

        for (int i = 0; i < characters.length(); i++) {  // Her bir karakter için
            combination[position] = characters.charAt(i);  // Karakteri mevcut pozisyona ata
            generateCombinationsRecursive(combination, characters, length, position + 1);  // Bir sonraki pozisyon için rekürsif metod çağrısı
        }
    }

    // Şifreyi dosyaya kaydetme metodu
    private static void saveToFile(String password, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName, true)) {  // Dosya yazıcı oluştur
            writer.write(password);  // Şifreyi yaz
            writer.write('\n');  // Yeni satıra geç
        }  // FileWriter otomatik olarak kapatılır
    }
}
