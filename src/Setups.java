public class Setups {

    public static void setup0(Application application) {

        //
        // set up Camera
        //
        Point cameraPosition = new Point(0, 0, 0);
        Vector cameraUp = new Vector(0, 1, 0);
        Vector cameraLookAt = new Vector(0, 0, 1);
        double cameraFocalLength = 1.5;
        Camera camera = new Camera(cameraPosition, cameraUp, cameraLookAt, cameraFocalLength);

        Point sphereCenter = new Point(0, 0, -3);
        double sphereRadius = 1.0;
        MyColor sphereColor = new MyColor(0, 0, 255);
        application.getWorld().addEntity(new Sphere(sphereCenter, sphereRadius, sphereColor));

        application.getWorld().setCamera(camera);
    }

    public static void setup1(Application application) {
        // an arbitary up works, it can later be projected to camera to work as its up :
        // https://raytracing.github.io/books/RayTracingInOneWeekend.html#positionablecamera/cameraviewinggeometry
        // TODO : not sure why but figure it out why
        Point cameraPosition = new Point(0, 1.15, 2.6);
        Vector cameraUp = new Vector(0, 1, 0); // this is wrt camera.
        Vector cameraLookAt = new Vector(0, 1.15, 2.5);
        double cameraFocalLength = 2.5;
        Camera camera = new Camera(cameraPosition, cameraUp, cameraLookAt, cameraFocalLength);

        application.getWorld().addEntity(new Sphere(new Point(-0.4, 1, 0.4), 0.755, new MyColor(0, 0, 255)));
        application.getWorld().addEntity(new Sphere(new Point(0.6, 0.8, -0.6), 0.6, new MyColor(0, 255, 0)));
        application.getWorld().addEntity(new Triangle(new MyColor(255, 0, 0), new Point(1, 0, 1),
                new Point[] { new Point(1, 0, 1), new Point(-1, 0, 1), new Point(1, 0, -1) }));
        application.getWorld().addEntity(new Triangle(new MyColor(255, 0, 0), new Point(1, 0, 1),
                new Point[] { new Point(-1, 0, 1), new Point(1, 0, -1), new Point(-1, 0, -1) }));
        application.getWorld().setCamera(camera);
    }
}
