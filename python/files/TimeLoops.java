public class TimeLoops {
    public static void main(String[] args) {
        long iterations = 100000;
        long start = System.nanoTime();

        for (long i = 0; i < iterations; i++) {
            // running only , not printing anything 
        }

        long end = System.nanoTime();
        double total_time = (end - start) ;

        System.out.println("\nLoop iterations: " + iterations);
        System.out.printf("Time taken: %.6f nanoseconds%n", total_time);
    }
}