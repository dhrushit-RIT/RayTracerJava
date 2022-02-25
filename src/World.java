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
        MyColor finalColor =  new MyColor(0, 0, 0);
        boolean didGetIlluminated = false;
        IntersectionDetails entityIntersectionDetails = this.checkIntersection(ray);
        for (Light light : lightSources) {
            Ray shadowRay =  new Ray(entityIntersectionDetails.intersectionPoint, Util.subtract(light.position, entityIntersectionDetails.intersectionPoint));
            
            // MyColor color  = entityIntersectionDetails.entity.getPixelIrradiance(light, this.camera, entityIntersectionDetails.intersectionPoint, entityIntersectionDetails.normalAtIntersection);
            
            // generate a shadow ray from the point of intersection to the light source
            // if the ray intersects any of the other entity then continue
            // if the ray does not intersect any other entity then do the phong and get the irradiance value from object
            // set the didGetIlluminated to true

        }

        if (didGetIlluminated){
            return finalColor;
        }else{
            return Camera.DEFAULT_COLOR;
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
