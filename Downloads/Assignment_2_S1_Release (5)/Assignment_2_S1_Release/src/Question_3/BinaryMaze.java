package Question_3;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class BinaryMaze {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a panel for the buttons
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

            // Create and configure buttons
            JButton buttonMaze1 = new JButton("Run Maze 1");
            buttonMaze1.setPreferredSize(new Dimension(150, 50));
            JButton buttonMaze2 = new JButton("Run Maze 2");
            buttonMaze2.setPreferredSize(new Dimension(150, 50));

            // Add action listeners to the buttons
            buttonMaze1.addActionListener(createMazeButtonListener(frame, "resources/maze1.txt"));
            buttonMaze2.addActionListener(createMazeButtonListener(frame, "resources/maze2.txt"));

            // Add buttons to the panel
            buttonPanel.add(buttonMaze1);
            buttonPanel.add(buttonMaze2);

            // Add the panel to the frame
            frame.add(buttonPanel);
            frame.setSize(400, 200);  // Adjust the size of the frame
            frame.setLocationRelativeTo(null);  // Center the frame on the screen
            frame.setVisible(true);  // Show the frame
        });
    }

    private static ActionListener createMazeButtonListener(JFrame frame, String filePath) {
        return e -> {
            try {
                FileManager fileManager = new FileManager(filePath);
                Maze maze = fileManager.loadMaze();  // Load the maze
                MazePanel panel = new MazePanel(maze);  // Create panel with maze

                frame.getContentPane().removeAll();  // Remove existing content
                frame.add(panel);  // Add the maze panel
                frame.revalidate();
                frame.repaint();
                frame.setSize(800, 700);  // Set frame size for the maze
                frame.setLocationRelativeTo(null);  // Center the frame on the screen
                frame.setVisible(true);  // Show the frame
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    }
}
