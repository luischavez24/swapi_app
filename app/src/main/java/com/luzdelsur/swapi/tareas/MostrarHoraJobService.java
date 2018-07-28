package com.luzdelsur.swapi.tareas;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import android.os.Handler;

public class MostrarHoraJobService extends JobService {

    private static final int PERIOD_MS = 5000;

    public MostrarHoraJobService() { }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        CharSequence text = "Servicio Iniciado, Hora: " + new Date();

        Log.i(this.getClass().getSimpleName(), "Servicio Iniciado, Hora: " + new Date());

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG ).show();

        jobFinished(jobParameters, false);

        refreshSchedule(getApplicationContext());

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(this.getClass().getSimpleName(), "Servicio Finalizado");
        return false;
    }

    public static void refreshSchedule(Context appContext) {

        JobScheduler jobScheduler = (JobScheduler) appContext.getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(appContext ,MostrarHoraJobService.class);

        JobInfo jobInfo = new JobInfo.Builder(0x1, componentName)
                .setMinimumLatency(PERIOD_MS)
                .setOverrideDeadline(PERIOD_MS)
                .setRequiresCharging(true)
                .build();

        jobScheduler.schedule(jobInfo);
    }
}
