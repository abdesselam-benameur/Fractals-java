package cpoo.projetcpoo;

import javafx.application.Application;
import org.apache.commons.cli.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParseException {
        // initialize the variables
        int width = 1000;
        int height = 1000;
        int maxIter = 1000;
        double xMin = -2.0;
        double yMin = -2.0;
        double xMax = 2.0;
        double yMax = 2.0;
        double cr = -0.7269, ci = 0.1889;
        boolean mandelbrot = false;
        double zoom = 1.0;
        String filename = "output.png";

        // create Options object
        Options options = new Options();

        // add the options
        options.addOption("w", "width", true, "width of the image");
        options.addOption("h", "height", true, "height of the image");
        options.addOption("m", "maxIter", true, "max iterations");
        options.addOption("cr", true, "real part of the constant");
        options.addOption("ci", true, "imaginary part of the constant");
        options.addOption("mandelbrot", false, "generate mandelbrot set image");
        options.addOption("xmin", true, "X_MIN");
        options.addOption("xmax", true, "X_MAX");
        options.addOption("ymin", true, "Y_MIN");
        options.addOption("ymax", true, "Y_MAX");
        options.addOption("z", "zoom", true, "zoom factor");
        options.addOption("f", "filename", true, "the filename of the generated image");
        options.addOption("g", "gui", false, "launch the GUI version");
        options.addOption("help", false, "print this message");

        // create the command line parser
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            // parse the command line arguments
            cmd = parser.parse(options, args);

            // if no option is specified or help is requested, display the help message
            if (cmd.getOptions().length == 0 || cmd.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("java -jar ProjetCPOO.jar", options);
                System.exit(0);
            }

            // if the GUI option is set, launch the GUI version
            if (cmd.hasOption("g")) {
                Application.launch(cpoo.projetcpoo.GUIMain.class);
                System.exit(0);
            }

            // get the width
            if (cmd.hasOption("w")) {
                try {
                    width = Integer.parseInt(cmd.getOptionValue("w"));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid width");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("java -jar ProjetCPOO.jar", options);
                    System.exit(1);
                }
            }

            // get the height
            if (cmd.hasOption("h")) {
                try {
                    height = Integer.parseInt(cmd.getOptionValue("h"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid height");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("java -jar ProjetCPOO.jar", options);
                    System.exit(1);
                }
            }

            // check if mandelbrot boolean is set to true
            if (cmd.hasOption("mandelbrot")) {
                mandelbrot = true;
            }

            // get the real part of the constant
            if (cmd.hasOption("cr")) {
                try {
                    cr = Double.parseDouble(cmd.getOptionValue("cr"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid real part of the constant");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("java -jar ProjetCPOO.jar", options);
                    System.exit(1);
                }
            }

            // get the imaginary part of the constant
            if (cmd.hasOption("ci")) {
                try {
                    ci = Double.parseDouble(cmd.getOptionValue("ci"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid imaginary part of the constant");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("java -jar ProjetCPOO.jar", options);
                    System.exit(1);
                }
            }

            // get the max iterations
            if (cmd.hasOption("m")) {
                try {
                    maxIter = Integer.parseInt(cmd.getOptionValue("m"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid max iterations");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("java -jar ProjetCPOO.jar", options);
                    System.exit(1);
                }
            }

            // get the xmin
            if (cmd.hasOption("xmin")) {
                try {
                    xMin = Integer.parseInt(cmd.getOptionValue("xmin"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid X_MIN");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("java -jar ProjetCPOO.jar", options);
                    System.exit(1);
                }
            }

            // get the xmax
            if (cmd.hasOption("xmax")) {
                try {
                    xMax = Integer.parseInt(cmd.getOptionValue("xmax"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid X_MAX");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("java -jar ProjetCPOO.jar", options);
                    System.exit(1);
                }
            }

            // get the ymin
            if (cmd.hasOption("ymin")) {
                try {
                    yMin = Integer.parseInt(cmd.getOptionValue("ymin"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid Y_MIN");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("java -jar ProjetCPOO.jar", options);
                    System.exit(1);
                }
            }

            // get the ymax
            if (cmd.hasOption("ymax")) {
                try {
                    yMax = Integer.parseInt(cmd.getOptionValue("ymax"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid Y_MAX");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("java -jar ProjetCPOO.jar", options);
                    System.exit(1);
                }
            }

            // get the zoom factor
            if (cmd.hasOption("zoom")) {
                try {
                    zoom = Double.parseDouble(cmd.getOptionValue("zoom"));
                    xMin /= zoom;
                    xMax /= zoom;
                    yMin /= zoom;
                    yMax /= zoom;
                }
                catch (NumberFormatException e) {
                    System.err.println("Invalid zoom factor");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("java -jar ProjetCPOO.jar", options);
                    System.exit(1);
                }
            }

            // get the filename
            if (cmd.hasOption("f")) {
                filename = cmd.getOptionValue("f");
                if (filename == null) {
                    System.err.println("Filename is required");
                    System.exit(1);
                }
            }

            // generate the image
            BufferedImage img;
            if (mandelbrot) {
                MandelbrotSetGenerator m = new MandelbrotSetGenerator(width, height, maxIter, xMin, xMax, yMin, yMax);
                img = m.generate();
            } else {
                JuliaSetGenerator j = new JuliaSetGenerator(width, height, maxIter, xMin, xMax, yMin, yMax, cr, ci);
                img = j.generate();
            }

            try {
                ImageIO.write(img, "png", new File(filename));
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

        } catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar ProjetCPOO.jar", options);
            System.exit(1);
        }
    }
}
