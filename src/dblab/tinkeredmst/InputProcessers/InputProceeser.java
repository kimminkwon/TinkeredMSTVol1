package dblab.tinkeredmst.InputProcessers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputProceeser {
    private String instance;
    private List<Terminal> terminalStatus;
    private List<Partition> partitionStatus;

    public InputProceeser(String path) {
        terminalStatus = new ArrayList<>();
        partitionStatus = new ArrayList<>();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            byte[] readBuffer = new byte[fileInputStream.available()];
            while(fileInputStream.read(readBuffer) != -1) {}
            this.instance = new String(readBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        readInstance();
    }

    private void readInstance() {
        String[] splitInstance = instance.split(System.getProperty("line.separator"));

        // 1. Partition의 정보 저장
        for(int i = 0; i < Consts.NUMOFPARTITIONS; i++) {
            partitionStatus.add(makePartition(splitInstance[i]));
        }

        // 2. Terminal의 정보와 Partition 내 terminal의 정보 저장
        int cnt = 0;
        for(int i = Consts.NUMOFPARTITIONS; i < Consts.NUMOFPARTITIONS + Consts.NUMOFTERMINALS; i++) {
            Terminal terminal = makeTerminal(splitInstance[i], cnt);
            terminalStatus.add(terminal);
            partitionStatus.get(terminal.getNumOfPartition()).setTerminalStatus(terminal);
        }

        partitionStatus.stream().forEach(
                partition -> System.out.println(partition)
        );
    }

    private Terminal makeTerminal(String terminalInfo, int num) {
        String[] tInfoArr = terminalInfo.split(" ");
        double xCoor = Double.parseDouble(tInfoArr[0]);
        double yCoor = Double.parseDouble(tInfoArr[1]);
        int numOfPartition = Integer.parseInt(tInfoArr[2]);

        Terminal terminal = new Terminal(xCoor, yCoor, numOfPartition, partitionStatus.get(numOfPartition));
        return terminal;
    }

    private Partition makePartition(String patitionInfo) {
        String[] pInfoArr = patitionInfo.split(" ");
        Partition partition = new Partition(Integer.parseInt(pInfoArr[0]));

        int cnt = 2;
        for(int i = 0; i < Integer.parseInt(pInfoArr[1]); i++) {
            int adjNum = Integer.parseInt(pInfoArr[cnt++]);
            Point adjPoint1 = new Point(Integer.parseInt(pInfoArr[cnt++]), Integer.parseInt(pInfoArr[cnt++]));
            Point adjPoint2 = new Point(Integer.parseInt(pInfoArr[cnt++]), Integer.parseInt(pInfoArr[cnt++]));

            AdjPartition adjPartition = new AdjPartition(adjNum, adjPoint1, adjPoint2);
            partition.setAdjPartition(adjPartition);
        }

        return partition;
    }

    public void setTerminalStatus(List<Terminal> terminalStatus) {
        this.terminalStatus = terminalStatus;
    }

    public void setPartitionStatus(List<Partition> partitionStatus) {
        this.partitionStatus = partitionStatus;
    }

    public void addTerminal(Terminal terminal) {
        this.terminalStatus.add(terminal);
    }

    public void addPartititon(Partition partition) {
        this.partitionStatus.add(partition);
    }

    public List<Terminal> getTerminalStatus() {
        return terminalStatus;
    }

    public List<Partition> getPartitionStatus() {
        return partitionStatus;
    }
}
