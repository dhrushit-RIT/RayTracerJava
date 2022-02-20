import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.ejml.simple.SimpleMatrix;

import javax.imageio.ImageIO;

public class Camera extends Entity {

    public FilmPlane filmPlane;
    public Vector wUp;
    public Vector wLookAt;
    public double focalLength;

    public Vector n;
    public Vector u;
    public Vector v;

    public World world;

    public static SimpleMatrix worldToNodeMatrix;

    public Camera(Point position, Vector up, Vector lookAt, double focalLength) {
        super(null, position);
        this.wUp = up;
        this.wLookAt = lookAt;
        this.focalLength = focalLength;
        this.filmPlane = new FilmPlane(8, 5, 400, 250, Vector.scale(lookAt, focalLength));

        n = Vector.subtract(position, lookAt);
        n.normalize();
        u = Vector.cross(up, n);
        u.normalize();
        v = Vector.cross(n, u);

        Camera.worldToNodeMatrix = new SimpleMatrix(new double[][] {
                { u.x, v.x, n.x, 0 },
                { u.y, v.y, n.y, 0 },
                { u.z, v.z, n.z, 0 },
                { -1.0 * Vector.dot(position, u), -1.0 * Vector.dot(position, v), -1.0 * Vector.dot(position, n), 1 }
        });

        System.out.println(this);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void takeASnap() {
        int counter = 0;

        for (Pixel pixel : filmPlane) {
            // get vector
            // convert to camera space
            // Vector dir = toCameraSpace(pixel.wPosition);
            // System.out.println(pixel);
            Vector dir = new Vector(pixel.wPosition);
            dir.normalize();
            Ray ray = new Ray(this.position, dir);
            boolean doesIntersect = this.world.checkIntersection(ray);
            if (doesIntersect) {
                pixel.setValue(255);
            } else {
                pixel.setValue(0);
            }
            counter++;

            // 9629770174
            // shoot a ray and check intersection
        }

        // System.out.println(counter);
    }

    public BufferedImage generateImage() {
        BufferedImage rgbImage = new BufferedImage(filmPlane.numPixelsWidth,
                filmPlane.numPixelsHeight,
                BufferedImage.TYPE_3BYTE_BGR);

        for (Pixel pixel : filmPlane) {
            Color color = new Color(pixel.value, pixel.value, pixel.value);
            int actualCol = filmPlane.numPixelsWidth - pixel.col - 1;
            rgbImage.setRGB(actualCol, pixel.row, color.getRGB());
        }
        writeImgToFile(rgbImage);
        // Graphics2D g2d = rgbImage.createGraphics();
        // g2d.drawImage(rgbImage, 0, 0, Color.GRAY, null);
        // g2d.dispose();

        return rgbImage;
    }

    public void writeImgToFile(BufferedImage bi) {
        try {
            // retrieve image

            File outputfile = new File("saved.png");
            ImageIO.write(bi, "jpg", outputfile);
        } catch (IOException e) {
            System.out.println("error while writing image file " + e);
        }
    }

    @Override
    public boolean intersect(Ray ray) {
        // TODO Auto-generated method stub
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Vector upCam = toCameraSpace(wUp);
        Vector lookAtCam = toCameraSpace(wLookAt);
        upCam.normalize();
        lookAtCam.normalize();

        sb.append("CAMERA : \n");
        sb.append("\tposition " + position + "\n");
        sb.append("\tlookAt " + wLookAt + "\n");
        sb.append("\tup " + wUp + "\n");
        sb.append("\tn " + n + "\n");
        sb.append("\tu " + u + "\n");
        sb.append("\tv " + v + "\n");
        sb.append("\tupCam " + upCam + "\n");
        sb.append("\tlookatCam " + lookAtCam + "\n");
        sb.append("\tviewmatrix " + worldToNodeMatrix + "\n");

        return sb.toString();
    }

    //
    // static methods
    //

    public static Vector toCameraSpace(Vector v) {
        return Vector.getVectorFromMatrix(Camera.worldToNodeMatrix.copy().mult(v.matrix));
    }

    public static Point toCameraSpace(Point p) {
        return Point.getPointFromMatrix(Camera.worldToNodeMatrix.copy().mult(p.matrix));
    }

}
