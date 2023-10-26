package Odev2;  // Paket adı

//Karakterlerin Tekrar Edilebilmesi Durumu:
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordGeneratorRepeating {  // Sınıf adı

    public static void main(String[] args) throws IOException {  // Ana metod
        String characters = "aBdXagBH2X";  // Kullanılacak karakterler
        int length = 6;  // Şifre uzunluğu

        generateCombinations(characters, length);  // Metod çağrısı
    }

    public static void generateCombinations(String characters, int length) throws IOException {  // Metod tanımı
        char[] combination = new char[length];  // Karakter dizisi oluşturma
        generateCombinationsRecursive(combination, characters, length, 0);  // Rekürsif metod çağrısı
    }

    private static void generateCombinationsRecursive(char[] combination, String characters, int length, int position) throws IOException {  // Rekürsif metod tanımı
        if (position == length) {  // Temel durum: tüm pozisyonlar doldurulmuşsa
            saveToFile(new String(combination));  // Şifreyi dosyaya kaydet
            return;  // Metodu sonlandır
        }

        for (int i = 0; i < characters.length(); i++) {  // Karakterler üzerinde döngü başlat
            combination[position] = characters.charAt(i);  // Karakteri mevcut pozisyona ata
            generateCombinationsRecursive(combination, characters, length, position + 1);  // Bir sonraki pozisyon için rekürsif metod çağrısı
        }
    }

    private static void saveToFile(String password) throws IOException {  // Dosyaya kaydetme metod tanımı
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("repeating_passwords.txt", true))) {  // Dosya yazıcı oluşturma
            writer.write(password);  // Şifreyi dosyaya yaz
            writer.newLine();  // Yeni satıra geç
        }  // BufferedWriter otomatik olarak kapatılır
    }
}
