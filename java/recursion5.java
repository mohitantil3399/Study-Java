import java.util.Scanner;
import java.math.BigInteger;

public class recursion5 {
    public static BigInteger calcPower(BigInteger x, BigInteger n) {
        if (n.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        if (x.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        BigInteger halfPower = calcPower(x, n.divide(BigInteger.TWO));
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return halfPower.multiply(halfPower);
        } else {
            return halfPower.multiply(halfPower).multiply(x);
        }
    }

    public static void main(String[] args) {
        Scanner ms = new Scanner(System.in);
        System.out.print("Enter the base : ");
        BigInteger x = ms.nextBigInteger();
        System.out.print("Enter the power : ");
        BigInteger n = ms.nextBigInteger();
        BigInteger ans = calcPower(x, n);
        System.out.println("The value of " + x + "^" + n + " is: " + ans);
    }
}
