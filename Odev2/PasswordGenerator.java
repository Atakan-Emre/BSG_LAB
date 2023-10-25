package Odev2;
//Karakterlerin Tekrar Edilmemesi Durumu

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PasswordGenerator {

    public static void main(String[] args) throws IOException {
        String characters = "aBdXagBH2X";
        int length = 6;

        generateCombinations(characters, length);
    }

    public static void generateCombinations(String characters, int length) throws IOException {
        char[] combination = new char[length];
        Set<Character> uniqueCharacters = new HashSet<>();
        for (char ch : characters.toCharArray()) {
            uniqueCharacters.add(ch);
        }
        generateUniqueCombinationsRecursive(combination, uniqueCharacters, length, 0);
    }

    private static void generateUniqueCombinationsRecursive(char[] combination, Set<Character> uniqueCharacters, int length, int position) throws IOException {
        if (position == length) {
            saveToFile(new String(combination));
            return;
        }

        Set<Character> usedCharacters = new HashSet<>(uniqueCharacters);  // Copy the set to preserve the original set
        for (char ch : usedCharacters) {
            uniqueCharacters.remove(ch);  // Remove the character as it's being used
            combination[position] = ch;
            generateUniqueCombinationsRecursive(combination, uniqueCharacters, length, position + 1);
            uniqueCharacters.add(ch);  // Backtrack: re-add the character for future use
        }
    }

    private static void saveToFile(String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("unique_passwords.txt", true))) {
            writer.write(password);
            writer.newLine();
        }
    }
}
