import gnu.getopt.Getopt;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.StringConcatException;

public class Main {
//    MAX_ITER=1000; RADIUS=2.;
//    int divergenceIndex(Complex z0) {
//        int ite = 0; Complex zn = z0;
//// sortie de boucle si divergence
//        while (ite < MAX_ITER-1 && |zn| <= RADIUS)
//        zn = f(zn); ite++;
//        return ite;
//    }

//    public static void main(String[] args) throws IOException {
//        MandelbrotSetGenerator generator = new MandelbrotSetGenerator();
//        BufferedImage image = generator.generateImage(1000, 1000);
//        ImageIO.write(image, "png", new File("mandelbrot.png"));
//    }

    public static void main(String[] args) {
        Getopt g = new Getopt("testprog", args, "w:h:a:b:c:d:t:r:i:f:p:m");
        int c;
        String arg;
        int width = 1000;
        int height = 1000;
        int maxIter = 1000;
        double re = -0.7269 ,im = 0.1889, pas = 0.0;
        boolean mandelbrot = false;
//        Complex constante = new Complex(0,0);
        int xMin = -1;
        int yMin = -1;
        int xMax = 1;
        int yMax = 1;
        while ((c = g.getopt()) != -1)
        {
            switch(c)
            {
                case 'w':
                    arg = g.getOptarg();
                    try {
                        width = Integer.parseInt(arg);
                    }
                    catch (NumberFormatException e) {
                        System.err.println("width must be an integer");
                        System.exit(1);
                    }
                    break;
                case 'h':
                    arg = g.getOptarg();
                    try {
                        height = Integer.parseInt(arg);
                    }
                    catch (NumberFormatException e) {
                        System.err.println("height must be an integer");
                        System.exit(1);
                    }
                    break;
                case 't':
                    arg = g.getOptarg();
                    try {
                        maxIter = Integer.parseInt(arg);
                    }
                    catch (NumberFormatException e) {
                        System.err.println("maxIter must be an integer");
                        System.exit(1);
                    }
                    break;
                case 'a':
                    arg = g.getOptarg();
                    try {
                        xMin = Integer.parseInt(arg);
                    }
                    catch (NumberFormatException e) {
                        System.err.println("xMin must be an integer");
                        System.exit(1);
                    }
                    break;
                case 'b':
                    arg = g.getOptarg();
                    try {
                        xMax = Integer.parseInt(arg);
                    }
                    catch (NumberFormatException e) {
                        System.err.println("xMax must be an integer");
                        System.exit(1);
                    }
                    break;
                case 'c':
                    arg = g.getOptarg();
                    try {
                        yMin = Integer.parseInt(arg);
                    }
                    catch (NumberFormatException e) {
                        System.err.println("yMin must be an integer");
                        System.exit(1);
                    }
                    break;
                case 'd':
                    arg = g.getOptarg();
                    try {
                        yMax = Integer.parseInt(arg);
                    }
                    catch (NumberFormatException e) {
                        System.err.println("yMax must be an integer");
                        System.exit(1);
                    }
                    break;
                case 'r':
                    arg = g.getOptarg();
                    try {
                        re =  Double.parseDouble(arg);
                        //constante.setRe(re);
                    }
                    catch (NumberFormatException e) {
                        System.err.println("real must be an integer");
                        System.exit(1);
                    }
                    break;
                case 'i':
                    arg = g.getOptarg();
                    try {
                        im = Double.parseDouble(arg);
                        //constante.setIm(im);
                        //System.out.println(constante.getIm());
                    }
                    catch (NumberFormatException e) {
                        System.err.println("imaginary must be an integer");
                        System.exit(1);
                    }
                    break;
                case 'f':
                    arg = g.getOptarg();
                    File f = new File(arg);;
                    break;
                case 'p':
                    arg = g.getOptarg();
                    try {
                        pas =  Double.parseDouble(arg);
                    }
                    catch (NumberFormatException e) {
                        System.err.println("step must be an integer");
                        System.exit(1);
                    }
                    break;
                case 'm':
                    mandelbrot = true;
                    break;

                case '?':
                    System.out.println("Usage: main [-w width] [-h height] [-a xMin] [-b xMax] [-c yMin] [-d yMax] [-t maxIterations] [-m mandelbrot] "
                            + "[-r real_part_constant] [-i imaginary_part_constant] [-f fileName] [-p pas]");
                    System.exit(1);
                    break; // getopt() already printed an error
                //
                default:
                    System.out.print("getopt() returned " + c + "\n");
            }
        }
//
//        JuliaSetGenerator juliaSetGenerator = new JuliaSetGenerator(width, height, maxIter, -2,2,-2,2);
        if (mandelbrot) {
            MandelbrotSetGenerator mandelbrotSetGenerator = new MandelbrotSetGenerator(width, height, maxIter, xMin, xMax, yMin, yMax);
            mandelbrotSetGenerator.generate();
        }
        else {
            JuliaSetGenerator juliaSetGenerator = new JuliaSetGenerator(width, height, maxIter, xMin, xMax, yMin, yMax);
            juliaSetGenerator.generate(re, im);
//        juliaSetGenerator.generate(0.4, 0.2);
        }
    }
}
