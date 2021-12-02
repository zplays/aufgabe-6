package de.hfu;
import java.util.Locale;
import java.util.Scanner;

public class gross {
    public static void main (String [] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Geben Sie einen text ein");
        String eingabe = scan.nextLine();
        eingabe = eingabe.toUpperCase();
        System.out.println(eingabe);
    }
}
