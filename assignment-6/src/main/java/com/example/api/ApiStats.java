package com.example.api;


public class ApiStats {
    private long callCount;
    private long totalExecutionTime;
    private long maxExecutionTime;
    private long minExecutionTime = Long.MAX_VALUE;
    private long errorCount;

    public synchronized void incrementCallCount() {
        callCount++;
    }

    public synchronized void updateExecutionTime(long executionTime) {
        totalExecutionTime += executionTime;
        if (executionTime > maxExecutionTime) {
            maxExecutionTime = executionTime;
        }
        if (executionTime < minExecutionTime) {
            minExecutionTime = executionTime;
        }
    }

    public synchronized void incrementErrorCount() {
        errorCount++;
    }

    public long getCallCount() {
        return callCount;
    }

    public double getAverageExecutionTime() {
        if (callCount > 0) {
            return (double) totalExecutionTime / callCount;
        } else {
            return 0;
        }
    }

    public long getMaxExecutionTime() {
        return maxExecutionTime;
    }

    public long getMinExecutionTime() {
        return minExecutionTime;
    }

    public long getErrorCount() {
        return errorCount;
    }
}
