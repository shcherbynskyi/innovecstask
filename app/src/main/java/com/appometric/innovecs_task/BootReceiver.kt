package com.appometric.innovecs_task

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                Log.d(App.INNOVECS_TAG, "Boot completed")
                // TODO save boot completed event timestamp
            }
        }
    }
}