package com.image.domain;

import com.image.logic.ImageData;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * TODO
 */
public class Image {
    private int id;
    private String fileName;
    private javafx.scene.image.Image imageView;
    private int userId;
    private ImageData imageData;

    public Image() {
    }

    public Image(int id, String fileName, InputStream imageView, int userId, ImageData imageData) {
        this.id = id;
        this.fileName = fileName;
        this.imageView = blobToImage(imageView);
        this.userId = userId;
        this.imageData = imageData;
    }

    public static javafx.scene.image.Image blobToImage(InputStream b) {
        /*BufferedImage bufferedImage = null;
        InputStream in = null;
        try {
            in = b.getBinaryStream();
            bufferedImage = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        javafx.scene.image.Image image = new javafx.scene.image.Image(b);

        return image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public javafx.scene.image.Image getImageView() {
        return imageView;
    }

    public void setImageView(InputStream imageView) {
        this.imageView = blobToImage(imageView);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }
}
