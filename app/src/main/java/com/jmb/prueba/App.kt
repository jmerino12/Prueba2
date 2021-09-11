package com.jmb.prueba

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}