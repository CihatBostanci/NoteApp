package com.task.noteapp.updatenote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.utils.Resource
import kotlinx.coroutines.launch

class UpdateNoteViewModel(private val updateNoteRepository: UpdateNoteRepository) : ViewModel() {

    private val _updatedNoteLiveData = MutableLiveData<Resource<Boolean>>()
    val updatedNoteLiveData: LiveData<Resource<Boolean>>
        get() = _updatedNoteLiveData

    fun updateNoteItem( note: NoteModel) {
        _updatedNoteLiveData.postValue(Resource.loading(data = null))
        viewModelScope.launch {
            try {
                updateNoteRepository.updateNote(note)
                _updatedNoteLiveData.postValue(Resource.success(data = true))

            } catch (ex:Exception){
                _updatedNoteLiveData.postValue(Resource.error(data= null, message = ex.message.toString() ))
            }
        }
    }
}