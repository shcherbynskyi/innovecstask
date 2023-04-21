package com.appometric.innovecs_task

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class BootWorker(passedContext: Context, params: WorkerParameters) : Worker(passedContext, params) {

    override fun doWork(): Result = try {
        // TODO get the info about the latest boot event and show/update the notification
        Result.success()
    } catch (error: Throwable) {
        Log.e(App.INNOVECS_TAG, error.message ?: "Something went wrong with worker task")
        Result.failure()
    }

    companion object {
        const val BOOT_WORK_MANAGER_TAG = "boot_work_manager_tag"
        const val BOOT_WORKER_REPEAT_INTERVAL: Long = 900000L
    }
}