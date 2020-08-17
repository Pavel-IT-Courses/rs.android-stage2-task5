package com.example.finalproject.repository;

import com.example.finalproject.model.UserModel;
import com.example.finalproject.remote.UserService;

import io.reactivex.Single;

public class UserRepository {
    private UserService userService;

    public UserRepository(UserService userService) {
        this.userService = userService;
    }
    public Single<UserModel> modelSingle() {
        return userService.getUserModel();
    }
}
