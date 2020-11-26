package dblab.tinkeredmst.BenchmarkModel;

public class ResultOfBenchmarkModel {
    private double time;
    private double usingLength;

    public ResultOfBenchmarkModel(double[] results) {
        this.time = results[0];
        this.usingLength = results[1];
    }

    public double getTime() {
        return time;
    }

    public double getUsingLength() {
        return usingLength;
    }
}
