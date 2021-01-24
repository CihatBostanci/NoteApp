package com.task.noteapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel (private val homeRepository: HomeRepository): ViewModel() {


    private val _noteListLiveData = MutableLiveData<Resource<MutableList<NoteModel>>>()
    val noteListLiveData: LiveData<Resource<MutableList<NoteModel>>>
        get() = _noteListLiveData

    private val _deleteNoteLiveData = MutableLiveData<Resource<Boolean>>()
    val deleteNoteLiveData: LiveData<Resource<Boolean>>
        get() = _deleteNoteLiveData




    fun fetchDataAllNotes(userId: Int) {
        _noteListLiveData.postValue(Resource.loading(data = null))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dataNoteList = homeRepository.fetchAllNotes(userId)
                dataNoteList.let {
                    _noteListLiveData.postValue(Resource.success(data = it))
                }
            } catch (ex:Exception){
                _noteListLiveData.postValue(Resource.error(data= null, message = ex.message.toString() ))
            }
        }
    }

    fun deleteNoteItem( noteId: Int) {
        _deleteNoteLiveData.postValue(Resource.loading(data = null))
        viewModelScope.launch {
            try {

                homeRepository.deleteNote(noteId)
                _deleteNoteLiveData.postValue(Resource.success(data = true))

            } catch (ex:Exception){
                _deleteNoteLiveData.postValue(Resource.error(data= null, message = ex.message.toString() ))
            }


        }
    }



}