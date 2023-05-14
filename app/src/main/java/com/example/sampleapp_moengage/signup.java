package com.example.sampleapp_moengage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;



import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moengage.core.DataCenter;
import com.moengage.core.MoESdkStateHelper;
import com.moengage.core.MoEngage;
import com.moengage.core.analytics.MoEAnalyticsHelper;
import com.moengage.core.model.AppStatus;
import com.moengage.core.model.UserGender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class signup extends AppCompatActivity
{
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button signin;
    private String gender="male";
    private EditText name;
    private EditText email;
    private EditText phone;
    private String dob;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        setContentView(R.layout.activity_signup);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        signin=findViewById(R.id.signin);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        addListenerOnButton();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin.setText(name.getText());
                MoEAnalyticsHelper.INSTANCE.setUniqueId(getApplicationContext(),String.valueOf(email.getText()));
                MoEAnalyticsHelper.INSTANCE.setEmailId(getApplicationContext(), String.valueOf(email.getText()));
                MoEAnalyticsHelper.INSTANCE.setMobileNumber(getApplicationContext(),String.valueOf(phone.getText()));
                MoEAnalyticsHelper.INSTANCE.setUserName(getApplicationContext(), String.valueOf(name.getText()));

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Date date = null;
                try {
                    date = format.parse(dob);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                Log.d("Date",String.valueOf(date));
                MoEAnalyticsHelper.INSTANCE.setBirthDate(getApplicationContext(), date);

                if (gender=="male") {
                    MoEAnalyticsHelper.INSTANCE.setGender(getApplicationContext(), UserGender.MALE);
                }
                else{
                    MoEAnalyticsHelper.INSTANCE.setGender(getApplicationContext(), UserGender.FEMALE);
                }


            }

        });
//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signin.setText(name.getText());
//                MoEAnalyticsHelper.INSTANCE.setUniqueId(getApplicationContext(),email.getText());
//                MoEAnalyticsHelper.INSTANCE.setMobileNumber(getApplicationContext(),String.valueOf(phone.getText()));
//                MoEAnalyticsHelper.INSTANCE.setUserName(getApplicationContext(), String.valueOf(name.getText()));
//                MoEAnalyticsHelper.INSTANCE.setBirthDate(getApplicationContext(), dob);
//
//
//            }
//
//        });


    }
    public void addListenerOnButton() {
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        btnDisplay = findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
// get selected radio button from radioGroup
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
// find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);
                gender=radioSexButton.getText().toString();
                btnDisplay.setText(gender);
                Log.d("Gender",gender);

            }
        });
    }




    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {

        dob = year+"-"+month +"-"+day+"T09:27:37Z" ;


        return getMonthFormat(month) + " " + day + " " + year;

    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}


