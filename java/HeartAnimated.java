import javax.swing.*;
import java.awt.*;

public class HeartAnimated {
    public static void main(String[] args) {
        int rows = 7;
        String a = "💖💖";

        // Setup GUI
        JFrame frame = new JFrame("Animated Emoji Heart");
        JEditorPane editorPane = new JEditorPane("text/html", "");
        editorPane.setEditable(false);
        editorPane.setBackground(Color.BLACK);
        editorPane.setForeground(Color.PINK);
        editorPane.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        frame.add(new JScrollPane(editorPane));
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // HTML styling
        StringBuilder art = new StringBuilder("<html><body style='color:#ff69b4; font-family:Segoe UI Emoji; font-size:28px; background-color:#111;'><pre>");

        try {
            // Upper part
            for (int i = 0; i <= rows; i++) {
                art.append("&nbsp;".repeat((rows - i) * 2));
                for (int k = 0; k <= i; k++) art.append(a).append(" ");
                art.append("&nbsp;".repeat((rows - i) * 4));
                for (int k = 0; k <= i; k++) art.append(a).append(" ");
                art.append("<br>");
                editorPane.setText(art.toString() + "</pre></body></html>");
                Thread.sleep(200);
            }

            // Lower part
            int r = rows + 1;
            int spaceCount = a.length() + 1;
            String space = "&nbsp;".repeat(spaceCount);
            for (int i = 0; i <= r; i++) {
                art.append(space.repeat(i));
                for (int k = 2 * (r - i); k >= 1; k--) art.append(a).append(" ");
                art.append("<br>");
                editorPane.setText(art.toString() + "</pre></body></html>");
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Final display
        editorPane.setText(art.toString() + "</pre></body></html>");
    }
}
