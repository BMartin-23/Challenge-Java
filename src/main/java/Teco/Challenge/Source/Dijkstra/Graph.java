package Teco.Challenge.Source.Dijkstra;

import java.util.*;

public class Graph {
    public final Map<String, List<Node>> nodes = new HashMap<>();

    public void addEdge(String source, String destination, int weight) {
        nodes.putIfAbsent(source, new ArrayList<>());
        nodes.putIfAbsent(destination, new ArrayList<>());
        nodes.get(source).add(new Node(destination, weight));
        nodes.get(destination).add(new Node(source, weight)); // Si el grafo es no dirigido
    }

    List<Node> getNeighbors(String node) {
        return nodes.getOrDefault(node, Collections.emptyList());
    }
}
