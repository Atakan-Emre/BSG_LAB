package Odev2;
//Karakterlerin Tekrar Edilmemesi Durumu


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordGenerator {

    public static void main(String[] args) throws IOException {
        String characters = "123456";
        int length = 4;

        generateCombinations(characters, length);
    }

    public static void generateCombinations(String characters, int length) throws IOException {
        char[] combination = new char[length];
        boolean[] used = new boolean[characters.length()];
        generateUniqueCombinationsRecursive(combination, characters, used, length, 0);
    }

    private static void generateUniqueCombinationsRecursive(char[] combination, String characters, boolean[] used, int length, int position) throws IOException {
        if (position == length) {
            saveToFile(new String(combination));
            return;
        }

        for (int i = 0; i < characters.length(); i++) {
            if (!used[i]) {
                used[i] = true;
                combination[position] = characters.charAt(i);
                generateUniqueCombinationsRecursive(combination, characters, used, length, position + 1);
                used[i] = false;  // backtrack
            }
        }
    }

    private static void saveToFile(String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("unique_passwords.txt", true))) {
            writer.write(password);
            writer.newLine();
        }
    }
}
