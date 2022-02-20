
public class Application {

    private final World world;

    public Application() {
        this.world = new World();
        Camera camera = this.getDefaultCamera();
        // add sphere to the world
        this.world.addEntity(this.getDefaultSphere1());
        this.world.addEntity(this.getDefaultSphere2());
        this.world.addEntity(this.getDefaultTrainTriangle1());
        this.world.addEntity(this.getDefaultTrainTriangle2());
        // TODO : add platform to the world
        // add camera to the world
        this.world.setCamera(camera);

        // get camera instance and snap
    }

    private Triangle getDefaultTrainTriangle1() {
        return new Triangle(null, new Point(1, 0, 1),
                new Point[] { new Point(1, 0, 1), new Point(-1, 0, 1), new Point(1, 0, -1) });
    }

    private Triangle getDefaultTrainTriangle2() {
        return new Triangle(null, new Point(1, 0, 1),
                new Point[] { new Point(-1, 0, 1), new Point(1, 0, -1), new Point(-1, 0, -1) });
    }

    private Sphere getDefaultSphere2() {
        return new Sphere(new Point(0.6, 0.8, -0.6), 0.6, null);
    }

    private Sphere getDefaultSphere1() {
        return new Sphere(new Point(-0.4, 1, 0.4), 0.755, null);

    }

    private Camera getDefaultCamera() {
        Point cameraPosition = new Point(0, 1.15, 6);
        // an arbitary up works, it can later be projected to camera to work as its up :
        // https://raytracing.github.io/books/RayTracingInOneWeekend.html#positionablecamera/cameraviewinggeometry
        // TODO : not sure why but figure it out why
        Vector cameraUp = new Vector(0, 1, 0); // this is wrt camera.
        Vector cameraLookAt = new Vector(0, 1.15, 5);
        double cameraFocalLength = 2.5;

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
