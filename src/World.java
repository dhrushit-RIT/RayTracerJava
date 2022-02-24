import java.util.ArrayList;

public class World {
    BoundingBox boundingBox;
    private Camera camera;

    ArrayList<Entity> worldObjects;
    ArrayList<Light> lightSources;

    public World() {
        this.worldObjects = new ArrayList<>();
        this.lightSources = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        this.worldObjects.add(entity);
    }

    public void addLightSource(Light light) {
        this.lightSources.add(light);
    }

    public void simulate() {

        this.camera.takeASnap();
        this.camera.generateImage();
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
        this.camera.setWorld(this);
    }

    public MyColor getPixelIrradiance(Ray ray) {
        IntersectionDetails entityIntersectionDetails = this.checkIntersection(ray);
        for (Light light : lightSources) {
            MyColor color  = entityIntersectionDetails.entity.getPixelIrradiance(light, this.camera, entityIntersectionDetails.intersectionPoint, entityIntersectionDetails.normalAtIntersection);
            
        }

    }

    /**
     * check if any of the object is intersecting
     * 
     * @param ray
     * @return Entity that intersects the first with the ray
     */
    public IntersectionDetails checkIntersection(Ray ray) {
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

        bestIntersection.entity = nearestEntity;

        if (nearestDistance > 0) {
            return bestIntersection;
        } else {
            return null;
        }
    }

}
