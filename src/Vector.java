import org.ejml.simple.SimpleMatrix;

public class Vector {
    public double x;
    public double y;
    public double z;

    public SimpleMatrix matrix;

    public Vector(Vector other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.magnitude = other.magnitude;
        this.matrix = new SimpleMatrix(other.matrix);
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.updateMagnitude();
        this.matrix = new SimpleMatrix(new double[][] { { x }, { y }, { z }, { 1.0 } });
    }

    private double magnitude;
    // private double

    public double xComponent() {
        return x;
    }

    /**
     * make sure the magnitude does not go to 0
     *
     * @return
     */
    public double xNormalized() {
        if (magnitude != 0)
            return x / magnitude;
        else {
            System.err.println("magnitude is 0");
            return -1;
        }
    }

    public double yNormalized() {
        if (magnitude != 0)
            return y / magnitude;
        else {
            System.err.println("magnitude is 0");
            return -1;
        }
    }

    public double zNormalized() {
        if (magnitude != 0)
            return z / magnitude;
        else {
            System.err.println("magnitude is 0");
            return -1;
        }
    }

    public SimpleMatrix getSimpleMatrix() {
        return this.matrix;
    }

    public static Vector getVectorFromMatrix(SimpleMatrix matrix) {
        if (matrix.numCols() == 1 && matrix.numRows() >= 3) {
            // TODO : might need to add fourth dimension to matrix and use x/w, y/w and z/w
            // instead of x,y,y
            return new Vector(matrix.get(0, 0) / matrix.get(3, 0), matrix.get(1, 0) / matrix.get(3, 0),
                    matrix.get(2, 0) / matrix.get(3, 0));
        } else if (matrix.numRows() == 1 && matrix.numCols() >= 3) {
            return new Vector(matrix.get(0, 0) / matrix.get(0, 3), matrix.get(0, 1) / matrix.get(0, 3),
                    matrix.get(0, 2) / matrix.get(0, 3));
        } else {
            System.out.println("Not a valid matrix to create a vector");
            return null;
        }
    }

    public void updateMagnitude() {
        this.magnitude = (double) Math.sqrt(x * x + y * y + z * z);
    }

    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        this.updateMagnitude();
    }

    public void subtract(Vector v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        this.updateMagnitude();
    }

    public void normalize() {
        if (this.magnitude > 0) {

            this.x /= this.magnitude;
            this.y /= this.magnitude;
            this.z /= this.magnitude;
            this.updateMagnitude();
        }
    }

    public String toString() {
        return "V: {" + this.x + " " + this.y + " " + this.z + "}";
    }

    public Vector copy() {
        return new Vector(this);
    }

    //
    // Static Methods
    //

    public static Vector add(Vector... vectors) {
        Vector ret = defaultVector();

        for (Vector v : vectors) {
            ret.x += v.x;
            ret.y += v.y;
            ret.z += v.z;
        }
        ret.updateMagnitude();
        return ret;
    }

    public static Vector subtract(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static Vector subtract(Point v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static Vector scale(Vector v, double scale) {
        return new Vector(v.x * scale, v.y * scale, v.z * scale);
    }

    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vector add(Point v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vector defaultVector() {
        return new Vector(0, 0, 0);
    }

    public static Vector cross(Vector a, Vector b) {
        return new Vector(
                // 0,0,1 x 1,0,0
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x);
    }

    public static double dot(Vector v1, Vector v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static double dot(Point v1, Vector v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

}
