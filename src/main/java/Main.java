import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        // initialize the variables
        int width = 1000;
        int height = 1000;
        int maxIter = 1000;
        int xMin = -2;
        int yMin = -2;
        int xMax = 2;
        int yMax = 2;
        double cr = -0.7269, ci = 0.1889;
        boolean mandelbrot = false;
        String filename = "output.png";

        // create Options object
        Options options = new Options();

        // add the options
        options.addOption("w", "width", true, "width of the image");
        options.addOption("h", "height", true, "height of the image");
        options.addOption("maxIter", true, "max iterations");
        options.addOption("cr", true, "real part of the constant");
        options.addOption("ci", true, "imaginary part of the constant");
        options.addOption("m", "mandelbrot", false, "generate mandelbrot set image");
        options.addOption("xmin", true, "X_MIN");
        options.addOption("xmax", true, "X_MAX");
        options.addOption("ymin", true, "Y_MIN");
        options.addOption("ymax", true, "Y_MAX");
        options.addOption("f", "filename", true, "the filename of the generated image");

        // create the command line parser
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        // get the width
        if(cmd.hasOption("w")) {
            try {
                width = Integer.parseInt(cmd.getOptionValue("w"));
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid width");
                System.exit(1);
            }
        }

        // get the height
        if(cmd.hasOption("h")) {
            try {
                height = Integer.parseInt(cmd.getOptionValue("h"));
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid height");
                System.exit(1);
            }
        }

        // check if mandelbrot boolean is set to true
        if(cmd.hasOption("m")) {
            mandelbrot = true;
        }

        // get the real part of the constant
        if(cmd.hasOption("cr")) {
            try {
                cr = Double.parseDouble(cmd.getOptionValue("cr"));
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid real part of the constant");
                System.exit(1);
            }
        }

        // get the imaginary part of the constant
        if(cmd.hasOption("ci")) {
            try {
                ci = Double.parseDouble(cmd.getOptionValue("ci"));
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid imaginary part of the constant");
                System.exit(1);
            }
        }

        // get the max iterations
        if (cmd.hasOption("maxIter")) {
            try {
                maxIter = Integer.parseInt(cmd.getOptionValue("maxIter"));
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid max iterations");
                System.exit(1);
            }
        }

        // get the xmin
        if (cmd.hasOption("xmin")) {
            try {
                xMin = Integer.parseInt(cmd.getOptionValue("xmin"));
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid X_MIN");
                System.exit(1);
            }
        }

        // get the xmax
        if (cmd.hasOption("xmax")) {
            try {
                xMax = Integer.parseInt(cmd.getOptionValue("xmax"));
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid X_MAX");
                System.exit(1);
            }
        }

        // get the ymin
        if (cmd.hasOption("ymin")) {
            try {
                yMin = Integer.parseInt(cmd.getOptionValue("ymin"));
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid Y_MIN");
                System.exit(1);
            }
        }

        // get the ymax
        if (cmd.hasOption("ymax")) {
            try {
                yMax = Integer.parseInt(cmd.getOptionValue("ymax"));
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid Y_MAX");
                System.exit(1);
            }
        }

        // get the filename
        if (cmd.hasOption("f")) {
            filename = cmd.getOptionValue("f");
            if (filename == null) {
                filename = "output.png";
            }
        }

        // generate the image
        if (mandelbrot) {
            MandelbrotSetGenerator m = new MandelbrotSetGenerator(width, height, maxIter, xMin, xMax, yMin, yMax);
            m.generate();
        }
        else {
            JuliaSetGenerator j = new JuliaSetGenerator(width, height, maxIter, xMin, xMax, yMin, yMax, cr, ci);
            j.generate();
        }
    }
}
