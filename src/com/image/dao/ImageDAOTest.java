package com.image.dao;

import com.image.domain.Image;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vladik on 19.05.2017.
 */
public class ImageDAOTest {
    static ImageDAO imageDAO = DAOFactory.getImageDAO();
    static List<Image> list;

    public static void main(String[] args) throws DAOException {
        list =imageDAO.getAllByUserId(7);
        System.out.println(list.size());
    }

    public ImageDAOTest() throws DAOException {
    }
}