import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class MandelbrotSetGenerator extends FractalSetGenerator {

    public MandelbrotSetGenerator(int width, int height, int maxIterations, double xMin, double xMax, double yMin, double yMax) {
        super(width, height, maxIterations, xMin, xMax, yMin, yMax);
    }

    @Override
    public int getIterations(Complex z, Function<Complex, Complex> f) {
        int ite = 0;
        Complex zn = new Complex(0, 0);
        while (ite < MAX_ITERATIONS - 1 && zn.module() <= RADIUS) {
            zn = f.apply(zn);
            ite++;
        }
        return ite;
    }

    @Override
    public void generate() {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Complex c = new Complex(getX(x), getY(y));
                Function<Complex, Complex> f = (z) -> z.square().add(c);
                int iterations = getIterations(new Complex(getX(x), getY(y)), f);
                int rgb = getRGB(iterations);
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        try {
            ImageIO.write(bufferedImage, "png", new File("julia.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
