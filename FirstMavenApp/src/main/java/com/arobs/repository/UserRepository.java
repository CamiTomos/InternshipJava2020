package com.arobs.repository;

import com.arobs.connection.DataSource;
import com.arobs.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public UserRepository() {
    }

    public User findUserByCredentials(String username, String password) {
        System.out.println("User repo: find by credentials");
        String queryFind = "select ID,username, password from users where username=? AND password=?";
        User foundUser = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(queryFind);
             ) {
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt(1);
                String user = rs.getString(2);
                String pass = rs.getString(3);
                foundUser = new User(ID, user, pass);
                System.out.println(foundUser.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundUser;
    }

    public List<User> getAll() {
        System.out.println("User repo: getAll");
        List<User> allUsers = new ArrayList<>();
        String queryFind = "select * from users";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(queryFind);
             ResultSet rs = pst.executeQuery();
        ) {

            while (rs.next()) {
                int ID = rs.getInt(1);
                String user = rs.getString(2);
                String pass = rs.getString(3);
                User foundUser = new User(ID, user, pass);
                allUsers.add(foundUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
}
