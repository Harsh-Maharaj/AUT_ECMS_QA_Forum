package Question_3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public Maze loadMaze() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File does not exist: " + filePath);
        }

        Maze maze = new Maze();
        Scanner scanner = new Scanner(file);

        // Read the first line for total edges, grid dimensions
        if (scanner.hasNextLine()) {
            String firstLine = scanner.nextLine().trim();
            String[] firstLineParts = firstLine.split(",");
            if (firstLineParts.length != 3) {
                throw new IOException("Invalid maze configuration: " + firstLine);
            }
            int totalEdges = Integer.parseInt(firstLineParts[0]);
            int gridWidth = Integer.parseInt(firstLineParts[1]);
            int gridHeight = Integer.parseInt(firstLineParts[2]);
            System.out.println("Total edges: " + totalEdges + ", Grid dimensions: " + gridWidth + "x" + gridHeight);
        }

        // Read node definitions
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            String[] parts = line.split(",");
            if (parts.length < 3) continue;

            String name = parts[0];
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            Node node = new Node(name, x, y);
            maze.addNode(node);
            System.out.println("Added node: " + name + " at (" + x + "," + y + ")");
        }
        scanner.close();

        // Read node connections
        scanner = new Scanner(file);
        scanner.nextLine(); // Skip the first line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            String[] parts = line.split(",");
            if (parts.length < 4) continue;

            String name = parts[0];
            Node node = maze.getNode(name);
            for (int i = 3; i < parts.length; i++) {
                String neighborName = parts[i].trim();
                if (!neighborName.equals("A")) {
                    Node neighbor = maze.getNode(neighborName);
                    if (neighbor != null) {
                        node.addNeighbor(neighbor);
                        System.out.println("Connecting " + name + " to " + neighborName);  // Debugging connection
                    }
                }
            }
        }

        // Manually link node "U" to "EXIT"
        Node nodeU = maze.getNode("U");
        Node nodeExit = maze.getNode("EXIT");
        if (nodeU != null && nodeExit != null) {
            nodeU.addNeighbor(nodeExit);
            System.out.println("Manually connecting U to EXIT");
        }

        scanner.close();
        return maze;
    }
}
