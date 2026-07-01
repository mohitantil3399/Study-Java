import java.io.FileWriter;
import java.io.IOException;

public class MultiplicationTables {

    public static void generateTable(int i, FileWriter writer) throws IOException {
        for (int j = 1; j <= 10; j++) {
            writer.write(i + "*" + j + "=" + (i * j) + "\n");
        }
        writer.write("\n"); // Add a blank line between tables
    }

    public static void main(String[] args) {
        
    
        try (FileWriter writer = new FileWriter("table.txt", true)) { // 'true' enables append mode
            for (int i = 2; i <= 100; i++) {
                generateTable(i, writer);
            }
            System.out.println("Multiplication tables written to table.txt");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}