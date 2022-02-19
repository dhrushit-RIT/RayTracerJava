
public class Sphere extends Entity {

    private Point wCenter;
    private Point nCenter;

    private double radius;

    public Sphere(Point wCenter, double radius, Color baseColor) {
        super(baseColor, new Point(wCenter));
        this.wCenter = wCenter;
        this.radius = radius;
    }

    public boolean intersect(Ray ray) {
        Vector rDirection = ray.getDirection();
        Point rOrigin = ray.getOrigin();

        Point centerCamSpace = Camera.toCameraSpace(wCenter);
        Point rOriginCamSpace = Camera.toCameraSpace(rOrigin);
        Vector rDirectionCamSpace = Camera.toCameraSpace(rDirection);

        // System.out.println(rOriginCamSpace + " " + centerCamSpace);

        double rx = rDirectionCamSpace.xNormalized();
        double ry = rDirectionCamSpace.yNormalized();
        double rz = rDirectionCamSpace.zNormalized();

        double xDiff = rOriginCamSpace.x - centerCamSpace.x;
        double yDiff = rOriginCamSpace.y - centerCamSpace.y;
        double zDiff = rOriginCamSpace.z - centerCamSpace.z;

        double A = rx * rx + ry * ry + rz * rz;
        double B = 2 * (rx * (xDiff) + ry * (yDiff) + rz * (zDiff));
        double C = xDiff * xDiff + yDiff * yDiff + zDiff * zDiff - radius * radius;

        double D = B * B - 4 * A * C;

        // System.out.println("B: " + B + "\nB * B : " + B * B + "\nC : " + C);
        // System.out.println(D);

        if (D < 0) { // no roots
            return false;
        } else if (D > 0) { // two roots
            return true;
        } else {
            return true;
        }
    }
}
