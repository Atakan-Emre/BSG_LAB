package Odev2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//Karakterlerin Tekrar Edilmemesi Durumu

public class PasswordGeneratorUniqueNew {


    public static void main(String[] args) throws IOException {
        // Kullanılabilir karakterleri ve şifre uzunluğunu tanımlayın
        String availableCharacters = "arıkzthx";
        int passwordLength = 7;

        // Şifreleri oluşturma işlemini başlatın
        generatePasswords(availableCharacters, passwordLength);
    }

    // Şifreleri oluşturmak için yardımcı metod
    public static void generatePasswords(String availableCharacters, int passwordLength) throws IOException {
        // Şifre kombinasyonunu saklamak için bir karakter dizisi oluşturun
        char[] passwordCombination = new char[passwordLength];
        // Karakterlerin kullanım durumunu takip etmek için bir boolean dizisi oluşturun
        boolean[] characterUsage = new boolean[availableCharacters.length()];

        // Rekürsif metod çağrısını başlatın
        generateUniqueCombinations(passwordCombination, availableCharacters, characterUsage, passwordLength, 0);
    }

    // Rekürsif metod: Her bir karakterin şifrede yalnızca bir kez kullanılmasını sağlar
    private static void generateUniqueCombinations(char[] passwordCombination, String availableCharacters, boolean[] characterUsage, int passwordLength, int currentPosition) throws IOException {
        // Eğer tüm pozisyonlar doldurulmuşsa, şifreyi dosyaya kaydedin ve döndürün
        if (currentPosition == passwordLength) {
            savePasswordToFile(new String(passwordCombination));
            return;
        }

        // Kullanılabilir tüm karakterler üzerinde döngü başlatın
        for (int i = 0; i < availableCharacters.length(); i++) {
            // Eğer karakter daha önce kullanılmamışsa
            if (!characterUsage[i]) {
                // Karakteri kullanıldı olarak işaretleyin
                characterUsage[i] = true;
                // Karakteri mevcut pozisyona yerleştirin
                passwordCombination[currentPosition] = availableCharacters.charAt(i);

                // Rekürsif metod çağrısını, bir sonraki pozisyon için yapın
                generateUniqueCombinations(passwordCombination, availableCharacters, characterUsage, passwordLength, currentPosition + 1);

                // Backtrack: Karakteri gelecekteki iterasyonlar için kullanılmamış olarak işaretleyin
                characterUsage[i] = false;
            }
        }
    }

    // Şifreyi dosyaya kaydetmek için yardımcı metod
    private static void savePasswordToFile(String password) throws IOException {
        // Şifreyi dosyaya kaydedin
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("unique_passwords.txt", true))) {
            writer.write(password);
            writer.newLine();
        }
    }
}
