package com.best.service;

import com.best.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
