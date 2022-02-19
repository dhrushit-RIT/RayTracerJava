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

    public boolean checkIntersection(Ray ray) {
        for (Entity entity : worldObjects) {
            if (entity.intersect(ray)) {
                return true;
            }
        }
        return false;
    }

}
