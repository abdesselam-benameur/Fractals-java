import java.awt.*;
import java.util.function.Function;

public abstract class FractalSetGenerator {
    protected int WIDTH;
    protected int HEIGHT;
    protected int MAX_ITERATIONS;
    protected double X_MIN;
    protected double X_MAX;
    protected double Y_MIN;
    protected double Y_MAX;
    protected final double RADIUS = 2.0;

    public FractalSetGenerator(int width, int height, int maxIterations, double xMin, double xMax, double yMin, double yMax) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.MAX_ITERATIONS = maxIterations;
        this.X_MIN = xMin;
        this.X_MAX = xMax;
        this.Y_MIN = yMin;
        this.Y_MAX = yMax;
    }

    public abstract int getIterations(Complex z, Function<Complex, Complex> f);

    public abstract void generate();

    public double getX(int x) {
        return X_MIN + (X_MAX - X_MIN) * x / WIDTH;
    }

    public double getY(int y) {
        return Y_MIN + (Y_MAX - Y_MIN) * y / HEIGHT;
    }

    public int getRGB(int iterations) {
        float bright;
        if (iterations < 10) {
            bright = 0.0f;
        } else {
            bright = 1.0f;
        }
        return Color.getHSBColor(iterations * 0.01f, 1.0f, bright).getRGB();
    }

//    public int getRGB(int iterations) {
//      int r= (255*iterations)/MAX_ITERATIONS, g=255, b=255;
//      int rgb = new Color(r,g,b).getRGB();
//      int rgb = Color.HSBtoRGB((float) iterations / MAX_ITERATIONS, (float) iterations / MAX_ITERATIONS, 0.9f);
//  }

//    public int getRGB(int iterations) {
//        int rgb;
//        double percentage = (double) iterations / MAX_ITERATIONS;
//        if (percentage < 0.1) {
//            rgb = 0xFFFFFF;
//            return rgb;
//        }
//        int r = (int) (256 * percentage), g = 0, b = r;
//        rgb = new Color(r,g,b).getRGB();
////        rgb=Color.HSBtoRGB((float)iterations/MAX_ITERATIONS, 0.7f, 0.7f);
//        return rgb;
//    }

//    public int getRGB(int iterations) {
//        int rgb;
//        if (iterations == MAX_ITERATIONS) {
//            rgb = 0x000000;
//        } else {
//            double distance = Math.pow(Math.pow(X_SCALE, iterations) + Math.pow(Y_SCALE, iterations), 1.0 / 2.0);
//            if (distance < MAX_DISTANCE) {
//                rgb = 0xFFFFFF;
//            } else if (distance < MAX_DISTANCE_SQUARED) {
//                rgb = 0xFF0000;
//            } else if (distance < MAX_DISTANCE_CUBED) {
//                rgb = 0x00FF00;
//            } else if (distance < MAX_DISTANCE_FOURTH) {
//                rgb = 0x0000FF;
//            } else if (distance < MAX_DISTANCE_FIFTH) {
//                rgb = 0xFFFF00;
//            } else {
//                rgb = 0xFF00FF;
//            }
//        }
//        return rgb;
//    }
}
