package dblab.tinkeredmst;

import dblab.tinkeredmst.BenchmarkModel.ResultOfBenchmarkModel;
import dblab.tinkeredmst.InputProcessers.*;
import dblab.tinkeredmst.MST.MST;
import dblab.tinkeredmst.MST.MST_UsingDistArr;
import dblab.tinkeredmst.Results.CompareTwoResults;
import dblab.tinkeredmst.TinkeredMST.*;

import java.util.Arrays;
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

            long start = System.currentTimeMillis();

        // 4. 각 Partition 별 거리를 저장한다.
        DistanceOfEachPartitions distanceOfEachPartitions = makeDistanceOfEachPartitions(inputProceeser);

        // 5. Partition 사이 MST를 구축한다.
        double totalLengthOfEachPartitions = makeTotalLengthOfEachPartitions(resultsOfEachPartition);
        double connectingLengthOfTinkeredMST = makeResultOfTinkeredMST(distanceOfEachPartitions);

            long end = System.currentTimeMillis();
            double timeOfTinkeredMST = (end - start) / 1000.0;

        printForCheck3(resultOfBenchmarkModel, totalLengthOfEachPartitions, connectingLengthOfTinkeredMST);
        ResultOfTinkeredMST resultOfTinkeredMST = makeResultOfTinkeredMST(timeOfTinkeredMST, totalLengthOfEachPartitions + connectingLengthOfTinkeredMST);

        // 6. Benchmark model과 TinkeredMST의 Result를 통합관리
        CompareTwoResults compareTwoResults = new CompareTwoResults(resultOfBenchmarkModel, resultOfTinkeredMST);
        printForCheck4(compareTwoResults);
    }

    private static void printForCheck4(CompareTwoResults compareTwoResults) {
        System.out.println();
        System.out.println("============================== RESULT ==============================");
        System.out.println("* RESULT OF TIME: " + compareTwoResults.getResultOfTime());
        System.out.println("* RESULT OF LENGTH: " + compareTwoResults.getResultOfLength());
    }

    private static ResultOfTinkeredMST makeResultOfTinkeredMST(double timeOfTinkeredMST, double finalResultLengthForTinkeredMST) {
        return new ResultOfTinkeredMST(timeOfTinkeredMST, finalResultLengthForTinkeredMST);
    }

    private static void printForCheck3(ResultOfBenchmarkModel resultOfBenchmarkModel, double totalLengthOfEachPartitions, double connectingLengthOfTinkeredMST) {
        System.out.println("* length of benchmarkModel: " + resultOfBenchmarkModel.getUsingLength());
        System.out.println("* Final result length for Tinkered MST: " + (connectingLengthOfTinkeredMST + totalLengthOfEachPartitions));
        System.out.println("    - Total length for partitions(not connecting each partitions): " + totalLengthOfEachPartitions);
        System.out.println("    - Connecting length for partitions: " + connectingLengthOfTinkeredMST);
        System.out.println("* Ratio for two results: " + ((connectingLengthOfTinkeredMST + totalLengthOfEachPartitions) / resultOfBenchmarkModel.getUsingLength() * 100.0));
    }

    private static double makeTotalLengthOfEachPartitions(ResultsOfEachPartitions resultsOfEachPartition) {
        double totalLengthOfEachPartitions = 0;
        for (int i = 0; i < Consts.NUMOFPARTITIONS; i++) {
            totalLengthOfEachPartitions += resultsOfEachPartition.getPartitionResultMap(i).getUsingLength();
        }
        return totalLengthOfEachPartitions;
    }

    private static double makeResultOfTinkeredMST(DistanceOfEachPartitions distanceOfEachPartitions) {
        MST mst = new MST_UsingDistArr(distanceOfEachPartitions.getDistanceOfEachPartitions());
        return mst.getMSTResult()[1];
    }

    private static void printForCheck2(DistanceOfEachPartitions distanceOfEachPartitions) {
        for(int i = 0; i < Consts.NUMOFPARTITIONS; i++) {
            System.out.println(Arrays.toString(distanceOfEachPartitions.getDistanceOfEachPartitions()[i]));
        }
    }

    private static DistanceOfEachPartitions makeDistanceOfEachPartitions(InputProceeser inputProceeser) {
        DistanceOfEachPartitions distanceOfEachPartitions = new DistanceOfEachPartitions();
        for(Partition p : inputProceeser.getPartitionStatus()) {
            for(AdjPartition adjP : p.getAdjPartitions()) {
                if(distanceOfEachPartitions.getDistanceOfTwoPartition(p.getNumber(), adjP.getNumber()) == 0.0) {
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
}
