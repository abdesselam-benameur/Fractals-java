import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class JuliaSetGenerator {
    private static final int MAX_ITERATIONS = 1000;
    private static final double ZOOM_FACTOR = 1.0;
    private static final double X_OFFSET = 0.0;
    private static final double Y_OFFSET = 0.0;
    private static final double X_SCALE = 1.0;
    private static final double Y_SCALE = 1.0;
    private static final double X_CENTER = 0.0;
    private static final double Y_CENTER = 0.0;
    private static final double X_MIN = -0.00001;
    private static final double X_MAX = 0.00001;
    private static final double Y_MIN = -0.00001;
    private static final double Y_MAX = 0.00001;
    private static final int WIDTH = 8000;
    private static final int HEIGHT = 8000;
    private static final int MAX_ITERATIONS_PER_PIXEL = 10;
    private static final double MAX_DISTANCE = 2.0;
    private static final double MAX_DISTANCE_SQUARED = 4.0;
    private static final double MAX_DISTANCE_CUBED = 8.0;
    private static final double MAX_DISTANCE_FOURTH = 16.0;
    private static final double MAX_DISTANCE_FIFTH = 32.0;

    public void generate() {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Function<Complex, Complex> f = (z) -> z.square().add(new Complex(-0.7269, 0.1889));

                int iterations = getIterations(new Complex(getX(x), getY(y)), f);
//                int rgb =  getRGB(iterations);
                int r= (255*iterations)/MAX_ITERATIONS, g=0, b=((255*iterations)/MAX_ITERATIONS)%255;
                int rgb = new Color(r,g,b).getRGB();
//                int rgb = Color.HSBtoRGB((float) iterations / MAX_ITERATIONS, (float) iterations / MAX_ITERATIONS, 0.9f);
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        try {
            ImageIO.write(bufferedImage, "png", new File("julia.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIterations(Complex z0, Function<Complex, Complex> f) {
        int ite = 0;
        Complex zn = z0;
        // sortie de boucle si divergence
        while (ite < MAX_ITERATIONS - 1 && zn.module() <= MAX_DISTANCE) {
            zn = f.apply(zn);
            ite++;
        }
        return ite;
    }

    private double getX(int x) {
        return X_MIN + (X_MAX - X_MIN) * (x + X_OFFSET) / WIDTH;
    }

    private double getY(int y) {
        return Y_MIN + (Y_MAX - Y_MIN) * (y + Y_OFFSET) / HEIGHT;
    }

    private int getRGB(int iterations) {
        int rgb;
        if (iterations == MAX_ITERATIONS) {
            rgb = 0x000000;
        } else {
            double distance = Math.pow(Math.pow(X_SCALE, iterations) + Math.pow(Y_SCALE, iterations), 1.0 / 2.0);
            if (distance < MAX_DISTANCE) {
                rgb = 0xFFFFFF;
            } else if (distance < MAX_DISTANCE_SQUARED) {
                rgb = 0xFF0000;
            } else if (distance < MAX_DISTANCE_CUBED) {
                rgb = 0x00FF00;
            } else if (distance < MAX_DISTANCE_FOURTH) {
                rgb = 0x0000FF;
            } else if (distance < MAX_DISTANCE_FIFTH) {
                rgb = 0xFFFF00;
            } else {
                rgb = 0xFF00FF;
            }
        }
        return rgb;
    }
}