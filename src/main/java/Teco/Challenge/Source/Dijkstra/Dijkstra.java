package Teco.Challenge.Source.Dijkstra;

import java.util.*;

public class Dijkstra {
    private final Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    public Map<String, Integer> shortestPath(String start) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String node : graph.nodes.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (!visited.add(current.name)) {
                continue;
            }

            for (Node neighbor : graph.getNeighbors(current.name)) {
                int newDist = distances.get(current.name) + neighbor.weight;
                if (newDist < distances.get(neighbor.name)) {
                    distances.put(neighbor.name, newDist);
                    pq.add(new Node(neighbor.name, newDist));
                    previous.put(neighbor.name, current.name);
                }
            }
        }

        return distances;
    }

    List<String> getPath(String start, String end, Map<String, String> previous) {
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}