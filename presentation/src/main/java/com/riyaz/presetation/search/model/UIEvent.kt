package com.riyaz.presetation.search.model

sealed interface UIEvent {
    data class OnSearch(val searchValue: String): UIEvent
}