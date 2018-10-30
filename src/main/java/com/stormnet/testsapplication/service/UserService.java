package com.stormnet.testsapplication.service;

import com.stormnet.testsapplication.connector.ConnectionPool;
import com.stormnet.testsapplication.model.User;
import com.stormnet.testsapplication.model.UserLogin;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserService {

    private static final String SALT = "stormnet";

    private static final String GET_USER_QUERY = "SELECT `users`.`id`,\n" +
            "    `users`.`name`,\n" +
            "    `users`.`lastName`,\n" +
            "    `users`.`login`,\n" +
            "    `users`.`password`,\n" +
            "    `users`.`role_id`\n" +
            "FROM `tests-appication`.`users`\n" +
            "where users.login='%s' and users.password='%s'";

    public User getUserFromUserLogin(UserLogin userLogin) {
        User result = null;
        try {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String encodedPassword = new String(Base64.decode(SALT + userLogin.getPassword()));

        String query = String.format(GET_USER_QUERY, userLogin.getLogin(), encodedPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getString("login"),
                        resultSet.getInt("roleId"));
            }
        } catch (SQLException | Base64DecodingException e) {
            e.printStackTrace();
        }
        return result;
    }


}
