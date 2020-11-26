package dblab.tinkeredmst.TinkeredMST;

import java.util.HashMap;
import java.util.Map;

public class ResultsOfEachPartitions {
    private Map<Integer, ResultOfEachPartition> partitionResultMap;

    public ResultsOfEachPartitions() {
        this.partitionResultMap = new HashMap<>();
    }

    public void addResultOfEachPartition(ResultOfEachPartition resultOfEachPartition) {
        partitionResultMap.put(resultOfEachPartition.getPartitionNumber(), resultOfEachPartition);
    }

    public ResultOfEachPartition getPartitionResultMap(int number) {
        return partitionResultMap.get(number);
    }
}
