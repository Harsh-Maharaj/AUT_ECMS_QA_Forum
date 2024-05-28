package Question_2;

import javax.swing.JFrame;

public class VirusSimulation {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mobile Phone Virus Simulation");
        frame.setSize(800, 600); // Adjust the size as needed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel panel = new Panel(frame);
        frame.add(panel);
        frame.setVisible(true);
    }
}
