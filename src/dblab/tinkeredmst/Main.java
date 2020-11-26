package dblab.tinkeredmst;

import dblab.tinkeredmst.BenchmarkModel.ResultOfBenchmarkModel;
import dblab.tinkeredmst.InputProcessers.Consts;
import dblab.tinkeredmst.InputProcessers.InputProceeser;
import dblab.tinkeredmst.InputProcessers.Partition;
import dblab.tinkeredmst.InputProcessers.Terminal;
import dblab.tinkeredmst.MST.MST;
import dblab.tinkeredmst.MST.MST_UsingDistArr;
import dblab.tinkeredmst.TinkeredMST.ResultOfEachPartition;
import dblab.tinkeredmst.TinkeredMST.ResultsOfEachPartitions;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // 1. INSTANCE 받기
        InputProceeser inputProceeser = makeInstance();

        // 2. Benchmark Model 만들기
        ResultOfBenchmarkModel resultOfBenchmarkModel = makeBenchmarkModel(inputProceeser.getTerminalStatus());

        System.out.println("TIME: " +resultOfBenchmarkModel.getTime());
        System.out.println("LENGTH: " +resultOfBenchmarkModel.getUsingLength());

        // 3. 각 Partition 별로 Sub MST를 만든다.
        ResultsOfEachPartitions resultsOfEachPartition = makeResultsOfEachPartitions(inputProceeser.getPartitionStatus());

        for(int i = 0; i < Consts.NUMOFPARTITIONS; i++) {
            System.out.println(resultsOfEachPartition.getPartitionResultMap(i));
        }
    }

    private static ResultsOfEachPartitions makeResultsOfEachPartitions(List<Partition> partitionStatus) {
        MST mst;
        ResultsOfEachPartitions resultsOfEachPartition = new ResultsOfEachPartitions();
        for(Partition p : partitionStatus) {
            mst = new MST_UsingDistArr(p.getTerminalStatus());
            ResultOfEachPartition resultOfEachPartition = new ResultOfEachPartition(p, mst.getMSTResult());
            resultsOfEachPartition.addResultOfEachPartition(resultOfEachPartition);
        }

        return resultsOfEachPartition;
    }

    private static ResultOfBenchmarkModel makeBenchmarkModel(List<Terminal> terminalStatus) {
        MST mst = new MST_UsingDistArr(terminalStatus);
        return new ResultOfBenchmarkModel(mst.getMSTResult());
    }

    private static InputProceeser makeInstance() {
        Scanner input = new Scanner(System.in);
        String path = input.nextLine();
        InputProceeser inputProceeser = new InputProceeser(path);

        return inputProceeser;
    }
}
