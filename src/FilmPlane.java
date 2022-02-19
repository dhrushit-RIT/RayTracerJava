import java.util.ArrayList;
import java.util.Iterator;

public class FilmPlane implements Iterable<Pixel> {
    public int height;
    public int width;
    public Vector wPosition;

    public int numPixelsHeight;
    public int numPixelsWidth;

    private ArrayList<ArrayList<Pixel>> filmPixels;

    public FilmPlane(int width, int height, int numPixelsWidth, int numPixelsHeight, Vector position) {

        this.width = width;
        this.height = height;
        this.wPosition = position;
        this.filmPixels = new ArrayList<ArrayList<Pixel>>();

        this.numPixelsHeight = numPixelsHeight;
        this.numPixelsWidth = numPixelsWidth;

        Pixel.height =  (double) height / numPixelsHeight;
        Pixel.width = (double) width / numPixelsWidth;

        for (int row = 0; row < numPixelsHeight; row++) {
            ArrayList<Pixel> pixelRow = new ArrayList<>();
            for (int col = 0; col < numPixelsWidth; col++) {

                double pX = -width / 2 + col * Pixel.width + Pixel.width / 2;
                double pY = -height / 2 + row * Pixel.height + Pixel.height / 2;

                Vector pixelPositionWrtPlane = new Vector(pX, pY, 0);
                Vector wPixelPos = Vector.add(this.wPosition, pixelPositionWrtPlane);

                pixelRow.add(new Pixel(row, col, wPixelPos));
            }
            filmPixels.add(pixelRow);
        }

        // System.out.println(this);
    }

    @Override
    public Iterator<Pixel> iterator() {
        return new Iterator<Pixel>() {
            public int curRow = 0;
            public int curCol = 0;

            @Override
            public boolean hasNext() {
                return curRow < filmPixels.size();
            }

            @Override
            public Pixel next() {
                Pixel ret = filmPixels.get(curRow).get(curCol);
                
                curCol++;
                if (curCol >= numPixelsWidth){
                    curCol = 0;
                    curRow++;
                }
               
                return ret;
            }

        };
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("FILM PLANE : \n");
        sb.append("\tposition " + wPosition + "\n");
        sb.append("\tposition wrt camera" + Camera.toCameraSpace(wPosition) + "\n");

        return sb.toString();
    }
    // public Ray get
    // public Pixel getNextPixel() {

    // }
}
