import org.ejml.simple.SimpleMatrix;

public class Point {
    public double x;
    public double y;
    public double z;
    public SimpleMatrix matrix;

    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;

        this.matrix = new SimpleMatrix(other.matrix);
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.matrix = new SimpleMatrix(new double[][] { { x }, { y }, { z }, { 1.0 } });
    }

    public static Point getPointFromMatrix(SimpleMatrix matrix) {
        if (matrix.numCols() == 1 && matrix.numRows() >= 3) {
            // TODO : might need to add fourth dimension to matrix and use x/w, y/w and z/w
            // instead of x,y,y
            return new Point(matrix.get(0, 0), matrix.get(1, 0), matrix.get(2, 0));
        } else if (matrix.numRows() == 1 && matrix.numCols() >= 3) {
            return new Point(matrix.get(0, 0), matrix.get(0, 1), matrix.get(0, 2));
        } else {
            System.out.println("Not a valid matrix to create a vector");
            return null;
        }
    }

    public String toString() {
        return "P: {" + this.x + " " + this.y + " " + this.z + "}";
    }
}
