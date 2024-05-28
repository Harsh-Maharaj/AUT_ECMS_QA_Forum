package Question_3;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PathFinder {
    private Maze maze;
    private BiConsumer<String, String> explorationCallback;
    private Consumer<List<String>> pathFoundCallback;
    private Runnable pathNotFoundCallback;

    public PathFinder(Maze maze, BiConsumer<String, String> explorationCallback, Consumer<List<String>> pathFoundCallback, Runnable pathNotFoundCallback) {
        this.maze = maze;
        this.explorationCallback = explorationCallback;
        this.pathFoundCallback = pathFoundCallback;
        this.pathNotFoundCallback = pathNotFoundCallback;
    }

    public void findPath(String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Map<String, String> predecessors = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        predecessors.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.println("Visiting: " + current); // Debugging visit

            if (current.equals(end)) {
                List<String> path = buildPath(predecessors, end);
                pathFoundCallback.accept(path);
                return;
            }

            Node currentNode = maze.getNode(current);
            for (Node neighbor : currentNode.getNeighbors()) {
                String neighborName = neighbor.getName();
                if (!visited.contains(neighborName)) {
                    visited.add(neighborName);
                    queue.add(neighborName);
                    predecessors.put(neighborName, current);
                    explorationCallback.accept(current, neighborName);
                    System.out.println("Adding to queue: " + neighborName); // Debugging queue addition
                }
            }
        }

        System.out.println("No path found to: " + end); // Debugging no path found
        pathNotFoundCallback.run();
    }

    private List<String> buildPath(Map<String, String> predecessors, String end) {
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
