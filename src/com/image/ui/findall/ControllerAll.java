package com.image.ui.findall;

import com.image.dao.DAOException;
import com.image.dao.DAOFactory;
import com.image.dao.ImageDAO;
import com.image.dao.UserDAO;
import com.image.domain.User;
import com.image.logic.Finder;
import com.image.ui.userinformationpage.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * TODO
 */
public class ControllerAll implements Initializable {
    public static com.image.domain.Image[][] result;
    @FXML
   private GridPane gridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        result = new com.image.domain.Image[1000][2];
        ImageDAO imageDAO = DAOFactory.getImageDAO();
        int rowCounter = -1;
        try {
            List<com.image.domain.Image> imageList = imageDAO.getAllImages();
            for (int i = 0; i < imageList.size(); i++) {
                for (int j = i + 1; j < imageList.size(); j++) {
                    double accum = Finder.match(imageList.get(i).getImageData(), imageList.get(j).getImageData());
                    if (accum > 30) {
                        writeImages(imageList.get(i), imageList.get(j), ++rowCounter);
                    }
                }
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    private void writeImages(com.image.domain.Image i, com.image.domain.Image j, int row) {
        Image image1 = i.getImageView();
        Image image2 = j.getImageView();

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);

        imageView1.setFitHeight(150);
        imageView1.setFitWidth(150);
        imageView2.setFitHeight(150);
        imageView2.setFitWidth(150);

        GridPane.setMargin(imageView1, new Insets(5, 5, 5, 95));
        GridPane.setMargin(imageView2, new Insets(5, 5, 5, 5));
        gridPane.add(imageView1, 0, row);
        gridPane.add(imageView2, 1, row);

        result[row][0] = i;
        result[row][1] = j;
    }

    public void buttonPressedAdd(ActionEvent actionEvent) {


    }

    public void buttonPressed(ActionEvent actionEvent) {
        Parent root;
        try {
            // TODO add path
            root = FXMLLoader.load(getClass().getResource("../thirdpage/third.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gridPaneClicked(MouseEvent mouseEvent) {
        for( Node node: gridPane.getChildren()) {
            if( node instanceof ImageView) {
                if( node.getBoundsInParent().contains(mouseEvent.getX(),  mouseEvent.getY())) {
                    System.out.println( "Node: " + node + " at " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex( node));
                    UserDAO userDAO = new UserDAO();
                    User user;
                    Parent root;
                    try {
                        user = userDAO.getById(result[GridPane.getRowIndex( node)][GridPane.getColumnIndex( node)].getUserId());
                        // TODO add path
                        UserController.user = user;
                        root = FXMLLoader.load(getClass().getResource("../userinformationpage/user.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("My New Stage Title");
                        stage.setScene(new Scene(root, 500, 600));
                        stage.show();

                    } catch (DAOException | IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }


    /*public void buttonPressed(ActionEvent actionEvent) { // Load all images from HardDisc
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


            // loadImage(images);
        }
    }
*/
    /*public void loadImage(List<com.image.domain.Image> images) {
            *//*BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("C:\\Users\\Vladik\\IdeaProjects\\YakovFain\\ip1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);*//*

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
            *//*imageViews.get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Tile pressed ");
                    event.consume();
                }
            });*//*
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
    }*/
}

