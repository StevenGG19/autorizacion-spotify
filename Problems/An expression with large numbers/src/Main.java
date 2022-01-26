import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        BigInteger num1 = scanner.nextBigInteger();
        BigInteger num2 = scanner.nextBigInteger();
        BigInteger num3 = scanner.nextBigInteger();
        BigInteger num4 = scanner.nextBigInteger();

        System.out.println(num1.negate().multiply(num2).add(num3).subtract(num4));
    }
}