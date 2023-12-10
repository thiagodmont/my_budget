package com.tmd.mybudget.base

sealed class ViewState {
    object INITIAL : ViewState()
    object LOADING : ViewState()
    object LOADED : ViewState()
    object ERROR : ViewState()
}