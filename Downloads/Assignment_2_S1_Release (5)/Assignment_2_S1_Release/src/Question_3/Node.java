package Question_3;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private int x, y;
    private List<Node> neighbors;

    public Node(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Node neighbor) {
        if (!this.neighbors.contains(neighbor)) {
            this.neighbors.add(neighbor);
        }
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
