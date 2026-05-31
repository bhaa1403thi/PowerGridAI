package com.powergridai;

import java.util.*;

// =========================================================================
// CO2 & CO3: Graph Network Topography, Route Logic, and Minimization
// TOPIC: BFS Traversal, Kruskal's MST, and Dijkstra's Shortest Path Matrix
// =========================================================================
public class TransmissionNetwork {
    
    public static class GridEdge {
        int target;
        double lineLoss; // Weight representing line transmission loss %

        public GridEdge(int target, double lineLoss) {
            this.target = target;
            this.lineLoss = lineLoss;
        }
    }

    public static class LinkEdge implements Comparable<LinkEdge> {
        int src, dest;
        double cost;

        public LinkEdge(int src, int dest, double cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(LinkEdge other) {
            return Double.compare(this.cost, other.cost);
        }
    }

    private final Map<Integer, List<GridEdge>> adjList = new HashMap<>();
    private final List<LinkEdge> edgeRegistry = new ArrayList<>();

    public void addTransmissionLine(int u, int v, double lossWeight) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(new GridEdge(v, lossWeight));
        adjList.get(v).add(new GridEdge(u, lossWeight));
        edgeRegistry.add(new LinkEdge(u, v, lossWeight));
    }

    // CO2: Graph Traversal - BFS for Network Connectivity Testing
    public boolean verifyConnectivityBFS(int startNode, int endNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == endNode) return true;

            for (GridEdge edge : adjList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(edge.target)) {
                    visited.add(edge.target);
                    queue.add(edge.target);
                }
            }
        }
        return false;
    }

    // CO2: Infrastructure Design - Kruskal's Minimum Spanning Tree
    public void designOptimalGridKruskal() {
        System.out.println("\n--- [CO2: Kruskal's MST] Constructing Optimal Infrastructure Topology ---");
        Collections.sort(edgeRegistry); // Optimal Sorting Step
        
        Map<Integer, Integer> parentMap = new HashMap<>();
        for (int node : adjList.keySet()) parentMap.put(node, node);

        double totalNetworkLossMetric = 0;
        int edgesDeployed = 0; // The variable causing the issue

        for (LinkEdge edge : edgeRegistry) {
            int rootSrc = findDisjointSet(edge.src, parentMap);
            int rootDest = findDisjointSet(edge.dest, parentMap);

            if (rootSrc != rootDest) {
                System.out.printf("  [Deploy Line] Substation %d <-> Substation %d | Efficiency Loss: %.2f%%\n", 
                        edge.src, edge.dest, edge.cost);
                totalNetworkLossMetric += edge.cost;
                unionDisjointSet(rootSrc, rootDest, parentMap);
                edgesDeployed++; // Incremented here
            }
        }
        // FIX: We now explicitly print the variable so the compiler is happy!
        System.out.printf("Baseline Infrastructure Loss Footprint: %.2f%% over %d active lines.\n", 
                totalNetworkLossMetric, edgesDeployed);
    }

    private int findDisjointSet(int vertex, Map<Integer, Integer> parent) {
        if (parent.get(vertex) == vertex) return vertex;
        return findDisjointSet(parent.get(vertex), parent);
    }

    private void unionDisjointSet(int root1, int root2, Map<Integer, Integer> parent) {
        parent.put(root1, root2);
    }

    // CO3: Routing & Logistics - Dijkstra's Algorithm for Path Optimization
    public void optimizeRoutingDijkstra(int source, int destination) {
        PriorityQueue<GridEdge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.lineLoss));
        Map<Integer, Double> accumulatedLoss = new HashMap<>();

        for (int node : adjList.keySet()) accumulatedLoss.put(node, Double.MAX_VALUE);
        accumulatedLoss.put(source, 0.0);
        pq.add(new GridEdge(source, 0.0));

        while (!pq.isEmpty()) {
            GridEdge current = pq.poll();
            int u = current.target;

            if (u == destination) break;

            for (GridEdge connection : adjList.getOrDefault(u, new ArrayList<>())) {
                double totalLossPath = accumulatedLoss.get(u) + connection.lineLoss;
                if (totalLossPath < accumulatedLoss.get(connection.target)) {
                    accumulatedLoss.put(connection.target, totalLossPath);
                    pq.add(new GridEdge(connection.target, totalLossPath));
                }
            }
        }
        System.out.println("\n--- [CO3: Dijkstra's Algorithm] Real-Time Routing Engine Analysis ---");
        if (accumulatedLoss.get(destination) == Double.MAX_VALUE) {
            System.out.println("  > Crucial Alert: No viable power pathway available between points.");
        } else {
            System.out.printf("  > Pathing Target Succeeded. Safest Routing Total Loss From Node %d to %d: %.2f%%\n", 
                    source, destination, accumulatedLoss.get(destination));
        }
    }
}