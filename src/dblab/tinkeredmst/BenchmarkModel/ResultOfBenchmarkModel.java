package dblab.tinkeredmst.BenchmarkModel;

public class ResultOfBenchmarkModel {
    private double time;
    private double usingLength;

    public ResultOfBenchmarkModel(double time, double usingLength) {
        this.time = time;
        this.usingLength = usingLength;
    }

    public double getTime() {
        return time;
    }

    public double getUsingLength() {
        return usingLength;
    }
}
