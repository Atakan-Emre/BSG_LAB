package com.example.sifreuret;
import java.util.Random;

public class SifreUretici {
    private static final Random rnd = new Random();

    public static String rastgeleSifreUret() {
        return String.format("%06d", rnd.nextInt(1000000));
    }
}
