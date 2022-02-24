public class IntersectionDetails {
    public double distance;
    public Point intersectionPoint;
    public Vector normalAtIntersection;

    public IntersectionDetails(double distance){
        this.distance = distance;
    }

    public IntersectionDetails() {
        this.distance = -1;
        this.intersectionPoint = null;
        this.normalAtIntersection = null;
    }
}
