package com.image.dao;

import com.image.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class UserDAO {
    public List<User> getAll() throws DAOException {
        DAOFactory factory = new DAOFactory();
        //TODO change *
        String query = "SELECT id, login, password, firstName, lastName, email, keyWord FROM Users";
        ArrayList<User> listUsers = new ArrayList<>();
        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setKeyWord(resultSet.getString("keyWord"));
                listUsers.add(user);
            }
        } catch (SQLException sqle) {
            throw new DAOException("Can`t execute query.", sqle);
        }
        return listUsers;
    }

    public User getById(int userId) throws DAOException {
        DAOFactory factory = new DAOFactory();
        String query = "SELECT id, login, password, firstName, lastName, email, keyWord FROM Users WHERE id = ?";
        User user;
        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setKeyWord(resultSet.getString("keyWord"));
            } catch (SQLException sqle) {
                throw new DAOException("Can`t execute query", sqle);
            }
        } catch (SQLException sqle) {
            throw new DAOException("Can`t execute query", sqle);
        }
        return user;
    }

    public int getId(String login, String password) throws DAOException {
        DAOFactory factory = new DAOFactory();
        String query = "SELECT id FROM Users WHERE login = ? AND password = ?";
        int id = 0;
        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                id = resultSet.getInt("id");
            } catch (SQLException sqle) {
                throw new DAOException("Can`t execute query", sqle);
            }
        } catch (SQLException sqle) {
            throw new DAOException("Can`t execute query", sqle);
        }
        return id;
    }

    public User getByKeyWord(String keyWord) throws DAOException {
        DAOFactory factory = new DAOFactory();
        String query = "SELECT id, login, password, firstName, lastName, email, keyWord FROM Users WHERE keyWord = ?";
        User user;
        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, keyWord);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setKeyWord(keyWord);
            } catch (SQLException sqle) {
                throw new DAOException("Can`t execute query", sqle);
            }
        } catch (SQLException sqle) {
            throw new DAOException("Can`t execute query", sqle);
        }
        return user;
    }
}
