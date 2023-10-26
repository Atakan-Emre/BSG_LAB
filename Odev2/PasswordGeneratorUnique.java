package Odev2;  // Paket adı

//Karakterlerin Tekrar Edilmemesi Durumu
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PasswordGeneratorUnique {  // Sınıf adı

    public static void main(String[] args) throws IOException {  // Ana metod
        String characters = "arlkzthx";  // Kullanılacak karakterler
        int length = 7;  // Şifre uzunluğu

        generateCombinations(characters, length);  // Metod çağrısı
    }

    public static void generateCombinations(String characters, int length) throws IOException {  // Metod tanımı
        char[] combination = new char[length];  // Karakter dizisi oluşturma
        Set<Character> uniqueCharacters = new HashSet<>();  // Tekil karakterler kümesi oluşturma
        for (char ch : characters.toCharArray()) {  // Karakterler üzerinde döngü başlat
            uniqueCharacters.add(ch);  // Karakteri küme ye ekle
        }
        generateUniqueCombinationsRecursive(combination, uniqueCharacters, length, 0);  // Rekürsif metod çağrısı
    }

    private static void generateUniqueCombinationsRecursive(char[] combination, Set<Character> uniqueCharacters, int length, int position) throws IOException {  // Rekürsif metod tanımı
        if (position == length) {  // Temel durum: tüm pozisyonlar doldurulmuşsa
            saveToFile(new String(combination));  // Şifreyi dosyaya kaydet
            return;  // Metodu sonlandır
        }

        Set<Character> usedCharacters = new HashSet<>(uniqueCharacters);  // Karakter kümesinin bir kopyasını oluştur
        for (char ch : usedCharacters) {  // Kullanılan karakterler üzerinde döngü başlat
            uniqueCharacters.remove(ch);  // Karakteri kümeden kaldır (kullanıldı olarak işaretle)
            combination[position] = ch;  // Karakteri mevcut pozisyona ata
            generateUniqueCombinationsRecursive(combination, uniqueCharacters, length, position + 1);  // Bir sonraki pozisyon için rekürsif metod çağrısı
            uniqueCharacters.add(ch);  // Backtrack: karakteri küme ye geri ekle (kullanılmadı olarak işaretle)
        }
    }

    private static void saveToFile(String password) throws IOException {  // Dosyaya kaydetme metod tanımı
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("unique_passwords.txt", true))) {  // Dosya yazıcı oluşturma
            writer.write(password);  // Şifreyi dosyaya yaz
            writer.newLine();  // Yeni satıra geç
        }  // BufferedWriter otomatik olarak kapatılır
    }
}
