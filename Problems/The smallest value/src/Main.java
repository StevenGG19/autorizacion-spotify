import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger tamano = scanner.nextBigInteger();
        int val = 1;
        while (true) {
            BigInteger res = BigInteger.valueOf(val);
            for (int i = val - 1; i > 0; i--) {
                res = res.multiply(BigInteger.valueOf(i));
                if (res.compareTo(tamano) >= 0) {
                    System.out.println(val);
                    return;
                }
            }
            val++;
        }
    }
}