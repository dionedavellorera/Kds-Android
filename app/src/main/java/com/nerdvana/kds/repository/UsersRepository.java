package com.nerdvana.kds.repository;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.nerdvana.kds.model.OrderListModel;
import com.nerdvana.kds.model.UserModel;

import java.util.List;

public class UsersRepository {
    private static UsersRepository INSTANCE;

    @NonNull
    private MutableLiveData<List<UserModel>> userListLiveData = new MutableLiveData<>();

    public static UsersRepository getInstance() {

        if(INSTANCE == null) {
            synchronized (UsersRepository.class) {
                if(INSTANCE == null) {
                    INSTANCE = new UsersRepository();
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    public MutableLiveData<List<UserModel>> getUserListLiveData() {
        return userListLiveData;
    }
}
