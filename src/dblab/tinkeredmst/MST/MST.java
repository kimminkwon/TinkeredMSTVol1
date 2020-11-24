package dblab.tinkeredmst.MST;

public abstract class MST {
    protected abstract double makeMST();

    public double[] getMSTResult() {
        long start = System.currentTimeMillis();
        double resultOfLength = makeMST();
        long end = System.currentTimeMillis();

        // 0: 실행시간, 1: 사용한 길이
        return new double[]{(end-start)/1000.0, resultOfLength};
    }
}
