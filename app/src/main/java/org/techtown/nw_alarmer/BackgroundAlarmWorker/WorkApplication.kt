package org.techtown.nw_alarmer.BackgroundAlarmWorker

import android.app.Application
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkApplication : Application(){

    private val backgroundCoroutineScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayCreateWork()
    }

    private fun delayCreateWork(){
        backgroundCoroutineScope.launch {
            createWorkManager()
        }
    }

    private fun createWorkManager(){

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .build()

            //제약사항

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<AlarmWorker>().
        setConstraints(constraints).build()

        WorkManager.getInstance(applicationContext).enqueueUniqueWork(AlarmWorker.WORK_NAME, ExistingWorkPolicy.KEEP, oneTimeWorkRequest)



    }
    
}