public class Sphere extends Entity {

    private Point wCenter;

    private double radius;

    public Sphere(Point wCenter, double radius, MyColor baseColor) {
        super(baseColor, new Point(wCenter));
        this.wCenter = wCenter;
        this.radius = radius;
    }

    /**
     * point of intersection:
     * ¤ (xi, yi, zi) = (x0 + dx * wi , y0 + dy * wi , z0 + dz * wi )
     * 
     * normal at the point of intersection
     * ¤ (xn, yn, zn) = (xi, yi, zi) - (xc, yc, zc)
     * ¤ = ((xi – xc), (yi – yc), (zi – zc))
     */
    public IntersectionDetails intersect(Ray ray) {
        Vector rDirection = ray.getDirection();
        Point rOrigin = ray.getOrigin();

        Point centerCamSpace = Camera.toCameraSpace(wCenter);
        Point rOriginCamSpace = Camera.toCameraSpace(rOrigin);
        Vector rDirectionCamSpace = Camera.toCameraSpace(rDirection);

        // System.out.println(rOriginCamSpace + " " + centerCamSpace);

        double rx = rDirection.xNormalized();
        double ry = rDirection.yNormalized();
        double rz = rDirection.zNormalized();

        double xDiff = rOriginCamSpace.x - centerCamSpace.x;
        double yDiff = rOriginCamSpace.y - centerCamSpace.y;
        double zDiff = rOriginCamSpace.z - centerCamSpace.z;

        double A = rx * rx + ry * ry + rz * rz;
        double B = 2 * (rx * (xDiff) + ry * (yDiff) + rz * (zDiff));
        double C = xDiff * xDiff + yDiff * yDiff + zDiff * zDiff - radius * radius;

        double D = B * B - 4 * A * C;

        IntersectionDetails intersection = new IntersectionDetails(this);
        double w = -1;
        if (D < 0) { // no roots
            w = -1;
        } else if (D > 0) { // two roots
            D = Math.sqrt(D);
            double w0 = (-B + D) / 2 / A;
            double w1 = (-B - D) / 2 / A;
            // System.out.println(w0 + " " + w1);
            if (w0 > 0 && w1 > 0)
                w = Math.min(w0, w1);
            else if (w0 > 0)
                w = w0;
            else if (w1 > 0)
                w = w1;
            else
                w = -1;
        } else {
            w = -1;
        }

        if(w >= 0) {
            intersection.distance = w;
            intersection.intersectionPoint = new Point(
                rOriginCamSpace.x + rDirectionCamSpace.x * w,
                rOriginCamSpace.y + rDirectionCamSpace.y * w,
                rOriginCamSpace.z + rDirectionCamSpace.z * w,
                Point.Space.CAMERA
            );

            intersection.normalAtIntersection = new Vector(
                intersection.intersectionPoint.x - centerCamSpace.x,
                intersection.intersectionPoint.y - centerCamSpace.y,
                intersection.intersectionPoint.z - centerCamSpace.z
            );
        }
        return intersection;
    }

    // @Override
    // public MyColor getPixelIrradiance(Light light, Camera camera, Point intersection, Vector normal) {
        
    //     return null;
    // }


}
