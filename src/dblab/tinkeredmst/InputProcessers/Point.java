package dblab.tinkeredmst.InputProcessers;

public class Point {
    private double xCoor;
    private double yCoor;

    public Point(int xCoor, int yCoor) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }

    public double getxCoor() {
        return xCoor;
    }

    public double getyCoor() {
        return yCoor;
    }

    @Override
    public String toString() {
        return "(" + xCoor + ", " + yCoor + ")";
    }
}
