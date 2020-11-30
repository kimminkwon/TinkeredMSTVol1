package dblab.tinkeredmst.Results;

import dblab.tinkeredmst.BenchmarkModel.ResultOfBenchmarkModel;
import dblab.tinkeredmst.TinkeredMST.ResultOfTinkeredMST;

public class CompareTwoResults {
    private ResultOfBenchmarkModel benchmarkModel;
    private ResultOfTinkeredMST tinkeredMST;

    public CompareTwoResults(ResultOfBenchmarkModel benchmarkModel, ResultOfTinkeredMST tinkeredMST) {
        this.benchmarkModel = benchmarkModel;
        this.tinkeredMST = tinkeredMST;
    }

    // 반환은 백분율로: 기댓값 100%보다 크지만 100%에 근접
    public double getResultOfLength() {
        return (tinkeredMST.getUsingLength() / benchmarkModel.getUsingLength()) * 100.0;
    }

    // 기댓값: 100%보다 훨씬 작은 값
    public double getResultOfTime() {
        return (tinkeredMST.getTime() / benchmarkModel.getTime()) * 100.0;
    }
}
