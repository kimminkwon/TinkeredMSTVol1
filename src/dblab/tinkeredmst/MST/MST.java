package dblab.tinkeredmst.MST;

public abstract class MST {
    protected abstract double makeMST();

    public double[] getMSTResult() {
        long start = System.currentTimeMillis();
        double resultOfLength = makeMST();

        // 시간 측정 유효성 검사
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        // 0: 실행시간, 1: 사용한 길이
        return new double[]{(end-start)/1000.0, resultOfLength};
    }
}
