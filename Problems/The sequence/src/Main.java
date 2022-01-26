import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int i = 0;
        int actual = 0;
        int datos = 0;
        while (i < size) {
            datos = scanner.nextInt();
            if (datos % 4 == 0 && datos > actual) {
                actual = datos;
            }
            i++;
        }
        System.out.println(actual);
    }
}