public class Shape {
    public static void main(String[] args) throws InterruptedException {
        for (int j = 39; j >= -39; j--) {
            for (int i = -35; i <= 35; i++) {
                boolean draw = (
                    ((Math.abs(j - 25) < 14 && Math.abs(i) < 6) ||
                    (Math.abs(j - 25) == 13 && Math.abs(i) < 10)) ||
                    (Math.pow(Math.abs(i) - 9, 2) + 2 * Math.pow(j, 2) <= 100) ||
                    (9 * Math.abs(i) - 14 * j - 210 <= 0 && j <= -3) ||
                    (Math.pow(i, 2) + 2 * Math.pow(j + 30, 2) <= 225 &&
                     Math.pow(i, 2) + 2 * Math.pow(j + 30, 2) >= 64 &&
                     j <= -29) ||
                    (Math.abs(Math.abs(i) - 11.5) < 3.5 && Math.abs(j + 23) < 7)
                );

                System.out.print(draw ? "*" : " ");
                Thread.sleep(2); // Delay for each character
            }
            System.out.println();
        }
    }
}