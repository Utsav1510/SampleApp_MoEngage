package com.example.sampleapp_moengage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

import com.moengage.core.DataCenter;
import com.moengage.core.LogLevel;
import com.moengage.core.MoECoreHelper;
import com.moengage.core.MoESdkStateHelper;
import com.moengage.core.MoEngage;
import com.moengage.core.analytics.MoEAnalyticsHelper;
import com.moengage.core.config.LogConfig;
import com.moengage.core.model.AppStatus;

public class MainActivity extends AppCompatActivity {

  private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MoEngage moEngage = new MoEngage.Builder(getApplication() ,"K5RQAWVLPPTTIA29F1XKRAGW") // application
                .setDataCenter(DataCenter.DATA_CENTER_1 )// datacenter
                .configureLogs(new LogConfig(LogLevel.VERBOSE, false))
                .build();

        MoEngage.initialiseDefaultInstance(moEngage);

        MoESdkStateHelper.enableAdIdTracking(getApplicationContext());

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        MoEAnalyticsHelper.INSTANCE.setAppStatus(getBaseContext(), AppStatus.INSTALL);



        button = (Button) findViewById(R.id.signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, signup.class);
                MoECoreHelper.INSTANCE.logoutUser(getApplicationContext());
                startActivity(i);

            }
        });



    }
}