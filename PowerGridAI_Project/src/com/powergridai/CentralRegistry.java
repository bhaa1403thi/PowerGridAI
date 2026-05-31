package com.powergridai;

// =========================================================================
// CO1: Hierarchical Data Structures - Binary Search Tree (BST)
// TOPIC: BST Construction, O(log n) Search, Insertion, and In-Order Traversal
// =========================================================================
public class CentralRegistry {
    private class Node {
        PowerStation station;
        Node left, right;

        Node(PowerStation station) {
            this.station = station;
        }
    }

    private Node root;

    // CO1: O(log n) Insertion Performance Guarantee
    public void registerStation(PowerStation station) {
        root = insertRec(root, station);
    }

    private Node insertRec(Node root, PowerStation station) {
        if (root == null) return new Node(station);
        if (station.stationId < root.station.stationId)
            root.left = insertRec(root.left, station);
        else if (station.stationId > root.station.stationId)
            root.right = insertRec(root.right, station);
        return root;
    }

    // CO1: O(log n) Direct Search Target Lookup
    public PowerStation findStation(int stationId) {
        return searchRec(root, stationId);
    }

    private PowerStation searchRec(Node root, int stationId) {
        if (root == null || root.station.stationId == stationId) 
            return root == null ? null : root.station;
        if (stationId < root.station.stationId) 
            return searchRec(root.left, stationId);
        return searchRec(root.right, stationId);
    }

    // CO1: In-Order Traversal for Structured Asset Reporting
    public void printRegistryReport() {
        System.out.println("\n--- [CO1: BST In-Order Traversal] System Asset Registry ---");
        inOrder(root);
        System.out.println("---------------------------------------------------------");
    }

    private void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(" " + root.station);
            inOrder(root.right);
        }
    }
}