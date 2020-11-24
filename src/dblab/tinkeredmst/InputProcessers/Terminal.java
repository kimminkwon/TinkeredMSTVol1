package dblab.tinkeredmst.InputProcessers;


public class Terminal {
    private int xCoor;
    private int yCoor;
    private int numOfPartition;
    private Partition partition;

    public Terminal(int xCoor, int yCoor, int numOfPartition, Partition partition) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.numOfPartition = numOfPartition;
        this.partition = partition;
    }

    public int getxCoor() {
        return xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public int getNumOfPartition() {
        return numOfPartition;
    }

    public Partition getPartition() {
        return partition;
    }
}
