package com.example.sampleapp_moengage;



import android.app.DatePickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.moengage.cards.ui.CardActivity;
import androidx.appcompat.app.AppCompatActivity;
import com.moengage.inbox.*;

import com.moengage.cards.ui.CardActivity;
import com.moengage.core.DataCenter;
import com.moengage.core.LogLevel;
import com.moengage.core.MoECoreHelper;
import com.moengage.core.MoESdkStateHelper;
import com.moengage.core.MoEngage;
import com.moengage.core.analytics.MoEAnalyticsHelper;
import com.moengage.core.config.LogConfig;
import com.moengage.core.model.AppStatus;
import com.moengage.inapp.MoEInAppHelper;
import com.moengage.inbox.ui.view.InboxActivity;
import com.moengage.pushbase.MoEPushHelper;

public class MainActivity extends AppCompatActivity {

  private Button button;
  private Button cards;
  private Button signin;
  private Button signout;
  private EditText emaillogin;
  private Button inbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        MoEngage moEngage = new MoEngage.Builder(getApplication() ,"K5RQAWVLPPTTIA29F1XKRAGW",DataCenter.DATA_CENTER_1) // application
//                .configureLogs(new LogConfig(LogLevel.VERBOSE, false))
//                .build();

//        MoEngage.initialiseDefaultInstance(moEngage);

        super.onCreate(savedInstanceState);
        MoEInAppHelper.getInstance().showInApp(this);

        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.signup);
        cards=(Button) findViewById(R.id.cards);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, signup.class);
                MoECoreHelper.INSTANCE.logoutUser(getApplicationContext());
                startActivity(i);

                Log.e("Context", String.valueOf(getBaseContext()));
            }
        });

        //CARDS
        cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });

        // INBOX
        inbox= (Button) findViewById(R.id.inbox);
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InboxActivity.class);
                startActivity(intent);
            }
        });

        requestNotificationPermission();


        // LOG IN----------------------
         signin = (Button) findViewById(R.id.signin);
         emaillogin= findViewById(R.id.emailid);
         signin.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v) {
                 MoEAnalyticsHelper.INSTANCE.setUniqueId(getApplicationContext(),String.valueOf(emaillogin.getText()));

             }

         });
         signout=(Button) findViewById(R.id.singout);
         signout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 MoECoreHelper.INSTANCE.logoutUser(getApplicationContext());


             }
         });








    }

    private void requestNotificationPermission() {
        MoEPushHelper.getInstance().requestPushPermission(this);
    }

}