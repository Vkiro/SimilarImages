package com.image.dao;


import com.image.domain.Image;
import com.image.logic.ImageData;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class ImageDAO {

    public List<Image> getAllImages() throws DAOException {
        DAOFactory factory = new DAOFactory();
        //TODO
        String query = "SELECT * FROM Images";
        List<Image> listImages = new ArrayList<>();

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Image image = new Image();
                image.setId(resultSet.getInt("id"));
                image.setFileName(resultSet.getString("fileName"));
                image.setImageView(resultSet.getBlob("imageView"));
                // Get object from DB
                ObjectInputStream in = new ObjectInputStream(resultSet.getBlob("imageData").getBinaryStream());
                ImageData imageData = (ImageData) in.readObject();
                image.setImageData(imageData);
                listImages.add(image);
                //listImages.add(com.image.domain.Image.blobToImage(resultSet.getBlob("imageView")));
            }
        } catch (SQLException | IOException e) {
            throw new DAOException("Can`t execute query", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listImages;
    }

    public void createImage(String fileName, InputStream imageView, ImageData imageData, int userId) throws DAOException {
        DAOFactory factory = new DAOFactory();
        String query = "INSERT INTO images (fileName, imageView, imageData, userId) VALUES (?, ?, ?, ?)";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, fileName);
            statement.setBlob(2, imageView);
            statement.setObject(3, imageData);
            statement.setInt(4, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can`t execute query", e);
        }
    }

}
