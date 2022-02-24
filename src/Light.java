public class Light extends Entity {

    MyColor color;

    public Light(MyColor baseColor, Point position) {
        super(baseColor, position);
        this.color = baseColor;
    }

    @Override
    public double intersect(Ray ray) {
        return -1;
    }

}
