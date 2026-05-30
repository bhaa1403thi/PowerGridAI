# PowerGridAI: Smart Energy Distribution Optimization System

PowerGridAI is a production-ready Java simulation framework designed to model, optimize, and secure next-generation urban electrical grids. By translating abstract data structures and algorithm paradigms into structural physical grid components, the system solves complex industrial distribution challenges such as transmission power loss, localized telemetry spikes, and critical resource allocation constraints.

This project has been architected to cleanly satisfy and demonstrate compliance with advanced Data Structures and Algorithms curriculum **Course Outcomes (CO1 - CO5)**.

---

## 🚀 Key Features & Architectural Mapping

### 📁 1. Asset Registry Indexer (CO1)
* **Structure:** Binary Search Tree (BST)
* **File:** `CentralRegistry.java`
* **Purpose:** Handles high-throughput indexing and lookup of generation assets. It guarantees average-case $O(\log n)$ performance boundaries for structural modifications and utilizes an **In-Order Traversal** protocol to extract telemetry reports sorted by unique asset keys.

### 📊 2. Live Telemetry Range Queries (CO2)
* **Structure:** Array-backed Balanced Segment Tree
* **File:** `RegionalAnalytics.java`
* **Purpose:** Profiles and queries active load streams across contiguous geographic distribution zones. It resolves Range Maximum Queries (RMQ) in $O(\log n)$ execution windows, entirely bypassing traditional linear $O(n)$ array scans during emergency load surges.

### 🌐 3. Grid Topology & Connectivity (CO2)
* **Structure:** Adjacency List Graph Matrix
* **File:** `TransmissionNetwork.java`
* **Purpose:** Leverages **Breadth-First Search (BFS)** to run live structural path clearance scans across high-voltage substations to predict potential regional blackouts caused by unexpected transmission link breakdowns.

### 🗺️ 4. Cost-Minimized Infrastructure & Smart Routing (CO2 / CO3)
* **Structure:** Disjoint Set Union (DSU) & Priority Queue Heap
* **File:** `TransmissionNetwork.java`
* **Purpose:** * Implements **Kruskal’s Minimum Spanning Tree (MST)** using a greedy approach to design structural cabling routing layouts with the lowest possible physical deployment cost footprint.
  * Implements **Dijkstra’s Shortest Path** to dynamically route electrical current through paths of least cumulative resistance, bypassing high-loss transmission pathways on the fly.

### 🎯 5. Dynamic Dispatch Capacity Planning (CO4)
* **Structure:** 2D Tabular Dynamic Programming Matrix
* **File:** `DispatchOptimizer.java`
* **Purpose:** Solves the multi-variable capacity allocation constraint problem by adapting the **0/1 Knapsack paradigm**. It processes lists of available standby auxiliary power units down to a bottom-up mathematical grid matrix, maximizing system stability metrics while keeping cumulative generation safe from blowing local transformer thresholds.

---

## 📁 Repository File Structure

```text
PowerGridAI_Project/
└── src/
    └── com/
        └── powergridai/
            ├── PowerStation.java        # Core data object blueprint model
            ├── CentralRegistry.java     # CO1: Hierarchical BST engine
            ├── RegionalAnalytics.java   # CO2: Segment Tree query matrix
            ├── TransmissionNetwork.java # CO2/CO3: Graph routing, BFS, MST, Dijkstra
            ├── DispatchOptimizer.java   # CO4: Dynamic Programming load balancer
            └── Main.java                # CO5: Central execution module & Interactive CLI
