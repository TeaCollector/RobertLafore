package Recursia6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TriangleApp {
    static int theNumber;
    public static void main(String[] args) throws IOException {
        System.out.println("Enter the number: ");
        theNumber = getInt();
        int theAnswer = triangle(theNumber);
        System.out.println("Triangle = " + theAnswer);
    }
    public static int triangle(int num) {
        System.out.println("Entering: num = " + num);
        if(num == 1) {                                      // Базовое ограничение.
            System.out.println("Returning 1");
            return 1;
        }
        else {
            int temp = num * triangle(num - 1);
            System.out.println("Returning " + temp);
            return temp;
        }
    }
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(isr);
        String s = bf.readLine();
        return s;
    }
    public static int getInt() throws IOException {
        String str = getString();
        return Integer.parseInt(str);
    }
}
