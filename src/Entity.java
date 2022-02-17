public class Entity {

    BoundingBox boundingBox;

    public boolean intersect(Ray ray) {
        return false;
    }

    public boolean intersect(Entity entity){
        return false;
    }
}
