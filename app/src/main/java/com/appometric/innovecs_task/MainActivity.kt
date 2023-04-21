package com.appometric.innovecs_task

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val bootReceiver: BootReceiver = BootReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerBootReceiver()
    }

    override fun onStart() {
        super.onStart()
        scheduleBootUpdate()
    }

    private fun scheduleBootUpdate() {
        // TODO start work manager (with BootWorker) here
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val bootWorkRequest: PeriodicWorkRequest = PeriodicWorkRequest.Builder(
            workerClass = BootWorker::class.java,
            repeatInterval = BootWorker.BOOT_WORKER_REPEAT_INTERVAL,
            repeatIntervalTimeUnit = TimeUnit.MILLISECONDS
        )
            .addTag(BootWorker.BOOT_WORK_MANAGER_TAG)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                BootWorker.BOOT_WORK_MANAGER_TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                bootWorkRequest
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterBootReceiver()
    }

    private fun registerBootReceiver() {
        val bootIntentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BOOT_COMPLETED)
        }
        applicationContext.registerReceiver(bootReceiver, bootIntentFilter)
    }

    private fun unregisterBootReceiver() {
        applicationContext.unregisterReceiver(bootReceiver)
    }
}