package de.hfu;

import java.util.Scanner;
/**
 * Javadoc Kommentar von Andi
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Geben Sie einen Text ein!");
        Scanner i = new Scanner(System.in);
        String input = i.nextLine();
        System.out.println("String in Großbuchstaben : " + input.toUpperCase());
    }
}
