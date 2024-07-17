package com.aagnia.universitiessite.fetching

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagnia.common.utils.FailureResponseWrapper
import com.aagnia.common.utils.SuccessResponseWrapper
import com.aagnia.domain.responseModels.UniversitiesResponseContainer
import com.aagnia.domain.usecase.UniversitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversitiesViewModel
@Inject
constructor(
    private val universitiesUseCase: UniversitiesUseCase
): ViewModel(){

    private val _universities = MutableStateFlow<List<UniversitiesResponseContainer>>(emptyList())
    val universities: StateFlow<List<UniversitiesResponseContainer>> get() = _universities

    fun universities(country: String){
        viewModelScope.launch {
            universitiesUseCase.fetchUniversities(country)
                .collect {
                    when (it) {
                        is SuccessResponseWrapper -> {
                            _universities.value = it.data
                            Log.d("UniversitiesViewModel", it.data.toString())
                        }
                        is FailureResponseWrapper -> Log.d("SignInViewModel", it.throwable.toString())
                    }
                }
        }
    }
}
