package cpoo.projetcpoo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class JuliaSetGenerator extends FractalSetGenerator {

    private double cr;
    private double ci;

    public double getCr() {
        return cr;
    }

    public void setCr(double cr) {
        this.cr = cr;
    }

    public double getCi() {
        return ci;
    }

    public void setCi(double ci) {
        this.ci = ci;
    }

    public JuliaSetGenerator(int width, int height, int maxIterations, double xMin, double xMax, double yMin, double yMax, double cr, double ci) {
        super(width, height, maxIterations, xMin, xMax, yMin, yMax);
        this.cr = cr;
        this.ci = ci;
    }

    @Override
    public int getIterations(Complex z, Function<Complex, Complex> f) {
        int ite = 0;
        Complex zn = z;
        while (ite < MAX_ITERATIONS - 1 && zn.module() <= RADIUS) {
            zn = f.apply(zn);
            ite++;
        }
        return ite;
    }

    @Override
    public void generate(String fileName) {
        Complex c = new Complex(cr, ci);
        Function<Complex, Complex> f = (z) -> z.square().add(c);
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int iterations = getIterations(new Complex(getX(x), getY(y)), f);
                int rgb = getRGB(iterations);
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        try {
            ImageIO.write(bufferedImage, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}