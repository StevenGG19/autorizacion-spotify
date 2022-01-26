import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int numero = scanner.nextInt();
        System.out.println(numero > 0 && numero < 10 ? "true" : "false");
    }
}