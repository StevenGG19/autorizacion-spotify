import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] cadena = scanner.nextLine().split(" ");
        int tamano = cadena.length;
        int[] cadenaNumeros = new int[tamano];
        int movimientos = scanner.nextInt();
        int posicion;

        for (int i = 0; i < tamano; i++) {
            posicion = (i + movimientos) % tamano;
            cadenaNumeros[posicion] = Integer.parseInt(cadena[i]);
        }

        for (int val : cadenaNumeros) {
            System.out.print(val + " ");
        }
    }
}