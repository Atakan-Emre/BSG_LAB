package Odev2;
//Karakterlerin Tekrar Edilebilmesi Durumu:
import java.io.FileWriter;
import java.io.IOException;

public class PasswordGeneratorRepeatingNew {

    // Ana metod
    public static void main(String[] args) throws IOException {
        // Kullanılabilir karakterleri ve şifre uzunluğunu tanımlayın
        String availableCharacters = "aBdXagBH2X";
        int passwordLength = 6;

        // Şifreleri oluşturma işlemini başlatın
        generateCombinations(availableCharacters, passwordLength);
    }

    // Şifreleri oluşturmak için yardımcı metod
    public static void generateCombinations(String availableCharacters, int passwordLength) throws IOException {
        // Şifre kombinasyonunu saklamak için bir karakter dizisi oluşturun
        char[] passwordCombination = new char[passwordLength];

        // Rekürsif metod çağrısını başlatın
        generateCombinationsRecursive(passwordCombination, availableCharacters, passwordLength, 0);
    }

    // Rekürsif metod: Karakterlerin şifrede tekrar kullanılmasına izin verir
    private static void generateCombinationsRecursive(char[] passwordCombination, String availableCharacters, int passwordLength, int currentPosition) throws IOException {
        // Eğer tüm pozisyonlar doldurulmuşsa, şifreyi dosyaya kaydedin ve döndürün
        if (currentPosition == passwordLength) {
            savePasswordToFile(new String(passwordCombination));
            return;
        }

        // Kullanılabilir tüm karakterler üzerinde döngü başlatın
        for (int i = 0; i < availableCharacters.length(); i++) {
            // Karakteri mevcut pozisyona yerleştirin
            passwordCombination[currentPosition] = availableCharacters.charAt(i);

            // Rekürsif metod çağrısını, bir sonraki pozisyon için yapın
            generateCombinationsRecursive(passwordCombination, availableCharacters, passwordLength, currentPosition + 1);
        }
    }

    // Şifreyi dosyaya kaydetmek için yardımcı metod
    private static void savePasswordToFile(String password) throws IOException {
        // BufferedWriter yerine FileWriter kullanarak şifreyi dosyaya kaydedin
        try (FileWriter writer = new FileWriter("repeating_passwords.txt", true)) {
            writer.write(password);
            writer.write('\n');  // Yeni bir satıra geçin
        }
    }
}
