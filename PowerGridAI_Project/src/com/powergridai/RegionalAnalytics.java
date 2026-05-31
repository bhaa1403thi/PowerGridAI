package com.powergridai;

// =========================================================================
// CO2: Range Query Structures - Segment Tree
// TOPIC: Range Maximum Query (RMQ) for Real-Time Interval Load Analysis
// =========================================================================
public class RegionalAnalytics {
    private int[] tree;
    private int n;

    // CO2: Constructing a balanced Segment Tree interval query system
    public void buildSegmentTree(int[] regionalDemands) {
        this.n = regionalDemands.length;
        this.tree = new int[4 * n];
        build(regionalDemands, 0, 0, n - 1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build(arr, 2 * node + 1, start, mid);
        build(arr, 2 * node + 2, mid + 1, end);
        tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
    }

    // CO2: O(log n) Interval Query execution over database ranges
    public int queryPeakDemand(int queryStart, int queryEnd) {
        return query(0, 0, n - 1, queryStart, queryEnd);
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Integer.MIN_VALUE; // Out of bounds segment
        if (l <= start && end <= r) return tree[node];      // Total structural overlap

        int mid = (start + end) / 2;
        int leftQuery = query(2 * node + 1, start, mid, l, r);
        int rightQuery = query(2 * node + 2, mid + 1, end, l, r);
        return Math.max(leftQuery, rightQuery);
    }
}