import gnu.getopt.Getopt;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        Getopt g = new Getopt("testprog", args, "w:h:a:b:c:d:m:");
        int c;
        String arg;
        int width = 1000;
        int height = 1000;
        int maxIter = 1000;
        int xMin = -2;
        int yMin = -2;
        int xMax = 2;
        int yMax = 2;
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
                case 'm':
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
                        yMax = Integer.parseInt(arg);
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
                        xMax = Integer.parseInt(arg);
                    }
                    catch (NumberFormatException e) {
                        System.err.println("xMax must be an integer");
                        System.exit(1);
                    }
                    break;
//                case 'c':
//                    arg = g.getOptarg();
//                    System.out.print("You picked " + (char)c +
//                            " with an argument of " +
//                            ((arg != null) ? arg : "null") + "\n");
//                    break;

                case '?':
                    System.out.println("Usage: main [-w width] [-h height]");
                    System.exit(1);
                    break; // getopt() already printed an error
                //
                default:
                    System.out.print("getopt() returned " + c + "\n");
            }
        }
//
        JuliaSetGenerator juliaSetGenerator = new JuliaSetGenerator(width, height, maxIter, -2,2,-2,2);
//        juliaSetGenerator.generateMandelbrot();
//        juliaSetGenerator.generate(-0.7269, 0.1889);
//        juliaSetGenerator.generate(0.4, 0.2);
    }
}
