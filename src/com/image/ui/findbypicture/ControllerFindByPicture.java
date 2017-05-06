package com.image.ui.findbypicture;

import com.image.dao.DAOException;
import com.image.dao.DAOFactory;
import com.image.dao.ImageDAO;
import com.image.logic.Finder;
import com.image.logic.ImageData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vladik on 06.05.2017.
 */
public class ControllerFindByPicture implements Initializable{
    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView imageView;

    public void imageLoader(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        ImageData inputData = Finder.createImageData(selectedFile.getAbsolutePath());

        Image image = new Image(selectedFile.toURI().toString());
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setImage(image);

        ImageDAO imageDAO = DAOFactory.getImageDAO();
        try {
            List<com.image.domain.Image> imageList = imageDAO.getAllImages();
            List<com.image.domain.Image> result = new ArrayList<>();
            for (com.image.domain.Image i : imageList) {
                if (Finder.match(inputData, i.getImageData()) > 15) {
                    result.add(i);
                }
            }
            writeImages(result);
        } catch (DAOException e) {
            e.printStackTrace();
        }
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
            /*imageViews.get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Tile pressed ");
                    event.consume();
                }
            });*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}