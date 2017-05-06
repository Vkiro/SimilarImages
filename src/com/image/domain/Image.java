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
    private Blob imageView;
    private int userId;
    private ImageData imageData;

    public Image() {
    }

    public Image(int id, String fileName, Blob imageView, int userId, ImageData imageData) {
        this.id = id;
        this.fileName = fileName;
        this.imageView = imageView;
        this.userId = userId;
        this.imageData = imageData;
    }

    public static javafx.scene.image.Image blobToImage(Blob b) {
        BufferedImage bufferedImage = null;
        try {
            InputStream in = b.getBinaryStream();
            bufferedImage = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        javafx.scene.image.Image image = SwingFXUtils.toFXImage(bufferedImage, null);

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

    public Blob getImageView() {
        return imageView;
    }

    public void setImageView(Blob imageView) {
        this.imageView = imageView;
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
