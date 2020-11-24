package dblab.tinkeredmst.InputProcessers;

import java.util.List;

public class Partition {
    private int number;
    private List<AdjPartition> adjPartitions;
    private List<Terminal> terminalStatus;

    public Partition(int number, List<AdjPartition> adjPartitions, List<Terminal> terminalStatus) {
        this.number = number;
        this.adjPartitions = adjPartitions;
        this.terminalStatus = terminalStatus;
    }

    public int getNumber() {
        return number;
    }

    public List<AdjPartition> getAdjPartitions() {
        return adjPartitions;
    }

    public List<Terminal> getTerminalStatus() {
        return terminalStatus;
    }
}
