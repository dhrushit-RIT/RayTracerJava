public abstract class Entity {

    BoundingBox boundingBox;
    protected MyColor baseColor;
    protected Point position;

    public Entity(MyColor baseColor, Point position) {
        this.setBaseColor(baseColor);
        this.position = position;
    }

    public abstract IntersectionDetails intersect(Ray ray);

    public boolean intersect(Entity entity) {
        return false;
    }

    protected void setBaseColor(MyColor baseColor) {
        if (baseColor == null) {
            this.baseColor = new MyColor(255, 255, 255);
        } else {
            this.baseColor = baseColor;
        }
    }

    // public MyColor getPixelIrradiance(Light light, Camera camera, Point intersection, Vector Normal){
        
    // }
}
