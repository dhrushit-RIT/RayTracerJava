public abstract class Entity {

    BoundingBox boundingBox;
    protected Color baseColor;
    protected Point position;

    public Entity(Color baseColor, Point position) {
        this.setBaseColor(baseColor);
        this.position = position;
    }

    public abstract double intersect(Ray ray);

    public boolean intersect(Entity entity) {
        return false;
    }

    protected void setBaseColor(Color baseColor) {
        if (baseColor == null) {
            this.baseColor = new Color(255, 255, 255);
        } else {
            this.baseColor = baseColor;
        }
    }
}
