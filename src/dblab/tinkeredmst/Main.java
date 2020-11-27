package dblab.tinkeredmst;

import dblab.tinkeredmst.BenchmarkModel.ResultOfBenchmarkModel;
import dblab.tinkeredmst.InputProcessers.*;
import dblab.tinkeredmst.MST.MST;
import dblab.tinkeredmst.MST.MST_UsingDistArr;
import dblab.tinkeredmst.TinkeredMST.CalculateForDistanceTwoPartitions;
import dblab.tinkeredmst.TinkeredMST.DistanceOfEachPartitions;
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

        // 3. 각 Partition 별로 Sub MST를 만든다.
        ResultsOfEachPartitions resultsOfEachPartition = makeResultsOfEachPartitions(inputProceeser.getPartitionStatus());

        // ============================== 현재까지 값 확인 ============================== //
        printForCheck1(resultOfBenchmarkModel, resultsOfEachPartition);
        // =========================================================================== //

        // 4. 각 Partition 별 거리를 저장한다.
        DistanceOfEachPartitions distanceOfEachPartitions = makeDistanceOfEachPartitions(inputProceeser);



    }

    private static DistanceOfEachPartitions makeDistanceOfEachPartitions(InputProceeser inputProceeser) {
        DistanceOfEachPartitions distanceOfEachPartitions = new DistanceOfEachPartitions();
        for(Partition p : inputProceeser.getPartitionStatus()) {
            for(AdjPartition adjP : p.getAdjPartitions()) {
                if(distanceOfEachPartitions.getDistanceOfTwoPartition(p.getNumber(), adjP.getNumber()) == 0) {
                    CalculateForDistanceTwoPartitions distanceTwoPartitions = new CalculateForDistanceTwoPartitions(p, inputProceeser.getPartitionStatus().get(adjP.getNumber()), adjP);
                    double distance = distanceTwoPartitions.getDistance();
                    distanceOfEachPartitions.setDistanceOfTwoPartition(p.getNumber(), adjP.getNumber(), distance);
                    distanceOfEachPartitions.setDistanceOfTwoPartition(adjP.getNumber(), p.getNumber(), distance);
                }
            }
        }

        return distanceOfEachPartitions;
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

    private static void printForCheck1(ResultOfBenchmarkModel resultOfBenchmarkModel, ResultsOfEachPartitions resultsOfEachPartition) {
        double box = 0;
        for (int i = 0; i < Consts.NUMOFPARTITIONS; i++) {
            box += resultsOfEachPartition.getPartitionResultMap(i).getUsingLength();
        }

        System.out.println("* length of benchmarkModel: " + resultOfBenchmarkModel.getUsingLength());
        System.out.println("* Total length for partitions(not connecting each partitions): " + box);
        System.out.println("* Ratio of benchmark & total length for partitions: " + ( (box / resultOfBenchmarkModel.getUsingLength()) * 100.0 ));
    }


}
