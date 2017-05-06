package com.image.ui;

import com.image.dao.DAOException;
import com.image.dao.DAOFactory;
import com.image.dao.ImageDAO;
import com.image.logic.Finder;
import com.image.logic.ImageData;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * TODO
 */
public class ControllerAll implements Initializable {
    Image image;

    @FXML
    GridPane gridPane;
    ArrayList<ImageView> imageViews;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*ImageDAO imageDAO = DAOFactory.getImageDAO();
        try {
            List<com.image.domain.Image> imageList = imageDAO.getAllImages();
            loadImage(imageList);
            for (com.image.domain.Image i : imageList) {
                for (com.image.domain.Image j : imageList) {
                    Finder.match(i.getImageData(), j.getImageData());
                }
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }*/
    }


    public void buttonPressed(ActionEvent actionEvent) {
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
                imageDAO.createImage(selectedFiles.get(i).getName(), images.get(i), imageData, 3);
            } catch (DAOException e) {
                e.printStackTrace();
            }


            // loadImage(images);
        }
    }

    public void loadImage(List<com.image.domain.Image> images) {
            /*BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("C:\\Users\\Vladik\\IdeaProjects\\YakovFain\\ip1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);*/

        List<Image> list = new ArrayList<>();
        for (com.image.domain.Image i : images) {
            list.add(com.image.domain.Image.blobToImage(i.getImageView()));
        }

        imageViews = new ArrayList<>();
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

    public void secondButtonPressed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        ImageData inputData = Finder.createImageData(selectedFile.getAbsolutePath());

        ImageDAO imageDAO = DAOFactory.getImageDAO();
        try {
            List<com.image.domain.Image> imageList = imageDAO.getAllImages();
            List<com.image.domain.Image> result = new ArrayList<>();
            int counter = 0;
            for (com.image.domain.Image i : imageList) {
                if (Finder.match(inputData, i.getImageData()) > 15) {
                    result.add(i);
                }
            }
            loadImage(result);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}

