package net.odpay.altidentifier.fingerprintUtil;

public class PingTracker {

    private int sum;
    private int num;

    public PingTracker() {
        this.sum = 0;
        this.num = 0;
    }

    public void recordPing(int latencyMS) {
        this.sum += latencyMS;
        this.num ++;
    }

    public Object getAvg() {
        if (this.num == 0) { return null; }
        return this.sum/this.num;
    }

    public void reset() {
        this.sum = 0;
        this.num = 0;
    }


}
