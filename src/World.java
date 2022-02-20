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
        Entity nearestEntity;
        double nearestDistance = -1;
        for (Entity entity : worldObjects) {
            double dist = entity.intersect(ray)
            if (dist > 0) {
                if (dist < nearestDistance){
                    nearestEntity = entity;
                    nearestDistance = dist;
                }
            }
        }

        if (nearestDistance > 0) {
            return nearestEntity;
        }
        return null;
    }

}
