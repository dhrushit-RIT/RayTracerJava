
public class Application {

    private final World world;

    public Application() {
        this.world = new World();
        Camera camera = this.getDefaultCamera();
        // add sphere to the world
        this.world.addEntity(this.getDefaultSphere1());
        this.world.addEntity(this.getDefaultSphere2());
        // TODO : add platform to the world
        // add camera to the world
        this.world.setCamera(camera);

        // get camera instance and snap
    }

    private Sphere getDefaultSphere2() {
        
        // return new Sphere(new Point(1.5, 0, -0.5), 0.9, null);
        return new Sphere(new Point(0.6, 0.8, -0.6), 0.6, null);
    }

    private Sphere getDefaultSphere1() {
        // 0.466789, -0.675721, 1.10661
        // old : 2, -.585, 2.6
        // radius = 0.755
        // color = null
        // return new Sphere(new Point(0, 0, 0), 1, null);
        return new Sphere(new Point(-0.4, 1, 0.4), 0.755, null);
        // return new Sphere(new Point(0.466789, -0.675721, 1.10661), 1, null);

    }

    private Camera getDefaultCamera() {
        // Point cameraPosition = new Point(0, 0, 5);
        Point cameraPosition = new Point(0, 1.15, 6);
        // Point cameraPosition = new Point(5.5, -0.5, 1);
        // an arbitary up works, it can later be projected to camera to work as its up : https://raytracing.github.io/books/RayTracingInOneWeekend.html#positionablecamera/cameraviewinggeometry
        // TODO : not sure why but figure it out why
        Vector cameraUp = new Vector(0, 1, 0); // this is wrt camera. 
        // Vector cameraUp = new Vector(0, 2.15, 6); 
        // Vector cameraUp = new Vector(0, 0, 1);
        // Vector cameraLookAt = new Vector(0, 0, -1); // this is wrt camera
        Vector cameraLookAt = new Vector(0, 1.15, 5); 
        // Vector cameraLookAt = new Vector(-1, 0, 0);
        double cameraFocalLength = .5;

        return new Camera(cameraPosition, cameraUp, cameraLookAt, cameraFocalLength);
    }

    public void start() {
        this.world.simulate();
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.start();

    }
}
