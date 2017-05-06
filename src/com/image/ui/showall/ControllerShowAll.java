package com.image.ui.showall;

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
public class ControllerShowAll implements Initializable {

    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageDAO imageDAO = DAOFactory.getImageDAO();
        List<com.image.domain.Image> imageList = null;
        try {
            imageList = imageDAO.getAllImages();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        writeImages(imageList);
    }

    public void writeImages(List<com.image.domain.Image> images) {
        List<javafx.scene.image.Image> list = new ArrayList<>();
        for (com.image.domain.Image i : images) {
            list.add(com.image.domain.Image.blobToImage(i.getImageView()));
        }
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(list.get(i));
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            imageViews.add(imageView);
        }

        int count = 0;
        for (int i = 0; i < (list.size() + 2) / 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (count == list.size()) break;
                GridPane.setMargin(imageViews.get(count), new Insets(5, 5, 5, 5));
                gridPane.add(imageViews.get(count), j, i);
                count++;
            }
        }
    }
}

