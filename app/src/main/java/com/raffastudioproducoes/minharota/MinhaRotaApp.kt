package com.raffastudioproducoes.minharota

import android.app.Application

class MinhaRotaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicializa apenas o CrashHandler conforme solicitado na Fase 1
        CrashHandler(this)
    }
}
