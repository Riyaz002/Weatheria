package com.riyaz.presetation.home.model

sealed interface UIEvent {
    data class OnSearch(val searchValue: String): UIEvent
}