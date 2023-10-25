package Odev2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainPasswordGenerator {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Karakterleri girin: ");
        String characters = scanner.nextLine();

        System.out.print("Şifre uzunluğunu girin: ");
        int length = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        System.out.print("Karakterler tekrar edebilir mi? (evet/hayir): ");
        String repeatResponse = scanner.nextLine().trim().toLowerCase();

        if (repeatResponse.equals("evet")) {
            PasswordGenerator2A.generateCombinations(characters, length);
        } else {
            PasswordGeneratorA.generateCombinations(characters, length);
        }

        scanner.close();
    }
}

class PasswordGeneratorA {

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

class PasswordGenerator2A {

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
