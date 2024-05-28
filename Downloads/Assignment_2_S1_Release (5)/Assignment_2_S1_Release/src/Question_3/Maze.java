package Question_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Maze {
    private Map<String, Node> nodes;

    public Maze() {
        this.nodes = new HashMap<>();
    }

    public void addNode(Node node) {
        nodes.put(node.getName(), node);
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public Set<Node> getAllNodes() {
        return new HashSet<>(nodes.values());
    }
}
