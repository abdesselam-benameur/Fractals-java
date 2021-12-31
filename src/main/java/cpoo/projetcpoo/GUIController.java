package cpoo.projetcpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.awt.image.BufferedImage;

public class GUIController {
    private final int width = 800;
    private final int height = 800;
    private double X_MAX;
    private double Y_MAX;
    private double X_MIN;
    private double Y_MIN;
    private String selected_choice;
    private double CI;
    private double CR;
    private int MAX_ITER;
    private double ZOOM;
    private boolean generated;

    @FXML
    private ToggleGroup choice;
    @FXML
    private TextField ci;
    @FXML
    private TextField cr;
    @FXML
    private ImageView image = null;
    @FXML
    private RadioButton julia;
    @FXML
    private TextField maxiter;
    @FXML
    private TextField xmax;
    @FXML
    private TextField xmin;
    @FXML
    private TextField ymax;
    @FXML
    private TextField ymin;
    @FXML
    private TextField zoom;

    @FXML
    void onImageClicked(MouseEvent event) {
        if (generated) {
            double deltaX = X_MAX - X_MIN;
            double deltaY = Y_MAX - Y_MIN;
            double x = event.getSceneX() - 223;
            double x_center = X_MIN + deltaX * x / width;
            double y = event.getSceneY();
            double y_center = Y_MIN + deltaY * y / height;

            ZOOM = Double.parseDouble(this.zoom.getText());
            // check if right click
            if (event.getButton() == MouseButton.PRIMARY) {
                deltaX = (X_MAX - X_MIN) / ZOOM;
                deltaY = (Y_MAX - Y_MIN) / ZOOM;
            }
            // else left click
            else if (event.getButton() == MouseButton.SECONDARY) {
                deltaX = (X_MAX - X_MIN) * ZOOM;
                deltaY = (Y_MAX - Y_MIN) * ZOOM;
            }

            deltaX /= 2;
            deltaY /= 2;
            X_MIN = x_center - deltaX;
            X_MAX = x_center + deltaX;
            Y_MIN = y_center - deltaY;
            Y_MAX = y_center + deltaY;

            BufferedImage bf;
            if (selected_choice.equals("Julia")) {
                JuliaSetGenerator j = new JuliaSetGenerator(width, height, MAX_ITER, X_MIN, X_MAX, Y_MIN, Y_MAX, CR, CI);
                bf = j.generate();
            } else {
                MandelbrotSetGenerator m = new MandelbrotSetGenerator(width, height, MAX_ITER, X_MIN, X_MAX, Y_MIN, Y_MAX);
                bf = m.generate();
            }

            WritableImage wr = new WritableImage(width, height);
            PixelWriter pw = wr.getPixelWriter();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    pw.setArgb(i, j, bf.getRGB(i, j));
                }
            }
            this.image.setImage(wr);
        }
    }

    @FXML
    void onGenerate() {
        generated = true;
        X_MIN = Double.parseDouble(this.xmin.getText());
        X_MAX = Double.parseDouble(this.xmax.getText());
        Y_MIN = Double.parseDouble(this.ymin.getText());
        Y_MAX = Double.parseDouble(this.ymax.getText());
        MAX_ITER = Integer.parseInt(this.maxiter.getText());
        CI = Double.parseDouble(this.ci.getText());
        CR = Double.parseDouble(this.cr.getText());
        selected_choice = ((RadioButton) this.choice.getSelectedToggle()).getText();
        ZOOM = Double.parseDouble(this.zoom.getText());

        BufferedImage bf;
        if (selected_choice.equals("Julia")) {
            JuliaSetGenerator j = new JuliaSetGenerator(width, height, MAX_ITER, X_MIN, X_MAX, Y_MIN, Y_MAX, CR, CI);
            bf = j.generate();
        } else {
            MandelbrotSetGenerator m = new MandelbrotSetGenerator(width, height, MAX_ITER, X_MIN, X_MAX, Y_MIN, Y_MAX);
            bf = m.generate();
        }

        WritableImage wr = new WritableImage(width, height);
        PixelWriter pw = wr.getPixelWriter();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pw.setArgb(i, j, bf.getRGB(i, j));
            }
        }
        this.image.setImage(wr);
    }
}
