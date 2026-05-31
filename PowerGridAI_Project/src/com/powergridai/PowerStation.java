package com.powergridai;

// =========================================================================
// CO5: Practical Application - Core Domain Object for Infrastructure Data
// =========================================================================
public class PowerStation {
    public int stationId;
    public String name;
    public double currentOutput; // in MW
    public double maxCapacity;   // in MW

    public PowerStation(int stationId, String name, double currentOutput, double maxCapacity) {
        this.stationId = stationId;
        this.name = name;
        this.currentOutput = currentOutput;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return String.format("Station ID: %-4d | Name: %-20s | Load: %6.1f / %6.1f MW", 
                stationId, name, currentOutput, maxCapacity);
    }
}