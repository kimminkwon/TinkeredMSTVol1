package dblab.tinkeredmst.InputProcessers;


public class Terminal {
    private double xCoor;
    private double yCoor;
    private int numOfPartition;
    private Partition partition;

    public Terminal(double xCoor, double yCoor, int numOfPartition, Partition partition) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.numOfPartition = numOfPartition;
        this.partition = partition;
    }

    public double getxCoor() {
        return xCoor;
    }

    public double getyCoor() {
        return yCoor;
    }

    public int getNumOfPartition() {
        return numOfPartition;
    }

    public Partition getPartition() {
        return partition;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "(" + xCoor + ", " + yCoor + ") numOfPartition=" + numOfPartition + "}";
    }
}
