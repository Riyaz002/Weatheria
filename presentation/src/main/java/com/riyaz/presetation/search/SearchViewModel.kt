package com.riyaz.presetation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyaz.domain.model.forecast.LocationCoordinate
import com.riyaz.domain.usecase.GetForecastUseCase
import com.riyaz.presetation.search.model.SearchPageState
import com.riyaz.presetation.search.model.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(val getForecastUseCase: GetForecastUseCase) : ViewModel() {
    val state = MutableStateFlow(SearchPageState())

    init {
        viewModelScope.launch {
            state.update { it.copy(forecast = getForecastUseCase(
                LocationCoordinate(77.102493, 28.704060)
            ))
            }
        }
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.OnSearch -> {
                Log.e("TEST", event.searchValue)
            }
        }
    }
}