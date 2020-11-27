package dblab.tinkeredmst.TinkeredMST;

import dblab.tinkeredmst.InputProcessers.Consts;

public class DistanceOfEachPartitions {

    private double[][] distanceOfEachPartitions;

    public DistanceOfEachPartitions() {
        this.distanceOfEachPartitions = new double[Consts.NUMOFPARTITIONS][Consts.NUMOFPARTITIONS];
    }

    public double getDistanceOfTwoPartition(int pNum1, int pNum2) {
        return distanceOfEachPartitions[pNum1][pNum2];
    }

    public void setDistanceOfTwoPartition(int pNum1, int pNum2, double distance) {
        this.distanceOfEachPartitions[pNum1][pNum2] = distance;
    }

    public double[][] getDistanceOfEachPartitions() {
        return distanceOfEachPartitions;
    }
}
