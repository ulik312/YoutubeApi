package com.sbor.youtubeapi

import android.app.Application
import com.sbor.youtubeapi.repozitory.Repozitory

class App : Application() {
    companion object {
        val repozitory: Repozitory by lazy {
            Repozitory()
        }
    }
}