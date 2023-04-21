package com.appometric.innovecs_task

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        const val INNOVECS_TAG = "INNOVECS-TASK"
    }
}