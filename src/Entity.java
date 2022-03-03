
public abstract class Entity {

    public enum BSDFTechnique {
        PHONG,
        PHONG_BLINN,
        // ASHIKHMIN_SHIRLEY,
        // COOK_TORRANCE
    }

    protected static double EPSILON = 0.000001;
    BoundingBox boundingBox;
    protected MyColor baseColor;
    protected MyColor specularColor;
    protected MyColor diffusedColor;
    protected Point position;

    protected double ka = 0.1;
    protected double kd = 0.4;
    protected double ks = 0.5;
    protected double ke = 180;

    public Entity(MyColor baseColor, Point position) {
        this.setBaseColor(baseColor);

        this.specularColor = baseColor;
        this.diffusedColor = baseColor;
        this.position = position;
    }

    protected void setBaseColor(MyColor baseColor) {
        if (baseColor == null) {
            this.baseColor = new MyColor(255, 255, 255, false).normalize();
        } else {
            this.baseColor = baseColor.normalize();
        }
    }

    public MyColor phongBlinn(Light light, Camera camera, Point intersecPoint, Vector normal) {
        Vector lightDir = Util.subtract(Camera.toCameraSpace(light.position), intersecPoint);
        lightDir.normalize();
        double ambientFactor = ka * light.irradiance;
        double diffuseFactor = kd * light.irradiance * Util.dot(lightDir, normal);

        normal.normalize();

        // Vector reflectVector = Util.reflect(lightDir, normal, intersecPoint);
        Vector view = Util.subtract(camera.getcPosition(), intersecPoint);
        view.normalize();
        // double reflectDotView = Math.max(0.0, Util.dot(reflectVector, view));
        Vector halfway = Util.add(lightDir, view).normalize();
        double normalDotHalf = Math.max(0.0, Util.dot(halfway, normal));

        double specularFactor = ks * light.irradiance * Math.pow(normalDotHalf, ke);

        MyColor ambient = Util.multColor(ambientFactor, baseColor);
        MyColor diffuse = Util.multColor(diffuseFactor, diffusedColor);
        MyColor specular = Util.multColor(specularFactor, specularColor);

        MyColor finalColor = Util.addColor(ambient, diffuse, specular);
        return finalColor;
    }

    public MyColor phong(Light light, Camera camera, Point intersecPoint, Vector normal) {
        Vector lightDir = Util.subtract(Camera.toCameraSpace(light.position), intersecPoint);
        lightDir.normalize();
        double ambientFactor = ka * light.irradiance;
        double diffuseFactor = kd * light.irradiance * Util.dot(lightDir, normal);

        normal.normalize();

        Vector reflectVector = Util.reflect(lightDir, normal, intersecPoint);
        Vector view = Util.subtract(camera.getcPosition(), intersecPoint);
        view.normalize();
        double reflectDotView = Math.max(0.0, Util.dot(reflectVector, view));
        double specularFactor = ks * light.irradiance * Math.pow(reflectDotView, ke);

        MyColor ambient = Util.multColor(ambientFactor, baseColor);
        MyColor diffuse = Util.multColor(diffuseFactor, diffusedColor);
        MyColor specular = Util.multColor(specularFactor, specularColor);

        MyColor finalColor = Util.addColor(ambient, diffuse, specular);
        return finalColor;
    }

    public MyColor getPixelIrradiance(Light light, Camera camera, Point intersection, Vector normal,
            BSDFTechnique technique) {
        MyColor retColor = new MyColor(0, 0, 0, true);
        switch (technique) {
            case PHONG_BLINN:
                retColor = this.phongBlinn(light, camera, intersection, normal);
                break;
            default:
                retColor = this.phong(light, camera, intersection, normal);
                break;
        }
        return retColor;
    }

    public void setCoeffs(double ka, double kd, double ks, double ke) {
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.ke = ke;
    }

    public void setColors(MyColor basecColor, MyColor speColor, MyColor diffuseColor) {
        this.baseColor = basecColor.normalize();
        this.specularColor = speColor.normalize();
        this.diffusedColor = diffuseColor.normalize();
    }

    public abstract IntersectionDetails intersect(Ray ray);
}
