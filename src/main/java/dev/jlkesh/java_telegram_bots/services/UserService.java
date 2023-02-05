package dev.jlkesh.java_telegram_bots.services;

import dev.jlkesh.java_telegram_bots.daos.UserDao;
import dev.jlkesh.java_telegram_bots.domains.UserDomain;
import dev.jlkesh.java_telegram_bots.dto.Response;
import dev.jlkesh.java_telegram_bots.utils.BaseUtils;
import dev.jlkesh.java_telegram_bots.utils.PasswordEncoderUtils;
import lombok.NonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Response<Void> create(@NonNull UserDomain domain) {
        try {
            domain.setPassword(PasswordEncoderUtils.encode(domain.getPassword()));
            userDao.save(domain);
            return new Response<>(null);
        } catch (SQLException e) {
            // TODO: 05/02/23 log
            return new Response<>("Something is wrong try later again", BaseUtils.getStackStraceAsString(e));
        }
    }

    public Response<List<UserDomain>> getAllUsers() {
        try {
            return new Response<>(userDao.findAll());
        } catch (SQLException e) {
            // TODO: 05/02/23 log
            return new Response<>(e.getMessage(), BaseUtils.getStackStraceAsString(e));
        }
    }
}
