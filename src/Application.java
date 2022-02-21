
public class Application {

    private final World world;

    public World getWorld() {
        return world;
    }

    public Application() {
        this.world = new World();
        // Setups.setup0(this);
        Setups.setup1(this);
    }


    public void start() {
        this.world.simulate();
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.start();
    }
}
