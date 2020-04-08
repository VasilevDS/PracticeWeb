package ru.vasilev.service;

import ru.vasilev.model.User;

public interface UserService {
    String login(User user);
    String registration(User user);
}
