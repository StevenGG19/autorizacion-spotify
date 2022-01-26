import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int tamano = scanner.nextInt();
        int[][] arreglo = new int[tamano][tamano];
        int con = 0;
        int top = 0;
        int izq = 0;
        for (int i = 0; i < tamano; i++) {
            for (int j = i; j < tamano; j++) {
                arreglo[i][j] = ++con;
            }
            int valor = tamano - 1;
            for (int j = tamano - (i + 1); j > 0; j--) {
                arreglo[++i][valor] = ++con;
            }

            int valor2 = 0;
            for (int j = i - 1; j >= izq; j--) {
                arreglo[i][j] = ++con;
                valor2 = j;
            }

            while (i > top + 1) {
                arreglo[--i][valor2] = ++con;
            }
            top++;
            i--;
            tamano--;
            izq++;
        }
        for (int[] num : arreglo) {
            for (int num2 : num) {
                System.out.print(num2 + " ");
            }
            System.out.println();
        }
    }
}