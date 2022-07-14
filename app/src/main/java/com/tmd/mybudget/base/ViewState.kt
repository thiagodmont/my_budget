package com.tmd.mybudget.base

sealed class ViewState {
    object EMPTY : ViewState()
    object LOADING : ViewState()
    object LOADED : ViewState()
    object ERROR : ViewState()
}