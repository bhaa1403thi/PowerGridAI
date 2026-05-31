package com.powergridai;

// =========================================================================
// CO4: Advanced Algorithm Optimization - Dynamic Programming (DP)
// TOPIC: 0/1 Knapsack Algorithm for Systematic Grid Capacity Allocation
// =========================================================================
public class DispatchOptimizer {

    // CO4: Dynamic Programming Matrix formulation minimizing spatial resource constraints
    public static void optimizeGenerationPlanning(int[] capacities, int[] priorityScores, int maxSafeCapacity) {
        int n = capacities.length;
        int[][] dpTable = new int[n + 1][maxSafeCapacity + 1];

        // Process grid calculations down via a bottom-up approach
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= maxSafeCapacity; w++) {
                if (capacities[i - 1] <= w) {
                    dpTable[i][w] = Math.max(priorityScores[i - 1] + dpTable[i - 1][w - capacities[i - 1]], 
                                            dpTable[i - 1][w]);
                } else {
                    dpTable[i][w] = dpTable[i - 1][w];
                }
            }
        }

        System.out.println("\n--- [CO4: Dynamic Programming 0/1 Knapsack] Demand Allocation Engine ---");
        System.out.println("  > Substation safe capacity threshold setting limit: " + maxSafeCapacity + " MW");
        System.out.println("  > Calculated maximum stable priority score achievable: " + dpTable[n][maxSafeCapacity]);
        System.out.println("------------------------------------------------------------------------");
    }
}