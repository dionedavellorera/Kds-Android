package com.nerdvana.kds.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.nerdvana.kds.model.OrderDetailsModel;
import com.nerdvana.kds.model.OrderListModel;
import com.nerdvana.kds.model.UserModel;
import com.nerdvana.kds.repository.OrdersRepository;
import com.nerdvana.kds.repository.UsersRepository;

import java.util.List;

public class UsersViewModel extends AndroidViewModel {
    private UsersRepository usersRepository = UsersRepository.getInstance();

    @NonNull
    private MutableLiveData<List<UserModel>> userListLiveData;


    public UsersViewModel(@NonNull Application application) {
        super(application);

        userListLiveData = usersRepository.getUserListLiveData();
    }

    @NonNull
    public MutableLiveData<List<UserModel>> getUserListLiveData() {
        return userListLiveData;
    }

    public boolean hasLoggedInUser(List<UserModel> userList) {
        boolean hasUser = true;
        if (userList.size() < 1) {
            hasUser = false;
        }
        return  hasUser;
    }
}
