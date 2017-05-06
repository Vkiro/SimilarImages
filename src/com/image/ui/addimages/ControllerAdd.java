package com.image.ui.addimages;

import com.image.dao.DAOException;
import com.image.dao.DAOFactory;
import com.image.dao.ImageDAO;
import com.image.logic.Finder;
import com.image.logic.ImageData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * TODO
 */
public class ControllerAdd implements Initializable {
    public static List<com.image.domain.Image> result = new ArrayList<>();
    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        List<InputStream> images = new ArrayList<>();

        for (int i = 0; i < selectedFiles.size(); i++) {
            try {
                images.add(new FileInputStream(selectedFiles.get(i)));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageDAO imageDAO = DAOFactory.getImageDAO();
            ImageData imageData = Finder.createImageData(selectedFiles.get(i).getAbsolutePath());

            try {
                imageDAO.createImage(selectedFiles.get(i).getName(), images.get(i), imageData, 4);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
    }

}