import java.util.ArrayList;

public class World {
    BoundingBox boundingBox;
    private Camera camera;

    ArrayList<Entity> worldObjects;

    public World() {
        this.worldObjects = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        this.worldObjects.add(entity);
    }

    public void simulate() {

        this.camera.takeASnap();
        this.camera.generateImage();
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
        this.camera.setWorld(this);
    }

    /**
     * check if any of the object is intersecting
     * 
     * @param ray
     * @return Entity that intersects the first with the ray
     */
    public Entity checkIntersection(Ray ray) {
        Entity nearestEntity = null;
        double nearestDistance = 100000000;
        IntersectionDetails bestIntersection = new IntersectionDetails(100000000);
        for (Entity entity : worldObjects) {
            IntersectionDetails intersection = entity.intersect(ray);
            if (intersection.distance > 0) {
                if (intersection.distance < nearestDistance) {
                    nearestEntity = entity;
                    nearestDistance = intersection.distance;
                    bestIntersection = intersection;
                }
            }
        }

        if (nearestDistance > 0) {
            return nearestEntity;
        } else {
            return null;
        }
    }

}
