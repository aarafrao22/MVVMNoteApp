package com.aarafrao.mvvm.UserViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aarafrao.mvvm.Repository.Repository
import com.aarafrao.mvvm.User

class UserViewModel: ViewModel() {

    fun insert(context: Context,user: User){
        Repository.insert(context,user)
    }
    fun getAllUserData(context: Context):LiveData<List<User>>?{
        return Repository.getAllUserData(context)
    }
}