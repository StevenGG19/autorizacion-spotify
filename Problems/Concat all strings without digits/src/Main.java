import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        StringBuilder cadena = new StringBuilder();
        int j = 0;
        for (String dato : strings) {
            cadena.append(dato);
        }
        for (; j < cadena.length(); j++) {
            if (!Character.isLetter(cadena.charAt(j))) {
                cadena.deleteCharAt(j);
                j--;
            }
        }
        return cadena.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}