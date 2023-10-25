package Odev2;
//Karakterlerin Tekrar Edilebilmesi Durumu:
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordGenerator2 {

    public static void main(String[] args) throws IOException {
        String characters = "aBdXagBH2X";
        int length = 6;

        generateCombinations(characters, length);
    }

    public static void generateCombinations(String characters, int length) throws IOException {
        char[] combination = new char[length];
        generateCombinationsRecursive(combination, characters, length, 0);
    }

    private static void generateCombinationsRecursive(char[] combination, String characters, int length, int position) throws IOException {
        if (position == length) {
            saveToFile(new String(combination));
            return;
        }

        for (int i = 0; i < characters.length(); i++) {
            combination[position] = characters.charAt(i);
            generateCombinationsRecursive(combination, characters, length, position + 1);
        }
    }

    private static void saveToFile(String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("repeating_passwords.txt", true))) {
            writer.write(password);
            writer.newLine();
        }
    }
}
