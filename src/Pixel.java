public class Pixel {
    public int value;
    public int row;
    public int col;
    public static double width;
    public static double height;

    // public Vector planePosition; // with respect to the origin of film plane
    public Vector wPosition; // with respect to the origin of world

    public int divisions = 1;

    public Pixel(int row, int col, Vector position) {
        // this.width = width;
        // this.height = height;
        this.row = row;
        this.col = col;
        this.wPosition = position;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setWidth(float width) {
        Pixel.width = width;
    }

    public void setHeight(float height) {
        Pixel.height = height;
    }

    public String toString() {
        return "Pix pos: " + wPosition;
    }

}
