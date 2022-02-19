public class Ray {

    public Point origin;
    public Vector direction;

    public Ray(Point origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Vector getDirection() {
        return direction;
    }

    public Point getOrigin() {
        return origin;
    }
}
