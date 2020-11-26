package dblab.tinkeredmst.MST;

import dblab.tinkeredmst.InputProcessers.Terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MST_UsingDistArr extends MST{
    private double[][] distance;
    private int numOfComponent;
    private List<Terminal> terminalStatus;

    public MST_UsingDistArr(double[][] distance) {
        this.distance = distance;
        this.numOfComponent = distance.length;
    }

    public MST_UsingDistArr(List<Terminal> terminalStatus) {
        this.terminalStatus = terminalStatus;
        makeDistance();
    }

    private void makeDistance() {
        distance = new double[terminalStatus.size()][terminalStatus.size()];
        numOfComponent = terminalStatus.size();

        for(int i = 0; i < numOfComponent; i++) {
            for(int j = i; j < numOfComponent; j++) {
                distance[i][j] = calculateDistance(terminalStatus.get(i), terminalStatus.get(j));
                distance[j][i] = distance[i][j];
            }
        }

        for(int i = 0; i < numOfComponent; i++) {
            System.out.println(i + "-th TERMINALS DISTANCE===========================================");
            System.out.println(Arrays.toString(distance[i]));
        }
    }

    private double calculateDistance(Terminal t1, Terminal t2) {
        return Math.sqrt(Math.pow(Math.abs(t2.getxCoor() - t1.getxCoor()), 2) + Math.pow(Math.abs(t2.getyCoor() - t1.getyCoor()), 2));
    }


    @Override
    public double makeMST() {
        // 1. Preprocessing
        double result = 0;
        boolean[] selected = new boolean[numOfComponent];
        double[] minDist = new double[numOfComponent];
        Arrays.fill(selected, false);
        Arrays.fill(minDist, Double.MAX_VALUE);

        // 2. 시작점 설정
        int selectedIndex = 0;
        selected[selectedIndex] = true;

        // 3. Prim Algorithm
        for(int k = 1; k < numOfComponent; k++) {
            // 1) 거리 값 업데이트
            for(int i = 0; i < numOfComponent; i++)
                minDist[i] = minDist[i] > distance[selectedIndex][i] ? distance[selectedIndex][i] : minDist[i];

            // 2) 최소 거리 찾기
            double min = Double.MAX_VALUE;
            for(int i = 0; i < numOfComponent; i++) {
                if(min > minDist[i] && selected[i] == false) {
                    min = minDist[i];
                    selectedIndex = i;
                }
            }

            // 3) 최소 거리인 Terminal을 포함하고 거리 값, 선택여부 업데이트
            result = result + min;
            selected[selectedIndex] = true;
        }

        return result;
    }
}
