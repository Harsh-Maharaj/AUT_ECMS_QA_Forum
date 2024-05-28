package Question_3;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Node> nodes;

    public Path() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(node.getName()).append(" -> ");
        }
        if (!nodes.isEmpty()) {
            sb.setLength(sb.length() - 4); // Remove the last " -> "
        }
        return sb.toString();
    }
}
