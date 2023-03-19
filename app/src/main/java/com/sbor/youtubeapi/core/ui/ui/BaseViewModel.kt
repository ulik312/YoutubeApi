package com.sbor.youtubeapi.core.ui.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val loading = MutableLiveData<Boolean>()
}