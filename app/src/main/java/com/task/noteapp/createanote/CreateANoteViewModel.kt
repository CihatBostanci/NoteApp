package com.task.noteapp.createanote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.utils.Resource
import kotlinx.coroutines.launch

class CreateANoteViewModel (private val createNoteRepository: CreateNoteRepository) :ViewModel() {


    private val _createANoteLiveData = MutableLiveData<Resource<Boolean>>()
    val createANoteLiveData: LiveData<Resource<Boolean>>
        get() = _createANoteLiveData



     fun addNote( note:NoteModel) {
         _createANoteLiveData.postValue(Resource.loading(data = null))
         viewModelScope.launch {
             try {

                 createNoteRepository.addNote(note)
                 _createANoteLiveData.postValue(Resource.success(data = true))

             } catch (ex:Exception){
                 _createANoteLiveData.postValue(Resource.error(data= null, message = ex.message.toString() ))
             }


        }
    }

}