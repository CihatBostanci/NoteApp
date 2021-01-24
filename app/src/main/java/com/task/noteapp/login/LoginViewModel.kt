package com.task.noteapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.database.model.UserModel
import com.task.noteapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository): ViewModel() {

    private val _allUserLiveData = MutableLiveData<Resource<MutableList<UserModel>>>()
    val allUserLiveData: LiveData<Resource<MutableList<UserModel>>>
        get() = _allUserLiveData

    private val _addUserLiveData  = MutableLiveData<Resource<Boolean>>()
    val addUserLiveData: LiveData<Resource<Boolean>>
        get() = _addUserLiveData

    private val _getUserLiveData  = MutableLiveData<Resource<UserModel>>()
    val getUserLiveData: LiveData<Resource<UserModel>>
        get() = _getUserLiveData

    fun fetchDataAllUsers() {
        _allUserLiveData.postValue(Resource.loading(data = null))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dataUserList = loginRepository.fetchAllUsers()
                dataUserList.let {
                    _allUserLiveData.postValue(Resource.success(data = it))
                }
            } catch (ex:Exception){
                _allUserLiveData.postValue(Resource.error(data= null, message = ex.message.toString() ))
            }
        }
    }

    fun addUser( user: UserModel) {
        _addUserLiveData.postValue(Resource.loading(data = null))
        viewModelScope.launch {
            try {

                loginRepository.addUser(user)
                _addUserLiveData.postValue(Resource.success(data = true))

            } catch (ex:Exception){
                _addUserLiveData.postValue(Resource.error(data= null, message = ex.message.toString() ))
            }
        }
    }


    fun getUserByEmailAndPassword(email:String, password:String){
        _getUserLiveData.postValue(Resource.loading(data = null))
        viewModelScope.launch {
            try {
                val user = loginRepository.getUserByEmailAndPassword(email, password)
                _getUserLiveData.postValue(Resource.success(data = user))

            } catch (ex:Exception){
                _getUserLiveData.postValue(Resource.error(data= null, message = ex.message.toString() ))
            }
        }
    }


}