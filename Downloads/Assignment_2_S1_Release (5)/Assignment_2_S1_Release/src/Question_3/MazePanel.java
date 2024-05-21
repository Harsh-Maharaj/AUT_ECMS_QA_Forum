package Question_3;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.RenderingHints;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MazePanel extends JPanel {
    private Maze maze;
    private List<String> path;
    private Map<String, Color> nodeColors;
    private Set<String> animatedPaths;
    private Set<String> exploredPaths;
    private Timer animationTimer;
    private Timer flashTimer;
    private int currentStep;
    private boolean flashState;
    private String message;
    private PathFinder pathFinder;
    private Queue<String> explorationQueue;
    private boolean displayPathMessage;

    public MazePanel(Maze maze) {
        this.maze = maze;
        this.nodeColors = new HashMap<>();
        this.animatedPaths = new HashSet<>();
        this.exploredPaths = new HashSet<>();
        for (Node node : maze.getAllNodes()) {
            nodeColors.put(node.getName(), Color.BLUE);
        }
        this.currentStep = 0;
        this.flashState = false;
        this.message = "";
        this.explorationQueue = new LinkedList<>();
        this.pathFinder = new PathFinder(maze, this::updateExploration, this::onPathFound, this::onPathNotFound);
        this.displayPathMessage = false;
        findPath();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int nodeDiameter = 30;
        int xOffset = 50;
        int yOffset = 50;
        int scale = 100;

        g2.setStroke(new BasicStroke(3));

        if (maze != null) {
            for (Node node : maze.getAllNodes()) {
                int x = node.getX() * scale + xOffset;
                int y = node.getY() * scale + yOffset;

                for (Node neighbor : node.getNeighbors()) {
                    int neighborX = neighbor.getX() * scale + xOffset + nodeDiameter / 2;
                    int neighborY = neighbor.getY() * scale + yOffset + nodeDiameter / 2;
                    g2.setColor(Color.YELLOW);
                    String pathKey = getPathKey(node.getName(), neighbor.getName());
                    if (animatedPaths.contains(pathKey)) {
                        g2.setColor(Color.GREEN); // Color for the active part of the path in green
                    } else if (exploredPaths.contains(pathKey)) {
                        g2.setColor(Color.RED); // Color for explored paths in red
                    }
                    g2.drawLine(x + nodeDiameter / 2, y + nodeDiameter / 2, neighborX, neighborY);
                }

                if (path != null && currentStep < path.size() && node.getName().equals(path.get(currentStep))) {
                    g2.setColor(flashState ? Color.GREEN : Color.BLUE); // Flashing effect
                } else {
                    g2.setColor(nodeColors.get(node.getName()));
                }
                g2.fillOval(x, y, nodeDiameter, nodeDiameter);
                g2.setColor(Color.BLACK);
                g2.drawOval(x, y, nodeDiameter, nodeDiameter);

                g2.setFont(g2.getFont().deriveFont(Font.BOLD));
                String nodeName = node.getName();
                int textWidth = g2.getFontMetrics().stringWidth(nodeName);
                int textX = x + (nodeDiameter - textWidth) / 2 - 5; // Adjust textX to move left
                int textY = y - 10;
                g2.drawString(nodeName, textX, textY);
            }

            drawLegendAndMessage(g2);
            if (displayPathMessage) {
                drawPathMessage(g2); // Draw the path message at the bottom
            }
        }
    }

    private String getPathKey(String node1, String node2) {
        return node1.compareTo(node2) < 0 ? node1 + "-" + node2 : node2 + "-" + node1;
    }

    private void drawLegendAndMessage(Graphics2D g2) {
        int legendX = 50;
        int legendY = getHeight() - 30;
        g2.setColor(Color.YELLOW);
        g2.fillRect(legendX, legendY - 20, 20, 20);
        g2.setColor(Color.BLACK);
        g2.drawString("Path between nodes", legendX + 30, legendY - 5);

        g2.setColor(Color.GREEN);
        g2.fillRect(legendX + 180, legendY - 20, 20, 20);
        g2.setColor(Color.BLACK);
        g2.drawString("Current path to exit maze", legendX + 210, legendY - 5);

        g2.setColor(Color.RED);
        g2.fillRect(legendX + 400, legendY - 20, 20, 20);
        g2.setColor(Color.BLACK);
        g2.drawString("Exploring paths to exit maze", legendX + 430, legendY - 5);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16));
        g2.drawString(message, getWidth() / 2 - g2.getFontMetrics().stringWidth(message) / 2, 20);
    }

    private void drawPathMessage(Graphics2D g2) {
        if (path != null && !path.isEmpty()) {
            String pathMessage = "Path: " + String.join(" -> ", path);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14));
            int messageY = getHeight() - 10; // Adjust y-coordinate to be below the legend
            g2.drawString(pathMessage, getWidth() / 2 - g2.getFontMetrics().stringWidth(pathMessage) / 2, messageY);
        }
    }

    private void findPath() {
        pathFinder.findPath("START", "EXIT");
    }

    private void startAnimation() {
        System.out.println("Starting animation");
        animationTimer = new Timer(500, e -> {
            if (!explorationQueue.isEmpty()) {
                String edge = explorationQueue.poll();
                exploredPaths.add(edge);
                repaint();
            } else if (currentStep < path.size() - 1) {
                String currentNode = path.get(currentStep);
                String nextNode = path.get(currentStep + 1);
                flashState = !flashState;
                if (flashState) {
                    nodeColors.put(currentNode, Color.GREEN);
                    animatedPaths.add(getPathKey(currentNode, nextNode));
                    System.out.println("Animating step: " + currentStep + ", " + currentNode + " -> " + nextNode);
                } else {
                    nodeColors.put(path.get(currentStep), Color.BLUE);
                }
                repaint();
                if (!flashState) {
                    currentStep++;
                }
            } else {
                // Ensure all path segments turn green
                for (int i = 0; i < path.size() - 1; i++) {
                    String node1 = path.get(i);
                    String node2 = path.get(i + 1);
                    animatedPaths.add(getPathKey(node1, node2));
                    nodeColors.put(node1, Color.GREEN);
                    nodeColors.put(node2, Color.GREEN);
                }
                // Turn the EXIT node green
                nodeColors.put("EXIT", Color.GREEN);
                System.out.println("Setting EXIT node to green");

                // Delay for a brief moment before final repaint to ensure it shows
                Timer finalRepaintTimer = new Timer(100, event -> {
                    animationTimer.stop();
                    flashTimer.stop();
                    message = "Path to exit maze completed";
                    System.out.println("Animation completed");
                    displayPathMessage = true; // Set flag to display path message
                    repaint(); // Final repaint to show the completed path and message
                });
                finalRepaintTimer.setRepeats(false);
                finalRepaintTimer.start();
            }
        });

        flashTimer = new Timer(250, e -> {
            flashState = !flashState;
            repaint();
        });

        animationTimer.start();
        flashTimer.start();
    }

    // Callback to update exploration
    private void updateExploration(String from, String to) {
        explorationQueue.add(getPathKey(from, to));
    }

    // Called by the PathFinder once the path is found
    public void onPathFound(List<String> path) {
        this.path = path;
        message = "Exploring paths to exit maze";
        System.out.println("Path found: " + path);
        startAnimation();
    }

    // Called by the PathFinder if no path is found
    public void onPathNotFound() {
        message = "No path found to exit maze";
        System.out.println("No path found");
        repaint();
    }
}
