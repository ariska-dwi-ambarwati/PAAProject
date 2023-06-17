package projectpaa2023;
import javax.swing.JFrame;
/**
 *
 * @author lenovo
 */

public class ProjectPAA2023 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Project PAA 2023");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Code panel = new Code();
        frame.getContentPane().add(panel);
        
        frame.pack();
        frame.setVisible(true);
    }
}