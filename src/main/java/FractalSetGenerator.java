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
    protected double RADIUS;

    public FractalSetGenerator(int width, int height, int maxIterations, double xMin, double xMax, double yMin, double yMax) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.MAX_ITERATIONS = maxIterations;
        this.X_MIN = xMin;
        this.X_MAX = xMax;
        this.Y_MIN = yMin;
        this.Y_MAX = yMax;
        this.RADIUS = 2.0;
    }

    public abstract int getIterations(Complex z, Function<Complex, Complex> f);

    public abstract void generate();

    public double getX(int x) {
        return X_MIN + (X_MAX - X_MIN) * x / WIDTH;
    }

    public double getY(int y) {
        return Y_MIN + (Y_MAX - Y_MIN) * y / HEIGHT;
    }

    private int getRGB(int iterations) {
        int rgb;
        double percentage = (double) iterations / MAX_ITERATIONS;
        if (percentage < 0.1) {
            rgb = 0xFFFFFF;
            return rgb;
        }
        int r = (int) (256 * percentage), g = 0, b = r;
        rgb = new Color(r,g,b).getRGB();

//        rgb=Color.HSBtoRGB((float)iterations/MAX_ITERATIONS, 0.7f, 0.7f);

        return rgb;
    }
}
