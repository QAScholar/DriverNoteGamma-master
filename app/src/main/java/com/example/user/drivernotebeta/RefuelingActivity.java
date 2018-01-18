package com.example.user.drivernotebeta;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RefuelingActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextL;
    EditText editTextR;
    TextView textViewRub;
    Button buttonSave;
    Button buttonMainMenu;

    float num1 = 0;
    float num2 = 0;
    float result = 0;

    //Создаем переменные для вызова календаря
    private EditText callcalendar;
    private DatePickerDialog datepicker;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_refueling );

        //Указываем формат даты
        dateFormatter=new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        //Создаем методы для запуска календаря
        findViewsByID();
        setDateTimeField();

        editTextL = findViewById( R.id.editTextL );
        editTextR = findViewById( R.id.editTextR );
        textViewRub = findViewById( R.id.textViewRub );

        buttonSave = findViewById( R.id.buttonSave );
        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // читаем EditText и заполняем переменные числами
                num1 = Float.parseFloat(editTextL.getText().toString());
                num2 = Float.parseFloat(editTextR.getText().toString());
                result = num1 * num2;
                textViewRub.setText(result + " руб. ");
            }
        });
        buttonMainMenu=findViewById(R.id.buttonMainMenu);
        buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToMainMenu=new Intent(RefuelingActivity.this,MainActivity.class);
               GoToMainMenu.putExtra("name", String.valueOf(result));
               startActivity(GoToMainMenu);
            }
        });
    }
//Описываем методы
    private void findViewsByID(){
            callcalendar=(EditText) findViewById(R.id.editTextCalendar);
            callcalendar.setInputType(InputType.TYPE_NULL);
            callcalendar.requestFocus();
    }
    private void setDateTimeField(){
        callcalendar.setOnClickListener(this);
        Calendar newCalendar=Calendar.getInstance();
        datepicker=new DatePickerDialog(this, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate=Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                callcalendar.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View view) {
        if(view == callcalendar) {
           datepicker.show();
     }
    }

}














