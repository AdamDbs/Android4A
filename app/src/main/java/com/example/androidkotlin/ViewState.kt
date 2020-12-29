package com.example.androidkotlin

sealed class ViewState

object LoadingViewState : ViewState()
object ErrorViewState : ViewState()

data class DataViewState(val rickAndMortylist : List<RickAndMorty>) : ViewState()


