package com.example.sampleapp_moengage;

import android.app.Application;

import com.moengage.core.DataCenter;
import com.moengage.core.LogLevel;
import com.moengage.core.MoESdkStateHelper;
import com.moengage.core.MoEngage;
import com.moengage.core.analytics.MoEAnalyticsHelper;
import com.moengage.core.config.FcmConfig;
import com.moengage.core.config.LogConfig;
import com.moengage.core.config.NotificationConfig;
import com.moengage.core.model.AppStatus;
import com.moengage.inapp.MoEInAppHelper;


public class MyApplication extends Application {
//K5RQAWVLPPTTIA29F1XKRAGW
    private String appId="2877NHMD0TOHATHC6NNHDERW";
    @Override
    public void onCreate() {
        super.onCreate();

        MoEngage moEngage = new MoEngage.Builder(this,appId , DataCenter.DATA_CENTER_1 )
//                .configureNotificationMetaData(NotificationConfig(R.drawable.small_icon, R.drawable.large_icon))
                .configureFcm(new FcmConfig(true))
                .configureLogs(new LogConfig(LogLevel.VERBOSE, true))
                .configureNotificationMetaData(new NotificationConfig(R.drawable.small_icon, R.drawable.large_icon))
                .build();



        MoEngage.initialiseDefaultInstance(moEngage);
        MoESdkStateHelper.enableAdIdTracking(this);
        MoEAnalyticsHelper.INSTANCE.setAppStatus(getBaseContext(), AppStatus.INSTALL);


    }
}
