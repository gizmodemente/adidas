package trial.adidas.itineraries.services.impl;

import org.springframework.stereotype.Service;
import trial.adidas.itineraries.model.Connection;
import trial.adidas.itineraries.model.Itinerary;
import trial.adidas.itineraries.model.graph.Graph;
import trial.adidas.itineraries.model.graph.Node;
import trial.adidas.itineraries.services.GraphService;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GraphServiceDefault implements GraphService {

    @Override
    public Graph buildGraph(List<Connection> connections) {
        Graph graph = new Graph();

        // Source and destination cities are Nodes in the graph
        Set<String> cities = connections.stream().map(Connection::getCity).distinct().collect(Collectors.toSet());
        Set<String> citiesDestination = connections.stream().map(Connection::getDestination).distinct().collect(Collectors.toSet());

        cities.addAll(citiesDestination);

        cities.forEach(city ->
                graph.getNodes().put(city, new Node(city)));

        // Creating weighted edges
        connections.forEach(connection -> {
            graph.getNodes().get(connection.getCity()).addDestination(
                    graph.getNodes().get(connection.getDestination()),
                    calculateDistance(connection.getDepartureTime(), connection.getArrivalTime()));
        });

        return graph;
    }

    @Override
    public Itinerary fasterItinerary(Graph graph, String source, String destination) {
        calculateShortestPathFromSource(graph, graph.getNodes().get(source), false);

        Node destinationNode = graph.getNodes().get(destination);

        Itinerary itinerary = new Itinerary();
        itinerary.setConnections(destinationNode.getShortestPath().size());
        itinerary.setRoute(destinationNode.getShortestPath().stream().map(Node::getName).collect(Collectors.toList()));
        itinerary.setTime(destinationNode.getDistance());

        return itinerary;
    }

    @Override
    public Itinerary mostDirectItinerary(Graph graph, String source, String destination) {
        calculateShortestPathFromSource(graph, graph.getNodes().get(source), true);

        Node destinationNode = graph.getNodes().get(destination);

        Itinerary itinerary = new Itinerary();
        itinerary.setConnections(destinationNode.getDistance());
        itinerary.setRoute(destinationNode.getShortestPath().stream().map(Node::getName).collect(Collectors.toList()));

        return itinerary;
    }

    private static Integer calculateDistance(LocalTime first, LocalTime second) {
        Duration duration = Duration.between(first, second);
        if(duration.isNegative()) duration = duration.plusHours(24);
        return Math.toIntExact(duration.toMillis() / 1000);
    }

    private static void calculateShortestPathFromSource(Graph graph, Node source, Boolean ignoreWeight) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< Node, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, ignoreWeight ? 1 : edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void CalculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
