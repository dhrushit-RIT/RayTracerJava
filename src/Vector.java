public class Vector {
    public float x;
    public float y;
    public float z;

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.magnitude = (float) Math.sqrt(x * x + y * y + z * z);
    }

    private float magnitude;
//    private float

    public float xComponent() {
        return x;
    }

    /**
     * make sure the magnitude does not go to 0
     *
     * @return
     */
    public float xNormalized() {

        return x / magnitude;
    }

    public float yNormalized() {

        return y / magnitude;
    }

    public float zNormalized() {

        return z / magnitude;
    }
}
