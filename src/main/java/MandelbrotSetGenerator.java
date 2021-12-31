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
        // sortie de boucle si divergence
        while (ite < MAX_ITERATIONS - 1 && zn.module() <= RADIUS) {// X_MIN < zn.getRe() && zn.getRe() < X_MAX && Y_MIN < zn.getIm() && zn.getIm() < Y_MAX) {
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
                // x^8+15*x^4-16
//                Function<Complex, Complex> f = (z) -> z.pow(8).add(z.pow(4).mul(15.0)).add(new Complex(-16.0, 0.0));
                int iterations = getIterations(new Complex(getX(x), getY(y)), f);
//                int rgb = getRGB(iterations);
//                int rgb =  getRGB(iterations);
//                int r= (255*iterations)/MAX_ITERATIONS, g=255, b=255;
//                int rgb = new Color(r,g,b).getRGB();
//                System.out.println(iterations);
//                int rgb = Color.HSBtoRGB((float) iterations / MAX_ITERATIONS, (float) iterations / MAX_ITERATIONS, 0.9f);
                float bright;
                if (iterations < 10){
                    bright = 0.0f;
                } else {
                    bright = 1.0f;
                }
                int rgb = Color.getHSBColor(iterations * 0.01f, 1.0f, bright).getRGB();
                bufferedImage.setRGB(x, y, rgb); //getRGB(iterations));
            }
        }
        try {
            ImageIO.write(bufferedImage, "png", new File("julia.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
