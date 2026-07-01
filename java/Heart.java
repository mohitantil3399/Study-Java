import javax.swing.*;
import java.io.*;
import java.awt.Desktop;

public class Heart {
    public static void main(String[] args) {
        int rows =9 ;
        String a = "💖💖💕";
        StringBuilder art = new StringBuilder();

        for (int i = 0; i <= rows; i++) {
            for (int j = rows - i; j >= 0; j--) {
                art.append("&nbsp;&nbsp;");// upper lobe, initial spacing
            }
            for (int k = 0; k <= i; k++) {
                art.append(a).append(" ");
            }
            for (int j = 2 * a.length() * (rows - i); j >= 0; j--) {
                art.append("&nbsp;");
            }
            for (int k = 0; k <= i; k++) {
                art.append(a).append(" ");
            }
            art.append("<br>");
        }

        int r = rows + 1;
        int spaceCount = a.length() + 2;
        String space = "&nbsp;".repeat(spaceCount);// space increases by length +2 .

        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= i; j++) {
                art.append(space);// lower lobe intial spacing 
            }
            for (int k = 2 * (r - i); k >= 1; k--) {
                art.append(a).append(" ");
            }
            art.append("<br>");
        }

        // Wrap in HTML
        String htmlContent = "<html><body style='font-size:32px; font-family:Segoe UI Emoji;'><pre>" + art + "</pre></body></html>";

        // Show in Swing JLabel (still monochrome)
      //  JLabel label = new JLabel("<html><div style='font-size:24px; font-family:Segoe UI Emoji;'><pre>" + art + "</pre></div></html>");
        // JOptionPane.showMessageDialog(null, label, "Emoji Heart Art (Swing)", JOptionPane.PLAIN_MESSAGE);

        // Export to HTML file and open in browser
        try {
            File file = new File("emoji_heart.html");
            try (PrintWriter out = new PrintWriter(file)) {
                out.println(htmlContent);
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(file.toURI());
            } else {
                JOptionPane.showMessageDialog(null, "HTML file saved as emoji_heart.html, but automatic browser launch is not supported.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving or opening HTML file: " + e.getMessage());
        }
    }
}
