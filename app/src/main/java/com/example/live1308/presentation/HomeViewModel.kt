package com.example.live1308.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.live1308.data.Dao
import com.example.live1308.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dao: Dao
) : ViewModel() {
    val users: StateFlow<List<User>> = dao.observUsers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun addUser(
        name: String
    ) {
        viewModelScope.launch {
            dao.addUser(
                User(
                    name = name
                )
            )
        }
    }
}