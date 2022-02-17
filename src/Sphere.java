public class Sphere extends Entity {
    private Point wCenter;
    private Point nCenter;
    private float radius;
    private Color baseColor;

    @Override
    public boolean intersect(Ray ray) {
        Vector direction = ray.getDirection();
        float rx = direction.xNormalized();
        float ry = direction.yNormalized();
        float rz = direction.zNormalized();

        double xDiff = ray.origin.x - center.x;
        double yDiff = ray.origin.y - center.y;
        double zDiff = ray.origin.z - center.z;

        double A = rx * rx + ry * ry + rz * rz;
        double B = 2 * ( rx * (xDiff) + ry * (yDiff) + rz * ()  );
        double C = xDiff * xDiff + yDiff * yDiff + zDiff * zDiff - radius * radius;

        double D = B * B - 4 * A * C;

        if (D < 0) { // no roots
            return false;
        } else if (D > 0) { // two roots
            return true;
        } else {
            return true;
        }
    }
}
