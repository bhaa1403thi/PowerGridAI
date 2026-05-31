package com.powergridai;

import java.util.Scanner;

// =========================================================================
// CO5: Practical Application - Interactive Evaluation & Testing Interface
// =========================================================================
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initialize our core engines behind the scenes
        CentralRegistry databaseRegistry = new CentralRegistry();
        RegionalAnalytics diagnosticEngine = new RegionalAnalytics();
        TransmissionNetwork operationalGrid = new TransmissionNetwork();

        // Pre-fill some baseline graph infrastructure data so the evaluator doesn't have to type everything from scratch
        operationalGrid.addTransmissionLine(1, 2, 1.2);
        operationalGrid.addTransmissionLine(2, 3, 0.8);
        operationalGrid.addTransmissionLine(1, 3, 3.9);
        operationalGrid.addTransmissionLine(3, 4, 2.5);

        while (true) {
            System.out.println("\n==========================================================================");
            System.out.println("                 PowerGridAI Optimization Control Panel                   ");
            System.out.println("==========================================================================");
            System.out.println("1. [CO1] Register & View Power Stations (BST)");
            System.out.println("2. [CO2] Query Peak Load in Regions (Segment Tree)");
            System.out.println("3. [CO2/CO3] Analyze Network Routing & Topology (BFS/Kruskal/Dijkstra)");
            System.out.println("4. [CO4] Optimize Plant Capacity Dispatching (0/1 Knapsack DP)");
            System.out.println("5. Exit Simulation");
            System.out.print("Select a module to evaluate (1-5): ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("\n--- [Module 1: Hierarchical BST Registry] ---");
                    System.out.print("Enter New Station ID (Integer): ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Enter Station Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Current Output (MW): ");
                    double output = scanner.nextDouble();
                    System.out.print("Enter Max Capacity (MW): ");
                    double capacity = scanner.nextDouble();

                    // Insert the evaluator's input live into the BST
                    databaseRegistry.registerStation(new PowerStation(id, name, output, capacity));
                    
                    // Display the updated tree structure report
                    databaseRegistry.printRegistryReport();
                    break;

                case 2:
                    System.out.println("\n--- [Module 2: Segment Tree Range Queries] ---");
                    System.out.print("How many grid regions do you want to profile? ");
                    int numRegions = scanner.nextInt();
                    int[] demands = new int[numRegions];
                    
                    for (int i = 0; i < numRegions; i++) {
                        System.out.print("  Enter active power demand for Region Index [" + i + "] (MW): ");
                        demands[i] = scanner.nextInt();
                    }
                    
                    diagnosticEngine.buildSegmentTree(demands);
                    
                    System.out.println("\n--- Segment Tree Built Successfully ---");
                    System.out.print("Enter Start Region Index for query: ");
                    int start = scanner.nextInt();
                    System.out.print("Enter End Region Index for query: ");
                    int end = scanner.nextInt();
                    
                    if (start >= 0 && end < numRegions && start <= end) {
                        int peak = diagnosticEngine.queryPeakDemand(start, end);
                        System.out.printf("  > Result: Peak load across indices [%d to %d] is: %d MW\n", start, end, peak);
                    } else {
                        System.out.println("  > Invalid range bounds entered.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- [Module 3: Graph Routing Engine] ---");
                    System.out.println("Current active substations in memory: 1, 2, 3, 4");
                    System.out.print("Enter Source Substation ID: ");
                    int src = scanner.nextInt();
                    System.out.print("Enter Destination Substation ID: ");
                    int dest = scanner.nextInt();

                    // Run the graph calculations live based on user input
                    boolean isLinked = operationalGrid.verifyConnectivityBFS(src, dest);
                    System.out.printf("\n  > BFS Connectivity Path Clearance: %s\n", (isLinked ? "PASS (Connected)" : "FAIL (Disconnected)"));
                    
                    if (isLinked) {
                        operationalGrid.optimizeRoutingDijkstra(src, dest);
                    }
                    
                    operationalGrid.designOptimalGridKruskal();
                    break;

                case 4:
                    System.out.println("\n--- [Module 4: Dynamic Programming Knapsack Optimizer] ---");
                    System.out.print("Enter the number of available backup power plants: ");
                    int n = scanner.nextInt();
                    int[] capacities = new int[n];
                    int[] priorities = new int[n];
                    
                    for (int i = 0; i < n; i++) {
                        System.out.println("  Backup Plant #" + (i + 1) + ":");
                        System.out.print("    Enter capacity generation (MW): ");
                        capacities[i] = scanner.nextInt();
                        System.out.print("    Enter grid stability priority score (1-100): ");
                        priorities[i] = scanner.nextInt();
                    }
                    
                    System.out.print("Enter target substation's maximum safe capacity threshold limit (MW): ");
                    int maxLimit = scanner.nextInt();
                    
                    DispatchOptimizer.optimizeGenerationPlanning(capacities, priorities, maxLimit);
                    break;

                case 5:
                    System.out.println("\nExiting System Dashboard. Simulation Terminated.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid selection. Choose an option between 1 and 5.");
            }
        }
    }
}