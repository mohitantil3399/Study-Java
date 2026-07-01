public class HelloHeart {
    public static void main(String[] args) {
        String hello = "Hello ";
        int n = 6; // Controls the size of the heart

        // Upper lobes
        for (int i = n / 2; i <= n; i += 2) {
            // Left spacing
            for (int j = 1; j < n - i; j += 2) {
                System.out.print("      ");
            }

            // Left lobe
            for (int j = 1; j <= i; j++) {
                System.out.print(hello);
            }

            // Middle spacing
            for (int j = 1; j <= n - i; j++) {
                System.out.print("      ");
            }

            // Right lobe
            for (int j = 1; j <= i; j++) {
                System.out.print(hello);
            }

            System.out.println();
        }

        // Lower triangle
        for (int i = n; i >= 1; i--) {
            // Left spacing
            for (int j = 0; j < n - i; j++) {
                System.out.print("      ");
            }

            // Heart taper
            for (int j = 1; j <= i * 2 - 1; j++) {
                System.out.print(hello);
            }

            System.out.println();
        }
    }
}
