package com.example.finalproject.remote;

import com.example.finalproject.model.UserModel;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface UserService {

    @GET("/users/2")
    Single<UserModel> getUserModel();
}
